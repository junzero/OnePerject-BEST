/**
 * JSONUtils.java 2011-03-08 09:40:08
 */
package com.sh.manage.utils;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.weaver.ast.HasAnnotation;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonGenerator.Feature;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.MappingJsonFactory;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.type.TypeReference;

import com.sh.manage.entity.Member;
import com.sh.manage.entity.Vipcard;

import net.sf.json.util.JSONUtils;

/**
 * JSON转换处理工具类
 * 
 * @author 
 * @created 
 */
public class JsonUtils {

	/** 日志记录者 */
	private static Log logger = LogFactory.getLog(JsonUtils.class);

	/**
	 * 数组格式JSON串转换为ObjectList对象
	 * 
	 * @param <T>
	 * @param json
	 *            JSON字符串
	 * @param tr
	 *            TypeReference,例如: new TypeReference< List<Album> >(){}
	 * @return ObjectList对象
	 */
	public static <T> T jsonToGenericObject(String json, TypeReference<T> tr) {

		if (StringUtils.isEmpty(json)) {
			return null;
		} else {

			// Jackson方式将Json转换为对象
			MappingJsonFactory f = new MappingJsonFactory();
			try {
				JsonParser parser = f.createJsonParser(json);
				return parser.readValueAs(tr);

			}
			catch (Exception e) {

				if (logger.isWarnEnabled()) {
					logger.warn("将JSON[" + json + "]转换成泛型[" + tr.getType() + "]出现异常!", e);
				}

				return null;
			}
		}
	}

	/**
	 * JSON转换为Object对象
	 * 
	 * @param json
	 *            JSON字符串
	 * @param clazz
	 *            要转换成的类的类型
	 * @return Object对象
	 */
	public static <T> T jsonToObject(String json, Class<T> clazz) {

		if (StringUtils.isEmpty(json)) {
			return null;

		} else {

			// JSON转换为对象
			MappingJsonFactory f = new MappingJsonFactory();
			try {
				JsonParser parser = f.createJsonParser(json);
				return parser.readValueAs(clazz);
			}
			catch (Exception e) {

				if (logger.isWarnEnabled()) {
					logger.warn("将JSON[" + json + "]转换成[" + clazz.getCanonicalName() + "]出现异常!", e);
				}

				return null;
			}
		}
	}

	public  static void main(String[] args){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("name", "ss");
		Vipcard card =  new Vipcard();
		card.setCardNum("name");
		List<Vipcard> cars = new ArrayList<>();
		cars.add(card);
		Member member = new Member();
		card.setMember(member);
		System.out.println(JsonUtils.toJson(card));
		System.out.println(JsonUtils.toJson(cars));
		
	}
	/**
	 * Object对象转换为JSON格式 例如List对象、JavaBean对象、JavaBean对象数组、Map对象、List Map对象
	 * 
	 * @param object
	 *            传入的Object对象
	 * @return object的JSON格式字符串
	 */
	public static String toJson(Object object) {

		// 将对象转为JSON
		MappingJsonFactory f = new MappingJsonFactory();
		StringWriter sw = new StringWriter();

		try {
			JsonGenerator generator = f.createJsonGenerator(sw);
			generator.configure(Feature.AUTO_CLOSE_JSON_CONTENT, true);
			generator.writeObject(object);
			generator.close();

		}
		catch (Exception e) {

			if (logger.isWarnEnabled()) {
				logger.warn("将对象转换成JSON出现异常!", e);
			}

			return "";
		}

		return sw.toString();

	}

	/**
	 * 数组格式JSON串转换为ObjectList对象
	 * 
	 * @param <T> 
	 * @param json JSON字符串
	 * @param tr TypeReference,例如: new TypeReference< List<Album> >(){}
	 * @return ObjectList对象
	 */
	public static <T> T jsonToGeneric(String json, TypeReference<T> tr){

		if (StringUtils.isEmpty(json)) {
			return null;

		} else {
			// 将Json转换为对象
			MappingJsonFactory f = new MappingJsonFactory();
			
			try {
				JsonParser parser = f.createJsonParser(json);
				return parser.readValueAs(tr);
			} catch (Exception e) {
				
				logger.warn("JSON格式转换为泛型对象出现异常!", e);
				
				return null;
			}
		}
	}
}