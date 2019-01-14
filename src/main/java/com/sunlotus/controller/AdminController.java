package com.sunlotus.controller;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.sunlotus.common.UserInterceptor;

@Before(UserInterceptor.class)
public class AdminController extends Controller {
	
	public void index(){
		render("/index.html");
	}
	
}
