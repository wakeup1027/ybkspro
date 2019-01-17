package com.sunlotus.service;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.plugin.activerecord.Page;
import com.sunlotus.common.model.Opend_log;

public class OpenLogService {
	/**
	 * 加载全部数据且可以分页
	 * @param page
	 * @param limit
	 * @return
	 */
	public JSONObject LoadTableData(Integer page, Integer limit){
		Page<Opend_log> list = Opend_log.dao.paginate(page, limit, "select *", "FROM opennumber_log order by create_time desc");
		JSONObject json = new JSONObject();
		json.put("code", 0);
		json.put("msg", "");
		json.put("count", list.getTotalRow());
		json.put("data", list.getList());
		return json;
	}
	

	/**
	 * 搜索且分页功能
	 * @param username
	 * @param city
	 * @param classify
	 * @param wealthStr
	 * @param page
	 * @param limit
	 * @return
	 */
	public JSONObject Sreach(String username, Integer page, Integer limit){
		Page<Opend_log> list = Opend_log.dao.paginate(page, limit, "select *", "FROM opennumber_log WHERE 1=1 AND create_qihao LIKE '%"+username+"%'");
		JSONObject json = new JSONObject();
		json.put("code", 0);
		json.put("msg", "");
		json.put("count", list.getTotalRow());
		json.put("data", list.getList());
		return json;
	}
	
	/**
	 * 用于修改时加载原来的数据
	 * @param idstr
	 * @return
	 */
	public Opend_log umodel(String idstr){
		return Opend_log.dao.findById(idstr);
	}
	
	/**
	 * 修改
	 * @param per
	 * @return
	 */
	public JSONObject updataUser(Opend_log per){
		JSONObject jsono = new JSONObject();
		try {
			per.update();
			jsono.put("mes", true);
		} catch (Exception e) {
			jsono.put("mes", false);
		}
		return jsono;
	}
}
