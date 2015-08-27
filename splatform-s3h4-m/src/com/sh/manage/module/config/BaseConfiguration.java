package com.sh.manage.module.config;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;

/**
 * Title. 基础配置类<br>
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
public class BaseConfiguration {
	/**
	 * 日志记录器
	 */
	protected static final Logger logger = Logger
			.getLogger(BaseConfiguration.class);
	/**
	 * 构造加载config.properties
	 */
	protected static final PropertiesConfiguration config = PropertiesFactory.getPropertiesConfiguration("config.properties");
	
	
	protected static final PropertiesConfiguration configIVR = PropertiesFactory.getPropertiesConfiguration("configIvr.properties");
	
	/**
	 * 获取属性值方法1
	 * @param key
	 * @return
	 */
	protected static String getVal(String key){
		return getVal(key,"");
	}
	/**
	 * 获取属性值方法2
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	protected static String getVal(String key, String defaultValue) {
		return config.getString(key, defaultValue);
	}
	/**
	 * 获取属性值方法3
	 * @param key
	 * @return
	 */
	protected static String[] getVals(String key){
	    return config.getStringArray(key);
	}
	
	
	/**
	 * 获取属性值方法4
	 * @param key
	 * @return
	 */
	protected static String getValIVR(String key){
		return getValIVR(key,"");
	}
	/**
	 * 获取属性值方法5
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	protected static String getValIVR(String key, String defaultValue) {
		return configIVR.getString(key, defaultValue);
	}
	/**
	 * 获取属性值方法6
	 * @param key
	 * @return
	 */
	protected static String[] getValsIVR(String key){
	    return configIVR.getStringArray(key);
	}
	
}
