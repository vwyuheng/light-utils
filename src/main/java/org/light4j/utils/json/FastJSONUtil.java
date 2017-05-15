package org.light4j.utils.json;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 封装fastJson工具类
 * 
 * @author longjiazuo
 */
public class FastJSONUtil {

	/**
	 * 把JSON文本parse为JSONObject或者JSONArray
	 */
	public static Object parse(String text) {
		return JSON.parse(text);
	}

	/**
	 * 把JSON文本parse成JSONObject
	 */
	public static JSONObject parseObject(String text) {
		return JSONObject.parseObject(text);
	}

	/**
	 * 把JSON文本parse为JavaBean
	 */
	public static <T> T parseObject(String text, Class<T> clazz) {
		return JSON.parseObject(text, clazz);
	}

	/**
	 * 把JSON文本parse成JSONArray
	 */
	public static JSONArray parseArray(String text) {
		return JSON.parseArray(text);
	}

	/**
	 * 把JSON文本parse成JavaBean集合
	 * 
	 */
	public static <T> List<T> parseArray(String text, Class<T> clazz) {
		return JSON.parseArray(text, clazz);
	}

	/**
	 * 将JavaBean序列化为JSON文本
	 * 
	 */
	public static String toJSONString(Object object) {
		return JSON.toJSONString(object);
	}

	/**
	 * 将JavaBean序列化为JSON文本
	 * 
	 */
	public static String toJSONString(Object object, String serializerFeature) {
		return JSON.toJSONString(object, SerializerFeature.WriteMapNullValue);
	}

	/**
	 * 将JavaBean序列化为带格式的JSON文本
	 * 
	 */
	public static String toJSONString(Object object, boolean prettyFormat) {
		return JSON.toJSONString(object, prettyFormat);
	}

	/**
	 * 将JavaBean转换为JSONObject或者JSONArray
	 * 
	 */
	public static Object toJSON(Object javaObject) {
		return JSON.toJSON(javaObject);
	}
}
