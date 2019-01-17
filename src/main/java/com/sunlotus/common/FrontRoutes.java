package com.sunlotus.common;

import com.jfinal.config.Routes;
import com.sunlotus.controller.AdminController;
import com.sunlotus.controller.IndexController;
import com.sunlotus.controller.LoginController;
import com.sunlotus.controller.OpenController;
import com.sunlotus.controller.OpenLogController;
import com.sunlotus.sys.quartz.controller.QuartzController;

/**
 * 前台路由
 */
public class FrontRoutes extends Routes {

	public void config() {
		add("/", IndexController.class);
		add("/adminstr/dfsw/rerw/gfwqwe/fghfghrt", AdminController.class);
		add("/adminstr/userpc/kqskdj/yuiasjx/qwieusk", OpenController.class);
		add("/adminstr/fdgdw/fgfwe/aqwe/qwieusk", OpenLogController.class);
		add("/adminstr/cvncvs/dsfs/wqewq/fcvbxxz", QuartzController.class); 
		add("/adsdowekd/fsddw/hgjgw/bnmrty/wertgfsd", LoginController.class); 
	}
}
