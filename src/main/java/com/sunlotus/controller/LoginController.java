package com.sunlotus.controller;

import com.jfinal.core.Controller;
import com.sunlotus.sys.until.FormString;

public class LoginController extends Controller{

	public void index(){
		render("/login.html");
	}
	
	public void checlogin(){
		if(new FormString().userLogin(getPara("userName"), getPara("password"))){
			setSessionAttr("loginUser", getPara("userName"));
			setAttr("mes", "");
			redirect("/adminstr/dfsw/rerw/gfwqwe/fghfghrt");
		}else{
			setAttr("mes", "用户名或者密码错误，请联系管理员获取授权！");
			render("/login.html");
		}
	}
}
