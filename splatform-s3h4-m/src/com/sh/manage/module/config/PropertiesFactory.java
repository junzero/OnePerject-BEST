package com.sh.manage.module.config;

import java.util.Iterator;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.log4j.Logger;

/**
 * Title. 配置工厂类<br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 
 * <p>
 * Company: 
 * <p>
 * Author: 
 * <p>
 * Version: 1.0
 * <p>
 */
public class PropertiesFactory {
	/**
	 * 日志记录器
	 */
	private static final Logger logger = Logger.getLogger(PropertiesFactory.class);
	/**
	 * 通过文件名获取配置信息
	 * @param fileName
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	static PropertiesConfiguration getPropertiesConfiguration(String fileName){
		PropertiesConfiguration config = null;
		try {
			 config = new PropertiesConfiguration();
		     config.setEncoding("gbk");
		     config.load(fileName);
		     //自动重载策略
		     config.setReloadingStrategy(new FileChangedReloadingStrategy());
		     //获取config信息
		     Iterator iter = config.getKeys();
		     StringBuffer sbf = new StringBuffer();
		     while(iter.hasNext()){
		    	 String key = (String) iter.next();
		    	 sbf.append(key + " = " + config.getString(key) + "\r\n");
		     }
		     logger.info("properties file loaded: \r\n" + sbf.toString());
		} catch (Exception e) {
			logger.error("Initial Configuration failed."+e);
			config = null;
		}
		return config;
	}
}
