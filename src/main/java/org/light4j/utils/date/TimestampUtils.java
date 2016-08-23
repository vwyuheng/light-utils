package org.light4j.utils.date;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Timestamp工具类
 * 
 * @author longjiazuo
 */
public class TimestampUtils {
	/**
     * 将String 转换为 timestamp<br>
     * 注：value必须形如： yyyy-mm-dd hh:mm:ss[.f...] 这样的格式，中括号表示可选，否则报错！！！ 
     * 
     * @param value
     * @param format
     * @return
     */
    public static Timestamp string2Timestamp(String value){
    	Timestamp ts = new Timestamp(System.currentTimeMillis());  
    	ts = Timestamp.valueOf(value);
    	return ts;
    }
    
    /**
     * 将timeStamp 转换为String类型，format为null则使用默认格式 yyyy-MM-dd HH:mm:ss
     * 
     * @param value
     * @param format
     * @return
     */
    public static String timeStamp2String(Timestamp value,String format){
    	if(null == value){
    		return "";
    	}
    	SimpleDateFormat sdf = DateFormatUtils.getFormat(format);
    	
    	return sdf.format(value);
    }
}
