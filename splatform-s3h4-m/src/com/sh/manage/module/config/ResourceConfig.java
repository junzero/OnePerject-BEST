package com.sh.manage.module.config;

/**
 * Title. 资源配置类,获取config.properties的属性值<br>
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
public class ResourceConfig extends BaseConfiguration {

	/**
	 * 文件统一上传绝对目录
	 * 
	 * @return 文件统一上传绝对目录
	 */
	public static String getSysUploadAbsPath() {
		return getVal("SYS.UPLOAD.ABS.PATH");
	}
	
	/**
	 * 文件统一上传WEB目录
	 * 
	 * @return 文件统一上传WEB目录
	 */
	public static String getSysUploadWebPath() {
		return getVal("SYS.UPLOAD.WEB.PATH");
	}
	/**
	 * 获得平台名称
	 * 
	 * @return 获得平台名称
	 */
	public static String getPlatformName() {
		return getVal("platform.name");
	}
	/**
	 * memcache客户端地址
	 * 
	 * @return memcache客户端地址
	 */
	public static String getMemcacheClientUrl() {
		return getVal("MEMCACH_CLIENT_URL");
	}
	
	/**
	 * 用户信息第三方接入URL
	 * @return
	 */
	public static String getUserInfoToIVRUrl(){
		
		return getValIVR("USERINFO_URL");
	}
	/**
	 * 用户信息第三方接入平台标识码
	 * @return
	 */
	public static String getUserInfoToIVRIdentiy(){
		
		return getValIVR("USERINFO_IDENT");
	}
	/**
	 * 用户信息第三方接入密钥
	 * @return
	 */
	public static String getUserInfoToIVRKey(){
		
		return getValIVR("USERINFO_KEY");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
