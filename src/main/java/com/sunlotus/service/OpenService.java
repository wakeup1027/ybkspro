package com.sunlotus.service;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.plugin.activerecord.Page;
import com.sunlotus.common.model.OpenNumber;

public class OpenService {
	/**
	 * 加载全部数据且可以分页
	 * @param page
	 * @param limit
	 * @return
	 */
	public JSONObject LoadTableData(Integer page, Integer limit){
		Page<OpenNumber> list = OpenNumber.dao.paginate(page, limit, "select *", "FROM yushenumber");
		JSONObject json = new JSONObject();
		json.put("code", 0);
		json.put("msg", "");
		json.put("count", list.getTotalRow());
		json.put("data", list.getList());
		return json;
	}
	
	/**
	 * 删除
	 */
	public JSONObject delUser(String dels){
		JSONObject jsono = new JSONObject();
		try {
			OpenNumber pson = new OpenNumber();
			pson.set("id", dels);
			pson.delete();
			jsono.put("mes", true);
		} catch (Exception e) {
			jsono.put("mes", false);
		}
		return jsono;
	}
	
	/**
	 * 保存用户
	 * @param per
	 * @return
	 */
	public JSONObject savaUser(OpenNumber per){
		JSONObject jsono = new JSONObject();
		try {
			per.save();
			jsono.put("mes", true);
		} catch (Exception e) {
			jsono.put("mes", false);
		}
		return jsono;
	}
}
