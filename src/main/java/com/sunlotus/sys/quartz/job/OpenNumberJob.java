package com.sunlotus.sys.quartz.job;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.sunlotus.common.model.OpenNumber;
import com.sunlotus.common.model.Opend_log;
import com.sunlotus.common.model.TaskConfig;
import com.sunlotus.sys.until.FormString;

public class OpenNumberJob implements Job{
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static SimpleDateFormat qihaoDate = new SimpleDateFormat("yyMMdd");
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		String igstr = "";
		//获取开奖记录中的当天最大期号
		Record qhrd = Db.findFirst("SELECT MAX(RIGHT(create_qihao,3)) qihaoNum FROM opennumber_log WHERE create_qihao LIKE ?", qihaoDate.format(new Date())+"%");
		if(null==qhrd){
			igstr = qihaoDate.format(new Date())+"001";
		}else{
			igstr = change_water_number(qhrd.getStr("qihaoNum"));
		}
		//更新这个字段，然后让秒定时器读秒
		TaskConfig qco = TaskConfig.dao.findById(1);
    	qco.set("second", 300);//恢复300秒：5分钟
    	//igstr = FormString.formNum(qco.getInt("nowNum"));
    	//qco.set("nowNum", qco.getInt("nowNum")+1);
    	qco.update();
    	
    	//开始从开奖池里面拿号码
    	Opend_log ol = new Opend_log();
    	ol.set("create_qihao", igstr);
		OpenNumber onum = OpenNumber.dao.findFirst("SELECT * FROM yushenumber WHERE qihao='next' ORDER BY creatime ASC");
		if(onum!=null){//不为空拿开奖池里面的号码
			ol.set("create_open", onum.getStr("openumber"));
			onum.delete();//删除开奖池里面的已开过的号码
		}else{//为空就再拿期号匹配，如果期号还没有匹配就随机开奖
			OpenNumber onumbyqh = OpenNumber.dao.findFirst("SELECT * FROM yushenumber WHERE qihao='2019"+igstr+"'");
			if(onumbyqh!=null){
				ol.set("create_open", onumbyqh.getStr("openumber"));
				onumbyqh.delete();//删除开奖池里面的已开过的号码
			}else{
				ol.set("create_open", new FormString().getThreeNum());
			}
		}
		ol.set("create_time", sdf.format(new Date()));
		ol.save();
	}
	
	/**
     * 把规定的数字字符串转正流水号
     */
    public static String change_water_number(String numstr){
    	String pinyin = qihaoDate.format(new Date());
    	if(null==numstr||"".equals(numstr)||"null".equals(numstr)){
    		return "";
    	}
    	int num = Integer.parseInt(numstr);
    	num+=1;
    	if((num+"").length()==1){
    		return pinyin+"00"+num;
    	}else if((num+"").length()==2){
        	return pinyin+"0"+num;
        }else if((num+"").length()==3){
        	return pinyin+""+num;
        }
    	return "";
    }
	
}
