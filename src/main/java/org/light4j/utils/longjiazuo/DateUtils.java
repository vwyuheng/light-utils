package org.light4j.utils.longjiazuo;

import java.io.File;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * 时间工具类
 * 
 * @author longjiazuo
 */
public class DateUtils {

	private static DateUtils instance = null;
	public static final String DEF_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String DEF_DATE_NO_TIME_FORMAT = "yyyy-MM-dd";
    /**
     * 
     * Methods Descrip:这是一个单例模式的设计方式,在这个法中主要是得到本类的实例方法
     * 
     *  
     */
    public static synchronized DateUtils getInstance() {
        if (instance == null) {
            instance = new DateUtils();
        }
        return instance;
    }

    /**
     * 这是一个构造函数
     *  
     */
    public DateUtils() {
    }

    /**
     * 
     * Methods Descrip:本方法主要是得到当前的日期
     * 
     * @return
     *  
     */
    public static Timestamp getSysTimestamp() {
    	final TimeZone zone = TimeZone.getTimeZone("GMT+8"); //获取中国时区
		TimeZone.setDefault(zone); //设置时区
        return new Timestamp((new Date()).getTime());
    }

    /**
     * 
     * Methods Descrip:按指定格式解析日期为timestamp对象
     * 
     * @param datetime:输入的String类型的日期字符串
     *            格式为:2004-01-11
     * @param datepattern:指定输出的格式类型
     * @return Timestamp对象
     *  
     */
    public static Timestamp getstrTimestamp(String datetime, String datepattern) {
        Date bb = null;
        try {
            DateFormat parser = new SimpleDateFormat(datepattern);
            bb = parser.parse(datetime);
            return new Timestamp(bb.getTime());
        } catch (ParseException ex) {
            //TODO
        }
        return null;
    }

    /**
     * 
     * Methods Descrip:得到本月开始时间
     * 
     * @param strMon
     *            :输入的String类型的月份字符串 格式:2004-03
     * @return:String类型的本月开始时间
     *  
     */
    public static String getCurMonBegin(String strMon) {
        String bdate = "";
        bdate = strMon + "-01";
        return bdate;
    }

    /**
     * 
     * Methods Descrip:得到本月结束时间
     * 
     * @param strMon:输入的String类型的月份字符串
     *            格式:2004-03
     * @return :String类型的本月结束时间
     *  
     */
    public static String getCurMonEnd(String strMon) {
        return getCurMonEnd(strMon, "yyyy-MM");
    }
    public static String getCurMonEndCHN(String strMon) {
        return getCurMonEnd(strMon, "yyyy年MM月");
    }
    
    public static String getCurMonEnd(String strMon, String pattern) {
        return null;
    }

    /**
     * 
     * Methods Descrip:获得指定日期所在week的起始日期，提供和返回的日期格式为yyyy-MM-dd
     * 
     * @param strDate:提供的日期
     * @return 指定日期所在week的起始日期
     *  
     */
    public static String getCurWeekBegin(String strDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.parse(strDate, new ParsePosition(0));
        Calendar calendar = dateFormat.getCalendar();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        calendar.add(Calendar.DATE, 1 - day);
        return dateFormat.format(calendar.getTime());
    }

    /**
     * 
     * Methods Descrip:获得指定日期所在week的终止日期，提供和返回的日期格式为yyyy-MM-dd
     * 
     * @param strDate:提供的日期
     * @return:指定日期所在week的终止日期
     *  
     */
    public static String getCurWeekEnd(String strDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.parse(strDate, new ParsePosition(0));
        Calendar calendar = dateFormat.getCalendar();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        calendar.add(Calendar.DATE, 7 - day);
        return dateFormat.format(calendar.getTime());
    }

    /**
     * 
     * Methods Descrip:日期大小的比较
     * 
     * @param date:字符串格式的开始日期
     * @param edate:字符串格式的日期结束日期
     * @return:boolean 如果date>=edate则返回true,否则返回false
     *  
     */
    public static boolean comDate(String date, String edate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date dt1 = formatter.parse(date, new ParsePosition(0));
        Date dt2 = formatter.parse(edate, new ParsePosition(0));
        return (!dt1.before(dt2));
    }
    
    /**
     * 
     * Methods Descrip:日期大小的比较
     * 
     * @param date:字符串格式的开始日期
     * @param edate:字符串格式的日期结束日期
     * @param pattern:指定的日期格式
     * @return:boolean 如果date>=edate则返回true,否则返回false
     *  
     */
    public static boolean comDate(String date, String edate,String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        Date dt1 = formatter.parse(date, new ParsePosition(0));
        Date dt2 = formatter.parse(edate, new ParsePosition(0));
        return (!dt1.before(dt2));
    }
    /**
     * 
     * Methods Descrip:String类型的日期加减
     * 
     * @param date:系统时间；
     * @param type:加减的类型
     *            D 日期 M 月份 Y 年
     * @param into:加减的数量
     * @return:String 返回时间
     *  
     */
    public static String addTime(String date, String type, int into,String pattern) {
        date = date.replaceAll("-", "/");
        GregorianCalendar grc = new GregorianCalendar();
        Date d =  parseDate(date);
        if(d == null){
        	d = new Date();
        }
        grc.setTime(d);
        if (type.equals("D")) {
            grc.add(GregorianCalendar.DATE, into);
        } else if (type.equals("M")) {
            grc.add(GregorianCalendar.MONTH, into);
        } else if (type.equals("Y")) {
            grc.add(GregorianCalendar.YEAR, into);
        } else if (type.equals("HH")) {
            grc.add(GregorianCalendar.HOUR, into);
        } else if (type.equals("MI")) {
            grc.add(GregorianCalendar.MINUTE, into);
        } else if (type.equals("SS")) {
            grc.add(GregorianCalendar.SECOND, into);
        }
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return new String(formatter.format(grc.getTime()));
    }
    
    public static String addTimeByPattern(String date, String type, int into,String pattern) {
        date = date.replaceAll("-", "/");
        GregorianCalendar grc = new GregorianCalendar();
        Date d =  parseDate(date, pattern);
        if(d == null){
        	d = new Date();
        }
        grc.setTime(d);
        if (type.equals("D")) {
            grc.add(GregorianCalendar.DATE, into);
        } else if (type.equals("M")) {
            grc.add(GregorianCalendar.MONTH, into);
        } else if (type.equals("Y")) {
            grc.add(GregorianCalendar.YEAR, into);
        } else if (type.equals("HH")) {
            grc.add(GregorianCalendar.HOUR, into);
        } else if (type.equals("MI")) {
            grc.add(GregorianCalendar.MINUTE, into);
        } else if (type.equals("SS")) {
            grc.add(GregorianCalendar.SECOND, into);
        }
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);

        return new String(formatter.format(grc.getTime()));
    }

    /**
     * 
     * Methods Descrip:Timestamp型的日期加减
     * 
     * @param date:Timestamp
     *            传入的基础时间
     * @param type:String
     *            加减的类型 D 日期 M 月份 Y 年
     * @param into:int
     *            加减的数量
     * @return:Timestamp 返回时间
     *  
     */
    public static Timestamp addDateTime(Timestamp date, String type, int into) {
        GregorianCalendar grc = new GregorianCalendar();
        grc.setTime(new Date(date.getTime()));
        if (type.equals("D")) {
            grc.add(GregorianCalendar.DATE, into);
        } else if (type.equals("M")) {
            grc.add(GregorianCalendar.MONTH, into);
        } else if (type.equals("Y")) {
            grc.add(GregorianCalendar.YEAR, into);
        } else if (type.equals("HH")) {
            grc.add(GregorianCalendar.HOUR, into);
        } else if (type.equals("MI")) {
            grc.add(GregorianCalendar.MINUTE, into);
        } else if (type.equals("SS")) {
            grc.add(GregorianCalendar.SECOND, into);
        }
        return new Timestamp(new Date(grc.getTimeInMillis())
                .getTime());
    }

    /**
     * 
     * 日期的加减；No 时，分，秒
     * 
     * @param date:系统时间
     * @param type:String
     *            加减的类型 D 日期 M 月份 Y 年
     * @param into:int
     *            加减的数量
     * @return 返回时间
     *  
     */
    public static String addDate(String date, String type, int into) {
    	date = date.replaceAll("-", "/");//适应系统时间格式，否则直接传入yyyy-MM-DD格式的参数会出错，修改人刘浩源，时间：2009-08-03
        GregorianCalendar grc = new GregorianCalendar();
        //grc.setTime(new Date(date));
        Date d =  parseDate(date);
        if(d == null){
        	d = new Date();
        }
        grc.setTime(d);
        if (type.equals("D")) {
            grc.add(GregorianCalendar.DATE, into);
        } else if (type.equals("M")) {
            grc.add(GregorianCalendar.MONTH, into);
        } else if (type.equals("Y")) {
            grc.add(GregorianCalendar.YEAR, into);
        } else {
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        return new String(formatter.format(grc.getTime()));
    }

    /**
     * 
     * Methods Descrip:得到数据库时间日期
     * 
     * @param date:数据库得到的带时间的字符串
     * @return:String 日期字符串
     *  
     */
    public static String getDateString(String date) {
    	if(date!=null && date.length()>10)
    		date = date.substring(0, 10);
        return date;
    }
   
    /**
     *  
     * Methods Descrip:返回数据库时间的前10位字符串表示
     * @param date:Timestamp 日期
     * @return
     *
     */
    public static String getDateString(Timestamp date) {
		if (date == null)
			return null;
		String s = date.toString();
		return s.substring(0, 10);
	}
    
    /**
     *  
     * Methods Descrip:将字符串转换为日期
     * @param exifDate:日期字符串
     * @return Date:
     *
     */
    public static Date parseDate(String exifDate) {
		if (exifDate == null) {
			return null;
		}
		String patterns[];
		int i;
		patterns = (new String[] { "yyyy:MM:dd HH:mm:ss", "yyyy:MM:dd HH:mm",
				"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "dd.MM.yy HH:mm",
				"yyyyMMdd HHmmss","yyyyMMdd.HHmmss", "yyyyMMdd HHmm", "MM/dd/yy hh:mm a",
				"HH:mm:ss dd.MM.yyyy", "yyyy:MM:dd", "yyyy-MM-dd", "dd.MM.yy",
				"yyyyMMdd", "yyyy/MM/dd","yyyy/MM/dd HH:mm:ss", "MM/dd/yy", "yyyy:MM:dd HH:mm:sss"});
		for (i = 0; i < patterns.length; i++) {
			try {
				DateFormat parser = new SimpleDateFormat(patterns[i]);
				return parser.parse(exifDate);
			} catch (ParseException ex) {
			}
		}
		return null;
	}
    
    /**
     *  
     * Methods Descrip:按指定格式转换日期字符串为日期对象,如果解析失败,返回null
     * @param date:日期字符串
     * @param pattern:指定的日期格式
     * @return:Date 日期
     *
     */
    public static Date parseDate(String date, String pattern) {
		if (date == null)
			return null;

		try {
			DateFormat parser = new SimpleDateFormat(pattern);
			return parser.parse(date);
		} catch (ParseException ex) {
		}

		return null;
	}
    
    /**
     *  
     * Methods Descrip:按指定格式转换日期对象为日期字符串,如果解析失败,返回null
     * @param timestamp:Timestamp类型的日期
     * @param pattern:指定的日期格式
     * @return: String 日期
     *
     */
    public static String parseDate(Timestamp timestamp, String pattern) {
		if (timestamp == null)
			return null;

		DateFormat parser = new SimpleDateFormat(pattern);
		return parser.format(timestamp);
	}
    
    /**
     *  
     * Methods Descrip:计算两个时间的相差天数
     * @param time1:开始日期
     * @param time2:结束日期
     * @return:int 相差天数
     *
     */
	public static int getDiscrepancyNum(Timestamp time1, Timestamp time2) {
		int result = 0;
		if (time1 != null && time2 != null) {
			long temp = time1.getTime() - time2.getTime();
			if (temp > 0) {
				result = (int) ((temp / (24 * 60 * 60 * 1000)));
			} else {
				result = -(int) (((temp / (24 * 60 * 60 * 1000))));
			}

		}
		return result;
	}
	
	public static String getString(Date d, String pattern) {
		String ret;
		try {
			ret = new SimpleDateFormat(pattern).format(d);
		} catch (Exception e) {
			ret = null;
		}
		return ret;
	}
   
	/**
	 * 比较两个日期相差的天数
	 * 
	 * @param unit
	 *            单位,'D' = 天
	 * @param testDate
	 * @param refDate
	 * @return
	 * @throws java.lang.IllegalArgumentException
	 */
	public static int dateDiff(char unit, Date testDate, Date refDate)
			throws IllegalArgumentException {
		long testDateMillis = testDate.getTime();
		long refDateMillis = refDate.getTime();
		unit = Character.toUpperCase(unit);
		if (unit != 'D') {
			throw new IllegalArgumentException("无效的单位，当前只完成了 'D' (天数) 的计算!");
		} else {
			return (int) ((testDateMillis - refDateMillis) / 1000L / 60L / 60L / 24L);
			
		}
		/* @todo,如果要完成其它的写在这里 */
	}
	

	public static int dateDiffNew(char unit, Date testDate, Date refDate)
			throws IllegalArgumentException {
		long testDateMillis = testDate.getTime();
		long refDateMillis = refDate.getTime();
		unit = Character.toUpperCase(unit);
		if (unit != 'D') {
			throw new IllegalArgumentException("无效的单位，当前只完成了 'D' (天数) 的计算!");
		} else {
			long time = testDateMillis - refDateMillis;			 
			return  (int )  time/(1000*60*60*24);

		}
		
	}
	
	/**
	 * 
	 * @param unit
	 *            单位,'D' = 天
	 * @param testDate
	 * @param refDate
	 * @return
	 * @see dateDiff(char unit, Date testDate, Date refDate)
	 * @throws java.lang.IllegalArgumentException
	 */
	public static int dateDiff(char unit, String testDate, String refDate)
			throws IllegalArgumentException {
		return dateDiff(unit, toDate(testDate), toDate(refDate));
	}

	public static Date toDate(String dateString) {
		return parseDate(dateString);
	}
	
	
	/**得到给定时间的第一天
	 * @author yuanz
	 * @param	String 给定的时间yyyy-MM-dd
	 * @return String 返回给定时间的本月第一天
	 * */ 
	public static String getMonthFirstDay(String inpdate) {
		String[] inpdates =  inpdate.split("-");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Integer.parseInt(inpdates[0]),Integer.parseInt(inpdates[1]) -1, Integer.parseInt(inpdates[2]));
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(calendar.getTime());
	}
	
	/**得到本月的最后一天* 
	 * @author yuanz
	 * @param	String 给定的时间 yyyy-MM-dd
	 * @return String 返回给定时间的本月最后一天
	 * @return*/ 
	public static String getMonthLastDay(String inpdate) {
		String[] inpdates =  inpdate.split("-");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Integer.parseInt(inpdates[0]),Integer.parseInt(inpdates[1]) -1, Integer.parseInt(inpdates[2]));
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(calendar.getTime());
	} 
	/**
	 * 获取当月月初时间
	 * @author lr
	 * @version 1.0
	 * @date 2016年3月22日 下午2:08:53
	 * @return String
	 */
	public static String   getCurDayBegin(){
		
		  Calendar cal = Calendar.getInstance(); 
		  cal.setTime(new Date()); 
		  cal.set(Calendar.DAY_OF_MONTH, 1);
		 return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime()); 
	}
	
	/**
	 * 
	 * 获取当月月末时间
	 * @author lr
	 * @version 1.0
	 * @date 2016年3月22日 下午2:09:36
	 * @return String
	 */
	public static String   getCurDayEnd(){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		Calendar ca = Calendar.getInstance();    
		ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));  
		String last = format.format(ca.getTime());
		return last; 

	}
	/**
	 * 根据营业日期获取路径
	 * @version 1.0
	 * @date 2013年6月26日 下午1:41:07
	 * @param busiDate
	 * @return String
	 */
	public static String getBusiDate(Date busiDate) {
		StringBuilder filepath = new StringBuilder();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(busiDate);
		filepath.append(calendar.get(Calendar.YEAR)).append(File.separator);
		filepath.append(calendar.get(Calendar.MONTH) + 1).append(File.separator);
		filepath.append(calendar.get(Calendar.DAY_OF_MONTH));
		return filepath.toString();
	}
    public static String getBeforeDateStr(Date date){
    	 SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
		 Calendar calendar = Calendar.getInstance();
		 calendar.setTime(date);
		 calendar.add(Calendar.DAY_OF_MONTH, -1);//取当前天的前一天
		 date = calendar.getTime();
		 return sdf.format(date);
    }
    public static Date getBeforeDate(Date date){
		 Calendar calendar = Calendar.getInstance();
		 calendar.setTime(date);
		 calendar.add(Calendar.DAY_OF_MONTH, -1);//取当前天的前一天
		 date = calendar.getTime();
		 return date;
   }
   public static String getBeforeDateByStrFormat(Date date,String str){
	   date=getBeforeDate(date);
	   SimpleDateFormat sdf=new SimpleDateFormat(str);
	   return sdf.format(date);
   }
}
