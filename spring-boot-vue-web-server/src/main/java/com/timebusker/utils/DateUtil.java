package com.timebusker.utils;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

public class DateUtil {

    protected final static Logger log = LoggerFactory.getLogger(DateUtil.class);

    public static final long SECOND = 1000;
    public static final long MINUTE = SECOND * 60;
    public static final long HOUR = MINUTE * 60;
    public static final long DAY = HOUR * 24;
    public static final long WEEK = DAY * 7;
    public static final long YEAR = DAY * 365; // or 366 ???

    public static final String DEFAULT_DATE = "1900-1-1";

    /**
     * This is the time difference between GMT time and Vietnamese time
     */
    public static final long GMT_VIETNAM_TIME_OFFSET = HOUR * 7;

    /**
     * RFC 822 date format
     */
    public static final String RFC_822_DATE_FORMAT = "EEE, dd MMM yyyy HH:mm:ss Z";
    // public static final String RFC_822_DATE_FORMAT = "EEE, d MMM yyyy
    // HH:mm:ss Z";

    /**
     * ISO 8601 [W3CDTF] date format
     */
    public static final String ISO_8601_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";

    /**
     * UTC style date format
     */
    public static final String UTC_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    /**
     * 系统默认日期格式：2008-12-10 21:01
     */
    public static final String DEFAULT_DATE_ONLY_PATTERN = "yyyy-MM-dd";

    /**
     * 系统默认日期格式：2008-12-10 21:01
     */
    public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm";

    public static final String DEFAULT_DATE_PATTERN2 = "yyyy-MM-dd HH:mm:ss";

    public static final String DEFAULT_DATE_PATTERN3 = "yyyyMMddHHmmss";

    public static final String DEFAULT_DATE_PATTERN4 = "yyyyMMddHHmm";

    public static final String DEFAULT_DATE_PATTERN5 = "yyyyMMdd";

    public static final String DEFAULT_DATE_PATTERN6 = "HHmm";

    public static final String DEFAULT_DATE_PATTERN7 = "HH:mm";

    public static final String DEFAULT_DATE_PATTERN8 = "dd-MMM-yy";

    public static final String DEFAULT_DATE_PATTERN9 = "dd-M月 -yy";

    public static final String DEFAULT_DATE_PATTERN_YEAR = "yyyy";

    public static final String DEFAULT_DATE_PATTERN_MONTH = "yyyyMM";

    /**
     * This is the time difference between GMT time and SERVER time
     */
    // private static long SERVER_TIME_OFFSET = HOUR * (new
    // DateOptions()).serverHourOffset;
    public static final String SCHEMA_DATE_JAVA = "yyyy-MM-dd", SCHEMA_DATE_JAVA_WITH_HM = "yyyy-MM-dd HH:mm", SCHEMA_DATE_JAVA_WITH_HMS = "yyyy-MM-dd HH:mm:ss";
    private static final String PATTERN_DATE = "\\d{4}-\\d{1,2}-\\d{1,2}", PATTERN_DATE_WITH_HM = "\\d{4}-\\d{1,2}-\\d{1,2}\\s{1}\\d{1,2}:\\d{1,2}", PATTERN_DATE_WITH_HMS = "\\d{4}-\\d{1,2}-\\d{1,2}\\s{1}\\d{1,2}:\\d{1,2}:\\d{1,2}";

    private static final String SCHEMA_DATE_JAVA_NOSEPARATOR = "yyyyMMdd", SCHEMA_DATE_JAVA_NOSEPARATOR_WITH_HM = "yyyyMMddHHmm", SCHEMA_DATE_JAVA_NOSEPARATOR_WITH_HMS = "yyyyMMddHHmmss";
    private static final String PATTERN_DATE_NOSEPARATOR = "\\d{8}", PATTERN_DATE_NOSEPARATOR_WITH_HM = "\\d{12}", PATTERN_DATE_NOSEPARATOR_WITH_HMS = "\\d{14}";


    private static DateFormat ddMMyyyyFormat = new SimpleDateFormat("dd/MM/yyyy");
    private static DateFormat yyyyMMddFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static DateFormat yyyyMMddFormat2 = new SimpleDateFormat("yyyy年MM月dd日");
    private static DateFormat rfc822Format = new SimpleDateFormat(RFC_822_DATE_FORMAT, Locale.US);
    private static DateFormat iso8601Format = new SimpleDateFormat(ISO_8601_DATE_FORMAT, Locale.US);
    private static DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT);
    private static DateFormat datetimeFormat = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT);
    private static DateFormat headerTimeFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US);

    static {
        TimeZone gmt = TimeZone.getTimeZone("GMT");
        headerTimeFormat.setTimeZone(gmt);
    }

    /**
     * private constructor
     */
    private DateUtil() {// prevent instantiation
    }

    public static String getDateDDMMYYYY(Date date) {
        return ddMMyyyyFormat.format(date);
    }

    public static String getDateYYYYMMDD(Date date) {
        return yyyyMMddFormat.format(date);
    }

    public static String getDateYYYYMMDD2(Date date) {
        return yyyyMMddFormat2.format(date);
    }

    public static String getDateRFC822(Date date) {
        return rfc822Format.format(date);
    }

    public static String getDateISO8601(Date date) {
        return iso8601Format.format(date);
    }

    public static String getHTTPHeaderTime(Date date) {
        return headerTimeFormat.format(date);
    }

    public static String formatDate(Date date) {
        return dateFormat.format(date);
    }

    public static String formatDateTime(Date date) {
        return datetimeFormat.format(date);
    }

    public static Date formatDate(String date, String pattern) {
        return formatDate(date, new SimpleDateFormat(pattern));
    }

    /**
     * 将日期字符串转换为Date类型的日期
     *
     * @param date 日期字符串(14位长度的日期字串)
     * @param sf   格式化器
     * @return 转换后的Date
     */
    public static Date formatDate(String date, SimpleDateFormat sf) {
        try {
            return sf.parse(date);
        } catch (ParseException pe) {
//			log.error(pe.getMessage());
            return null;
        }
    }

    public static String getBeforeOneDate(String partitionDateStr) {
        Date now = new Date();
        try {
            int year = Integer.parseInt(partitionDateStr.substring(0, 4));
            int month = Integer.parseInt(partitionDateStr.substring(4, 6));
            int date = Integer.parseInt(partitionDateStr.substring(6, 8));
            Calendar c = Calendar.getInstance();
            c.set(year, month - 1, date);
            //c.setTime(now);
            int offset = (int) (-1);
            c.add(Calendar.DAY_OF_MONTH, offset);
            now = c.getTime();
            SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
            String partitionBeforeOneDate = f.format(now);
            return partitionBeforeOneDate;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static Timestamp getCurrentGMTTimestamp() {
        return new Timestamp(System.currentTimeMillis() - (HOUR * 0));
    }

    public static void updateCurrentGMTTimestamp(Timestamp timeToUpdate) {
        timeToUpdate.setTime(System.currentTimeMillis() - (HOUR * 0));
    }

    public static Date getVietnamDateFromGMTDate(Date date) {
        return new Date(date.getTime() + GMT_VIETNAM_TIME_OFFSET);
    }

    public static Date convertGMTDate(Date gmtDate, int hourOffset) {
        return new Date(gmtDate.getTime() + hourOffset * HOUR);
    }

    public static Timestamp convertGMTTimestamp(Timestamp gmtTimestamp,int hourOffset) {
        return new Timestamp(gmtTimestamp.getTime() + hourOffset * HOUR);
    }

    public static Timestamp getCurrentGMTTimestampExpiredYear(int offsetYear) {
        // return new Timestamp(System.currentTimeMillis() + offsetYear*(365*24*60*60*1000));
        Calendar now = Calendar.getInstance();
        now.add(Calendar.YEAR, offsetYear);
        return new Timestamp(now.getTime().getTime());
    }

    public static Timestamp getCurrentGMTTimestampExpiredMonth(int offsetMonth) {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.MONTH, offsetMonth);
        return new Timestamp(now.getTime().getTime());
    }

    public static Timestamp getCurrentGMTTimestampExpiredDay(int offsetDay) {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.DATE, offsetDay);
        return new Timestamp(now.getTime().getTime());
    }

    public static String format(Date date, String pattern) {
        DateFormat formatter = new SimpleDateFormat(pattern, Locale.US);
        return formatter.format(date);
    }

    public static String formatDuration(long duration, String pattern) {
        DurationFormater time = new DurationFormater(duration, pattern);
        return time.toString();
    }

    public static String formatDuration(long duration) {
        DurationFormater time = new DurationFormater(duration, null);
        return time.toString();
    }

    public static final String getDate(String pattern, Date date) {
        SimpleDateFormat df = null;
        String returnValue = "";
        if (date != null) {
            df = new SimpleDateFormat(pattern);
            returnValue = df.format(date);
        }
        return (returnValue);
    }

    /**
     * 获取时间的默认格式字符串：2008-12-01 21:01
     *
     * @param date
     * @return
     */
    public static String getDefaultDateString(Date date) {
        String str = "";
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTime(date);
//		calendar.set(Calendar.SECOND, 0);
//		calendar.set(Calendar.MILLISECOND, 0);
        str = format(date, DEFAULT_DATE_PATTERN);
        return str;
    }

    public static String getDefaultDatePattern4() {
        String str = "";
        str = format(new Date(), DEFAULT_DATE_PATTERN4);
        return str;
    }

    public static String getDefaultDatePattern4(Date date) {
        String str = "";
        str = format(date, DEFAULT_DATE_PATTERN4);
        return str;
    }

    /**
     * 获当前时间的默认格式字符串，2008-12-10 21:10
     *
     * @return String
     */
    public static String getDefaultDateString() {
        return getDefaultDateString(new Date());
    }

    /**
     * 获取与当前时间距离n秒的日期
     *
     * @param date
     * @param seconds
     * @return
     */
    public static Date getDateWithSecondDistance(Date date, int seconds) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, seconds);
        date = calendar.getTime();
        return date;
    }

    /**
     * 获取与当前时间距离n分钟的日期
     *
     * @param date
     * @param minutes
     * @return
     */
    public static Date getDateWithMinuteDistance(Date date, int minutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minutes);
        date = calendar.getTime();
        return date;
    }

    /**
     * 获取与当前时间距离n小时的日期
     *
     * @param date
     * @param hours
     * @return
     */
    public static Date getDateWithHourDistance(Date date, int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, hours);
        date = calendar.getTime();
        return date;
    }

    /**
     * 获取与当前时间距离n天的日期
     *
     * @param date
     * @param days
     * @return
     */
    public static Date getDateWithDayDistance(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);
        date = calendar.getTime();
        return date;
    }

    /**
     * 获取与当前时间距离n个月的日期
     *
     * @param date
     * @param months
     * @return
     */
    public static Date getDateWithMonthDistance(Date date, int months) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, months);
        date = calendar.getTime();
        return date;
    }

    /**
     * 获取与当前时间距离n年的日期
     *
     * @param date
     * @param years
     * @return
     */
    public static Date getDateWithYearDistance(Date date, int years) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, years);
        date = calendar.getTime();
        return date;
    }

    public static void main(String... args) {
        String startDate = "2014-01-02";
        System.out.println(DateUtil.compareDate(DateUtil.getDateYYYYMMDD(new Date()), startDate));
        while (!DateUtil.compareDate(startDate, DateUtil.getDateYYYYMMDD(new Date()))) {
            Date d = DateUtil.getDateWithMonthDistance(DateUtil.formatDate(startDate), 1);
            startDate = DateUtil.getDateYYYYMMDD(d);
            System.out.println(DateUtil.getDateYYYYMMDD(d));
        }
    }

    /**
     * 获取两个时间的毫秒数
     *
     * @param lastOccurredtime
     * @param datetime
     * @param pattern
     * @return
     */
    public static Long milliSecondsBetweenTwoTimes(String lastOccurredtime, String datetime, String pattern) {
        Long milliseconds = null;
        if (StringUtils.isNotBlank(lastOccurredtime) && StringUtils.isNotBlank(datetime)) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                Calendar calLastOccurredTime = Calendar.getInstance();
                Date dtLastOccurredTime = sdf.parse(lastOccurredtime);
                calLastOccurredTime.setTime(dtLastOccurredTime);
                Date dtDatetime = sdf.parse(datetime);
                Calendar calDatetime = Calendar.getInstance();
                calDatetime.setTime(dtDatetime);
                milliseconds = (calDatetime.getTimeInMillis() - calLastOccurredTime.getTimeInMillis());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return milliseconds;
    }

    /**
     * 将时间转化为时间戳：yyyyMMdHHmmss
     *
     * @return
     */
    public static String getTimeStamp(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(date);
    }

    /**
     * 将日期字符串转换为Date类型的日期
     *
     * @param date
     * @return
     */
    public static Date formatDate(String date) {
        if (!isTrue(date))
            throw new IllegalArgumentException("日期格式不正确");
        Date currDT = new Date(System.currentTimeMillis());
        try {
            if (Pattern.matches(PATTERN_DATE, date))
                return new SimpleDateFormat(SCHEMA_DATE_JAVA).parse(date);
            else if (Pattern.matches(PATTERN_DATE_WITH_HM, date))
                return new SimpleDateFormat(SCHEMA_DATE_JAVA_WITH_HM).parse(date);
            else if (Pattern.matches(PATTERN_DATE_WITH_HMS, date))
                return new SimpleDateFormat(SCHEMA_DATE_JAVA_WITH_HMS).parse(date);
            else if (Pattern.matches(PATTERN_DATE_NOSEPARATOR, date))
                return new SimpleDateFormat(SCHEMA_DATE_JAVA_NOSEPARATOR).parse(date);
            else if (Pattern.matches(PATTERN_DATE_NOSEPARATOR_WITH_HM, date))
                return new SimpleDateFormat(SCHEMA_DATE_JAVA_NOSEPARATOR_WITH_HM).parse(date);
            else if (Pattern.matches(PATTERN_DATE_NOSEPARATOR_WITH_HMS, date))
                return new SimpleDateFormat(SCHEMA_DATE_JAVA_NOSEPARATOR_WITH_HMS).parse(date);
            else
                return currDT;
        } catch (ParseException e) {
            return currDT;
        }
    }

    /**
     * 判断日期字符是否符合指定格式
     *
     * @param date
     * @return
     */
    public static boolean isTrue(String date) {
        if (Pattern.matches(PATTERN_DATE, date))
            return true;
        else if (Pattern.matches(PATTERN_DATE_WITH_HM, date))
            return true;
        else if (Pattern.matches(PATTERN_DATE_WITH_HMS, date))
            return true;
        else if (Pattern.matches(PATTERN_DATE_NOSEPARATOR, date))
            return true;
        else if (Pattern.matches(PATTERN_DATE_NOSEPARATOR_WITH_HM, date))
            return true;
        else if (Pattern.matches(PATTERN_DATE_NOSEPARATOR_WITH_HMS, date))
            return true;
        else
            return false;
    }

    /**
     * 比较两个日期，如果date1比date2大则返回true，否则返回false
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean compareDate(String date1, String date2) {
        boolean isLarge = false;
        try {
            Date dt1 = null;
            Date dt2 = null;
            if (date1.trim().length() == 10) {
                dt1 = formatDate(date1, DEFAULT_DATE_ONLY_PATTERN);
                dt2 = formatDate(date2, DEFAULT_DATE_ONLY_PATTERN);
            } else {
                dt1 = formatDate(date1, DEFAULT_DATE_PATTERN2);
                dt2 = formatDate(date2, DEFAULT_DATE_PATTERN2);
            }

            if (dt1.getTime() > dt2.getTime()) {
                isLarge = true;
            } else {
                isLarge = false;
            }
        } catch (Exception e) {
            isLarge = false;
            log.error(e + "日期格式错误");
        }
        return isLarge;
    }

    /**
     * 将时间戳格式的字符串转换为日期对象
     *
     * @return
     */
    public static Date timeStamp2Date(String timeStamp) {
        try {
            long unixLong = Long.parseLong(timeStamp) * SECOND;//时间戳是秒为单位
            return new Date(unixLong);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    /**
     * 默认的日期格式
     */
    private static String defaultDatePattern = "yyyy-MM-dd";
    /**
     * 默认的时间格式
     */
    private static String timePattern = "HH:mm:ss";

    //~ Methods ================================================================

    /**
     * 返回默认的日期格式 (yyyy-MM-dd)
     *
     * @return 默认日期格式(yyyy-MM-dd)
     */
    public static synchronized String getDatePattern() {
        return defaultDatePattern;
    }

    /**
     * 把日期按照指定格式转换为字符串
     *
     * @param date    输入的日期，如：2010-12-09 16:32:25
     * @param pattern 日期格式，如："yyyy-MM-dd"
     * @return 格式化的日期字符串，如：2010-12-09
     */
    public static final String getDateString(Date date, String pattern) {
        SimpleDateFormat df = null;
        String returnValue = "";
        if (pattern == null || pattern.equals("")) {
            pattern = getDatePattern();
        }
        if (date != null) {
            df = new SimpleDateFormat(pattern);
            returnValue = df.format(date);
        }
        return (returnValue);
    }

    public static Date convertStringToDate(String strDate) throws ParseException {
        Date aDate = null;
        try {
            if (log.isDebugEnabled()) {
                log.debug("converting date with pattern: " + getDatePattern());
            }
            aDate = convertStringToDate(getDatePattern(), strDate);
        } catch (ParseException pe) {
            log.error("Could not convert '" + strDate + "' to a date, throwing exception");
            pe.printStackTrace();
            throw new ParseException(pe.getMessage(), pe.getErrorOffset());
        }
        return aDate;
    }

    /**
     * 把日期字符串转换为指定格式的日期
     *
     * @param pattern 日期格式
     * @param strDate 日期字符串
     * @return 转换得到的日期对象
     * @throws ParseException
     * @see SimpleDateFormat
     */
    public static final Date convertStringToDate(String pattern, String strDate) throws ParseException {
        SimpleDateFormat df = null;
        Date date = null;
        if (pattern == null || pattern.equals("")) {
            pattern = getDatePattern();
        }
        df = new SimpleDateFormat(pattern);
//        if (log.isDebugEnabled()) {
//            log.debug("converting '" + strDate + "' to date with mask '"
//                      + pattern + "'");
//        }
        if (strDate != null && pattern != null && strDate.length() >= pattern.length()) {
            try {
                date = df.parse(strDate);
            } catch (ParseException pe) {
                throw new ParseException(pe.getMessage(), pe.getErrorOffset());
            }
        }
        return (date);
    }

    public static String convertDateToString(String pattern, Date date) {
        if ((pattern == null) || (pattern.trim().length() == 0)) {
            return convertDateToString(date);
        }
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        if (date != null) {
            return format.format(date);
        }
        return "";
    }


    public static Date convertStringToDateTime(String strDate) throws ParseException {
        Date aDate = null;
        try {
            if (log.isDebugEnabled()) {
                log.debug("converting date with pattern: " + defaultDatePattern + " " + timePattern);
            }
            aDate = convertStringToDate(defaultDatePattern + " " + timePattern, strDate);
        } catch (ParseException pe) {
            log.error("Could not convert '" + strDate +
                    "' to a date, throwing exception");
            pe.printStackTrace();
            throw new ParseException(pe.getMessage(), pe.getErrorOffset());
        }
        return aDate;
    }

    /**
     * 取得当前日期(格式：yyyy-MM-dd)的日历对象
     *
     * @return 当前日期日历对象
     * @throws ParseException
     */
    public static Calendar getToday() {
        Date today = new Date();
        SimpleDateFormat df = new SimpleDateFormat(getDatePattern());
        // This seems like quite a hack (date -> string -> date),
        // but it works ;-)
        String todayAsString = df.format(today);
        Calendar cal = new GregorianCalendar();
        try {
            cal.setTime(convertStringToDate(null, todayAsString));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return cal;
    }

    public static Date getMonthFirstDay(Date date) throws ParseException {
        Date inDate = date;
        if (inDate == null) {
            inDate = new Date();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String str = sdf.format(inDate) + "-01";
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        inDate = sdf.parse(str);
        return inDate;
    }

    public static Date getYearFirstDay(Date date) throws ParseException {
        Date inDate = date;
        if (inDate == null) {
            inDate = new Date();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String str = sdf.format(inDate) + "-01-01";
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        inDate = sdf.parse(str);
        return inDate;
    }

    /**
     * 得到指定日期的指定部分增加一个数值之后的日期，闰年由Calendar自己判断，<br>
     *
     * @param dateString 日期字符串
     * @param pattern    日期格式
     * @param addType    增加的部分，"yyyy"或"yy"或"y"为年份，"MM"或"M"为月份,"dd"或"d"为日
     * @param addValue   增加的数值，正数表示增加，负数表示减少
     * @return 返回日期值的字符串，格式：yyyy-MM-dd hh:mm:ss
     */
    public static String dateAdd(String dateString, String pattern, String addType, int addValue) {
        String ret = "";
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = df.parse(dateString);
            ret = dateAdd(date, addType, addValue);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * 得到指定日期的指定部分增加一个数值之后的日期，闰年由Calendar自己判断，<br>
     *
     * @param date     传入的日期
     * @param addType  增加的部分，"yyyy"或"yy"或"y"为年份，"MM"或"M"为月份,"dd"或"d"为日
     * @param addValue 增加的数值，正数表示增加，负数表示减少
     * @return 返回日期值的字符串，格式：yyyy-MM-dd hh:mm:ss
     */
    public static String dateAdd(Date date, String addType, int addValue) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int addPart = 1;
        if ("yyyy".equals(addType.toLowerCase().trim()) || "yy".equals(addType.toLowerCase().trim()) || "y".equals(addType.toLowerCase().trim())) {
            addPart = Calendar.YEAR;
        }
        if ("mm".equals(addType.toLowerCase().trim()) || "m".equals(addType.toLowerCase().trim())) {
            addPart = Calendar.MONTH;
        }
        if ("dd".equals(addType.toLowerCase().trim()) || "d".equals(addType.toLowerCase().trim())) {
            addPart = Calendar.DATE;
        }
        cal.add(addPart, addValue);
        return cal.getTime().toLocaleString();
    }

    /**
     * 取得格式化的两个时间差，结束时间应大于开始时间
     *
     * @param tStart 开始时间的getTime()值，long类型
     * @param tEnd   结束时间的getTime()值，long类型
     * @return 格式化的时间差值，如100 [y] 0 [m] 25 [d] 0 [h] 5 [min] 52 [s]
     */
    public static String getTimeDiff(long tStart, long tEnd) {
        long sec = 1000;
        long min = sec * 60;
        long hour = min * 60;
        long day = hour * 24;
        long month = day * 30;
        long year = 365 * day;
        // UTC is temporary realized to hold the time in miliss passed from ..
        // 1970
        long diffInMills = tEnd - tStart;
        if (diffInMills < min) {
            return String.valueOf(diffInMills / sec) + " [s]";
        } else if (diffInMills < hour) {
            long m = diffInMills / min;
            long s = (diffInMills - m * min) / sec;
            return String.valueOf(m) + " [min] " + String.valueOf(s) + " [s]";
        } else if (diffInMills < day) {
            long h = diffInMills / hour;
            long m = (diffInMills - h * hour) / min;
            long s = (diffInMills - h * hour - m * min) / sec;
            return String.valueOf(h) + " [h] " + String.valueOf(m) + " [min] " + String.valueOf(s) + " [s]";
        } else if (diffInMills < month) {
            long d = diffInMills / day;
            long h = (diffInMills - d * day) / hour;
            long m = (diffInMills - d * day - h * hour) / min;
            long s = (diffInMills - d * day - h * hour - m * min) / sec;
            return String.valueOf(d) + " [d] " + String.valueOf(h) + " [h] " + String.valueOf(m) + " [min] " + String.valueOf(s) + " [s]";
        } else if (diffInMills < year) {
            long mn = diffInMills / month;
            long d = (diffInMills - mn * month) / day;
            long h = (diffInMills - mn * month - d * day) / hour;
            long m = (diffInMills - mn * month - d * day - h * hour) / min;
            long s = (diffInMills - mn * month - d * day - h * hour - m * min) / sec;
            return String.valueOf(mn) + " [m] " + String.valueOf(d) + " [d] " + String.valueOf(h) + " [h] " + String.valueOf(m) + " [min] " + String.valueOf(s) + " [s]";
        } else { // if (diffInMills>=year)
            long y = diffInMills / year;
            long mn = (diffInMills - y * year) / month;
            long d = (diffInMills - y * year - mn * month) / day;
            long h = (diffInMills - y * year - mn * month - d * day) / hour;
            long m = (diffInMills - y * year - mn * month - d * day - h * hour) / min;
            long s = (diffInMills - y * year - mn * month - d * day - h * hour - m * min) / sec;
            return String.valueOf(y) + " [y] " + String.valueOf(mn) + " [m] " + String.valueOf(d) + " [d] " + String.valueOf(h) + " [h] " + String.valueOf(m) + " [min] " + String.valueOf(s) + " [s]";
        }
    }

    public static final String getDate(Date aDate) {
        SimpleDateFormat df = null;
        String returnValue = "";
        if (aDate != null) {
            df = new SimpleDateFormat(getDatePattern());
            returnValue = df.format(aDate);
        }
        df = null;
        return returnValue;
    }

    public static final String convertDateToString(Date aDate) {
        return getDateTime(getDatePattern(), aDate);
    }

    public static final String getDateTime(Date aDate) {
        SimpleDateFormat df = null;
        String returnValue = "";

        if (aDate != null) {
            df = new SimpleDateFormat(defaultDatePattern + " " + timePattern);
            returnValue = df.format(aDate);
        }
        return returnValue;
    }

    public static final String getDateTime(String aMask, Date aDate) {
        SimpleDateFormat df = null;
        String returnValue = "";
        if (aDate == null) {
            System.out.println("aDate is null!");
        } else {
            df = new SimpleDateFormat(aMask);
            returnValue = df.format(aDate);
        }
        return returnValue;
    }

    /**
     * 给日期字符串添时分秒（yyyy/MM/dd或者yyyy-MM-dd）
     * b=true时，添加"00:00:00",
     * b=false时,添加"23:59:59"
     *
     * @param dateStr
     * @param b
     * @return
     */
    public static final String appendHMS(String dateStr, boolean b) {
        if (dateStr.length() > 10) {
            return dateStr;
        }
        if (b) {
            return new StringBuffer(dateStr).append(" 00:00:00").toString();
        } else {
            return new StringBuffer(dateStr).append(" 23:59:59").toString();
        }
    }

    public static Date addMinutes(Date date, int amount) {
        return add(date, 12, amount);
    }

    public static Date add(Date date, int calendarField, int amount) {
        if (date == null) {
            throw new IllegalArgumentException("日期对象不允许为null!");
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(calendarField, amount);
        return c.getTime();
    }

    public static Date getCurrentDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(11, 0);
        cal.set(12, 0);
        cal.set(13, 0);
        cal.set(14, 0);
        return cal.getTime();
    }

    /**
     * 计算出目标时间与当前时间相隔的天数或者小时
     *
     * @param dateTime
     * @return
     */
    public static String getDateDistance(Date dateTime) {
        Date now = new Date();
        StringBuffer sb = new StringBuffer();
        long l = now.getTime() - dateTime.getTime();
        long day = l / (24 * 60 * 60 * 1000);
        long hour = l / (60 * 60 * 1000) - day * 24;
        long min = l / (60 * 1000) - day * 24 - hour * 60;
        long s = l / 1000 - day * 24 - hour * 60 - min * 60;
        if (day > 0) {
            sb.append(day + "天");
        } else {
            if (hour > 0) {
                sb.append(hour + "小时");
            } else if (min > 0) {
                sb.append(min + "分钟");
            }
        }
        sb.append("前");
        return sb.toString();
    }
}


class DurationFormater {
    public static final long MILISECONDS_PER_SECOND = 1000;
    public static final long SECONDS_PER_MINUTE = 60;
    public static final long MINUTES_PER_HOUR = 60;
    public static final long HOURS_PER_DAY = 24;

    public static final int MILISECOND = 0;
    public static final int SECOND = 1;
    public static final int MINUTE = 2;
    public static final int HOUR = 3;
    public static final int DAY = 4;

    public static final String PATTERNS[] = {"@ms", "@s", "@m", "@h", "@d"};
    private static final long[] AMOUNTS = {MILISECONDS_PER_SECOND, SECONDS_PER_MINUTE, MINUTES_PER_HOUR, HOURS_PER_DAY};
    private static long[] times = new long[5];
    private long time;
    private String pattern;
    private boolean detail = false;

    public DurationFormater() {
    }

    public DurationFormater(long time, String pattern) {
        this.time = time;
        this.pattern = pattern;
        update();
    }

    public DurationFormater(long time) {
        this.time = time;
        update();
    }

    private void update() {
        long remain = time;
        for (int i = 0; i < AMOUNTS.length; i++) {
            times[i] = remain % AMOUNTS[i];
            remain = remain / AMOUNTS[i];
        }
        times[DAY] = (int) remain;
    }

    /*
     * @h @M --> Month @m --> minute @ms --> milisecond @s --> second
     */
    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long duration) {
        time = duration;
        update();
    }

    public long getMiliseconds() {
        return times[MILISECOND];
    }

    public long getSeconds() {
        return times[SECOND];
    }

    public long getMinutes() {
        return times[MINUTE];
    }

    public long getHours() {
        return times[HOUR];
    }

    public long getDays() {
        return times[DAY];
    }

    public void setDetail(boolean detail) {
        this.detail = detail;
    }

    public String getString() {
        StringBuffer buffer = new StringBuffer(1024);
        buffer.append(pattern);
        for (int i = 0; i < PATTERNS.length; i++) {
            int start = -1;
            int end = -1;
            // Note, in JDK 1.3, StringBuffer does not have method indexOf
            while ((start = buffer.toString().indexOf(PATTERNS[i])) > -1) {
                end = start + PATTERNS[i].length();
                buffer.replace(start, end, String.valueOf(times[i]));
            }
        }
        return buffer.toString();
    }

    public String toString() {
        if (pattern != null) {
            return getString();
        }
        StringBuffer desc = new StringBuffer(256);
        if (times[DAY] > 0) {
            desc.append(checkPlural(times[DAY], "天"));
        }
        if (times[HOUR] > 0) {
            desc.append(checkPlural(times[HOUR], "小时"));
        }
        if ((times[MINUTE] > 0) || (times[DAY] == 0 && times[HOUR] == 0)) {
            desc.append(checkPlural(times[MINUTE], "分钟"));
        }
        if (times[SECOND] > 0 || (times[DAY] == 0 && times[HOUR] == 0 && times[HOUR] == 0)) {
            desc.append(checkPlural(times[SECOND], "秒"));
        }
        if (detail) {
            desc.append(checkPlural(times[MILISECOND], "毫秒"));
        }
        return desc.toString();
    }

    private static String checkPlural(long amount, String unit) {
        StringBuffer desc = new StringBuffer(20);
        if (amount > 0) {
            desc.append(amount).append(unit);
            //		if (amount > 1) {
            //			desc.append("s");
            //		}
        }
        return desc.toString();
    }

    /**
     * 将Date类型的日期转换为日期字符串
     *
     * @param date Date类型的日期
     * @param sf   格式化器(为NULL的话，默认格式化为：yyyyMMddHHmmss)
     * @return 日期字符串
     */
    public static String formatDate(Date date, SimpleDateFormat sf) {
        if (sf == null)
            sf = new SimpleDateFormat("yyyyMMddHHmmss");
        return date == null ? sf.format(new Date(System.currentTimeMillis())) : sf.format(date);
    }

}
