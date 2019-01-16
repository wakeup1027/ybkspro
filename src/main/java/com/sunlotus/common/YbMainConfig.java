package com.sunlotus.common;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.plugin.redis.RedisPlugin;
import com.jfinal.server.undertow.UndertowServer;
import com.jfinal.template.Engine;
import com.sunlotus.common.model._MappingKit;
import com.sunlotus.sys.quartz.scan.QuartzScanner;
import com.sunlotus.sys.until.Config;

/**
 * 本 demo 仅表达最为粗浅的 jfinal 用法，更为有价值的实用的企业级用法
 * 详见 JFinal 俱乐部: http://jfinal.com/club
 * 
 * API引导式配置
 */
public class YbMainConfig extends JFinalConfig {
	private Config cf = Config.getInstance();//.getConfigValue("DevMode")
	
	/**
	 * 运行此 main 方法可以启动项目，此main方法可以放置在任意的Class类定义中，不一定要放于此
	 * 
	 * 使用本方法启动过第一次以后，会在开发工具的 debug、run config 中自动生成
	 * 一条启动配置，可对该自动生成的配置再添加额外的配置项，例如 VM argument 可配置为：
	 * -XX:PermSize=64M -XX:MaxPermSize=256M
	 */
	public static void main(String[] args) {
		/**
		 * 特别注意：Eclipse 之下建议的启动方式
		 */
		//JFinal.start("src/main/webapp", 80, "/", 5);
		UndertowServer.create(YbMainConfig.class, "undertow.txt").start();
		/**
		 * 特别注意：IDEA 之下建议的启动方式，仅比 eclipse 之下少了最后一个参数
		 */
		//JFinal.start("src/main/webapp", 80, "/");
	}
	
	/**
	 * 配置常量
	 */
	public void configConstant(Constants me) {
		// 加载少量必要配置，随后可用PropKit.get(...)获取值
		/*PropKit.use("a_little_config.txt");
		me.setDevMode(PropKit.getBoolean("devMode", false));*/
	}
	
	/**
	 * 配置路由
	 */
	public void configRoute(Routes me) {
		me.add(new FrontRoutes());
	}
	
	public void configEngine(Engine me) {
		me.addSharedFunction("/_layout.html");
		/*me.addSharedFunction("/common/_paginate.html");*/
	}
	
	/**
	 * 配置插件
	 */
	public void configPlugin(Plugins me) {
		RedisPlugin redis=new RedisPlugin("lxqRedis", "localhost");
		me.add(redis);
		
        String jdbcUrl = cf.getConfigValue("msserver.jdbcUrl");
        String url = cf.getConfigValue("msserver.url");
        String user = cf.getConfigValue("msserver.user");
        String passw = cf.getConfigValue("msserver.passw");
		C3p0Plugin c3p0Plugin = new C3p0Plugin(jdbcUrl, user, passw, url);
		me.add(c3p0Plugin);

		// 配置ActiveRecord插件
		ActiveRecordPlugin arp = new ActiveRecordPlugin(c3p0Plugin);
		//arp.setDialect(new SqlServerDialect());//注：更换sql server的时候需要添加这个，mysql不用。
		arp.setShowSql(false);
		_MappingKit.mapping(arp);
		me.add(arp);
	}
	
	/**
	 * 配置全局拦截器
	 */ 
	public void configInterceptor(Interceptors me) {
		
	}
	
	/**
	 * 配置处理器
	 */
	public void configHandler(Handlers me) {
		
	}
	
	/**
	 * 启动项目时执行
	 */
	public void afterJFinalStart(){
		QuartzScanner.getInstance().Quzar();
	}

}
