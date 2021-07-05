package zed.eureka.cli.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 时间工具类
 */
public  class DateUtil {
    private static Logger logger = (Logger) LoggerFactory.getLogger(DateUtil.class);

    /**
     * 日期格式
     */
    public interface DATE_PATTERN {
        String HHMMSS = "HHmmss";
        String HH_MM_SS = "HH:mm:ss";
        String HH_MM = "HH:mm";
        String YYYY = "yyyy";
        String YYYYMMDD = "yyyyMMdd";
        String YYYYMM = "yyyyMM";
        String YYYY_MM_DD = "yyyy-MM-dd";
        String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
        String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";
        String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    }

    /**
     * 获取当前时间
     *
     * @return Timestamp对象
     */
    public static Timestamp getCurrontTime() {
        Timestamp sqlTimestamp = new Timestamp(new Date().getTime());
        return sqlTimestamp;
    }

    /**
     * 将Date类型转换成String类型
     *
     * @param date Date对象
     * @return 形如:"yyyy-MM-dd HH:mm:ss"
     */
    public static String date2String(Date date) {
        return date2String(date, DATE_PATTERN.YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 将Date按格式转化成String
     *
     * @param date  Date对象
     * @param pattern 日期类型
     * @return String
     */
    public static String date2String(Date date, String pattern) {
        if (date == null || pattern == null) {
            return null;
        }
        return new SimpleDateFormat(pattern).format(date);
    }

    /**
     * 将String类型转换成Date类型
     *
     * @param date Date对象
     * @return
     */
    public static Date string2Date(String date) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_PATTERN.YYYY_MM_DD_HH_MM_SS);
        try {
            return format.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取某日期N天后的日期
     *
     * @param datestr
     * @param day
     * @return
     */
    public static Date getBeforeAfterDate(String datestr, int day) {
        SimpleDateFormat df = new SimpleDateFormat(DATE_PATTERN.YYYY_MM_DD_HH_MM_SS);
        java.sql.Date olddate = null;
        try {
            df.setLenient(false);
            olddate = new java.sql.Date(df.parse(datestr).getTime());
        } catch (ParseException e) {
            throw new RuntimeException("日期转换错误");
        }
        Calendar cal = new GregorianCalendar();
        cal.setTime(olddate);

        int Year = cal.get(Calendar.YEAR);
        int Month = cal.get(Calendar.MONTH);
        int Day = cal.get(Calendar.DAY_OF_MONTH);

        int NewDay = Day + day;

        cal.set(Calendar.YEAR, Year);
        cal.set(Calendar.MONTH, Month);
        cal.set(Calendar.DAY_OF_MONTH, NewDay);

        return new Date(cal.getTimeInMillis());
    }


    /**
     * @return
     * @Description: 获取当前日期的前一天
     * @ReturnType String
     * @author: liyl
     * @Created 2015年11月13日 下午5:11:14
     */
    public static Date currentBeforeDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        return calendar.getTime();
    }

    /**
     * @return
     * @Description: 获取当前日期的后一天
     * @ReturnType Date
     * @author: liyl
     * @Created 2015年11月13日 下午5:14:54
     */
    public static Date currentNextDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    /**
     * 获取指定日期星期几(int)
     *
     * @param dt
     * @return
     */
    public static int getWeekOfInt(Date dt) {
        int[] weekDays = {7, 1, 2, 3, 4, 5, 6};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekDays[w];
    }

    /**
     * 获取指定日期星期几
     *
     * @param dt
     * @return
     */
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekDays[w];
    }

    /**
     * 时间比大小
     *
     * @param DATE1
     * @param DATE2
     * @param pattern
     * @return
     */
    public static int compareDate(String DATE1, String DATE2, String pattern) {

        DateFormat df = new SimpleDateFormat(pattern);
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                System.out.println("dt1 在dt2前");
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                System.out.println("dt1在dt2后");
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    /**
     * 在一个时间上加上或减去分钟
     *
     * @param date long
     * @param i  int
     * @return Date
     */
    public static Date addOrMinusMinutes(Date date, int i) {
        Date rtn = null;
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(GregorianCalendar.MINUTE, i);
        rtn = cal.getTime();
        return rtn;
    }

    /**
     * 按照指定格式返回格式好的当前日期
     *
     * @param dateFormat 默认yyyy-MM-dd
     * @return
     */
    public static String getCurrentDateString(String dateFormat) {
        return DateUtil.format(new Date(), DATE_PATTERN.YYYY_MM_DD);
    }

    /**
     * 说明 将日期格式化字符串，为null的返回空字符串
     *
     * @param date
     * @return
     */
    public static String format(Date date) {
        if (null == date) return "";
        SimpleDateFormat sf = new SimpleDateFormat(DATE_PATTERN.YYYY_MM_DD);
        return sf.format(date);
    }

    /**
     * 说明 将日期格式化字符串，为null的返回空字符串
     *
     * @param date    日期
     * @param dateFormat 格式化字符串，比如：yyyy-MM-dd
     * @return
     */
    public static String format(Date date, String dateFormat) {
        if (null == dateFormat || "".equals(dateFormat)) return DateUtil.format(date);
        if (null == date) return "";
        SimpleDateFormat sf = new SimpleDateFormat(dateFormat);
        return sf.format(date);
    }

    /**
     * @param source 要进行解析的源字符串
     * @return
     * @说明 将指定的字符串格解析成日期类型，格式默认为：yyyy-MM-dd
     */
    public static Date parase(String source) {
        SimpleDateFormat sf = new SimpleDateFormat(DATE_PATTERN.YYYY_MM_DD);
        try {
            return sf.parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param source   要进行解析的源字符串
     * @param dateFormat 要解析的日期格式。
     * @return
     * @说明 将指定的字符串格解析成日期类型 例：如果日期source=20131210,则dateFormat应为：yyyyMMdd,两个应对应
     */
    public static Date parase(String source, String dateFormat) {
        SimpleDateFormat sf = new SimpleDateFormat(dateFormat);
        try {
            return sf.parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param date
     * @param days
     * @说明 对指定的日期增加或减少指定的天数
     */
    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, days);
        return cal.getTime();
    }

    /**
     * @param date
     * @param days
     * @说明 对指定的日期增加或减少指定的天数
     */
    public static Calendar addDays(Calendar date, int days) {
        date.add(Calendar.DAY_OF_MONTH, days);
        return date;
    }

    /**
     * @param date
     * @param months
     * @return
     * @说明 对指定的日期增加或减少指定的月数
     */
    public static Date addMonths(Date date, int months) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, months);
        return cal.getTime();
    }

    /**
     * @param date
     * @param months
     * @return
     * @说明 对指定的日期增加或减少指定的月数
     */
    public static Calendar addMonths(Calendar date, int months) {
        date.add(Calendar.MONTH, months);
        return date;
    }

    /**
     * @param date
     * @param hours
     * @return
     * @说明 对指定的日期增加或减少指定的小时数
     */
    public static Date addHours(Date date, int hours) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR_OF_DAY, hours);
        return cal.getTime();
    }

    /**
     * 对指定的日期增加或减少指定的小时数
     *
     * @param date
     * @param hours
     * @return
     */
    public static Calendar addHours(Calendar date, int hours) {
        date.add(Calendar.HOUR_OF_DAY, hours);
        return date;
    }

    /**
     * 以字符串形式返回当前时间的毫秒数
     *
     * @return
     */
    public static String getTimeMillions() {
        Calendar cal = Calendar.getInstance();
        long lt = cal.getTimeInMillis();
        return String.valueOf(lt);
    }

    /**
     * 获取当前月的第一天
     *
     * @return 当前月的第一天
     */
    public static String getMonthFirstDay() {
        SimpleDateFormat df = new SimpleDateFormat(DATE_PATTERN.YYYY_MM_DD);
        Calendar calendar = Calendar.getInstance();
        Date theDate = calendar.getTime();
        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
        gcLast.setTime(theDate);
        gcLast.set(Calendar.DAY_OF_MONTH, 1);
        String day_first = df.format(gcLast.getTime());
        StringBuffer str = new StringBuffer().append(day_first);
        return str.toString();

    }

    /**
     * 获取当前月的最后一天
     *
     * @return 当前月的最后一天
     */
    public static String getMonthLastDay() {
        Calendar calendar = Calendar.getInstance();
        // 最后一天
        int maxday = calendar.getActualMaximum(Calendar.DATE);
        calendar.set(Calendar.DATE, maxday);
        SimpleDateFormat df = new SimpleDateFormat(DATE_PATTERN.YYYY_MM_DD);
        Date theDate = calendar.getTime();
        String s = df.format(theDate);
        StringBuffer str = new StringBuffer().append(s);
        return str.toString();

    }

    /**
     * 获取当前月的第一天，精确到时分秒
     *
     * @return 当前月的第一天，精确到时分秒
     */
    public static Date getFirstDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date date = calendar.getTime();
        return date;
    }

    /**
     * 获得往数据库字段类型为Date型时，插入的时间
     *
     * @param date    默认为当前日期，如果为空时 方法会自动new Date()
     * @param dateFormat 默认为yyyy-MM-dd
     * @return
     */
    public static java.sql.Date paraseSqlDate(String date, String dateFormat) {
        try {
            if (date == null || date.length() == 0) {
                return new java.sql.Date(new Date().getTime());
            } else {
                if (dateFormat == null) dateFormat = DateUtil.DATE_PATTERN.YYYY_MM_DD;
                SimpleDateFormat sf = new SimpleDateFormat(dateFormat);
                Date d = sf.parse(date);
                return new java.sql.Date(d.getTime());
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }

    /**
     * 将日期按照特定格式转换成字符串
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String formatString(Date date, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }

    /**
     * 将日期字符串转换为日期
     *
     * @param strDate
     * @param mask
     * @return
     * @throws ParseException
     */
    public static Timestamp convertStringToTimestamp(String strDate, String mask) throws ParseException {
        SimpleDateFormat df;
        Date date = null;
        df = new SimpleDateFormat(mask);
        try {
            date = df.parse(strDate);
            return new Timestamp(date.getTime());
        } catch (ParseException pe) {
            throw new ParseException(pe.getMessage(), pe.getErrorOffset());
        }
    }

    /**
     * 月份相加 add by yuanjq
     *
     * @param timest1
     * @param month
     * @return
     */
    public static Timestamp DateAddMonth(Timestamp timest1, int month) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(timest1);
        cal.add(Calendar.MONTH, month);
        return new Timestamp(cal.getTimeInMillis());
    }

    /**
     * 对输入的日期进行格式化, 如果输入的日期是null则返回空串.
     * FrameWork使用
     *
     * @param dtDate   java.sql.Timestamp 需要进行格式化的日期字符串
     * @param strFormatTo String 要转换的日期格式
     * @return String 经过格式化后的字符串
     */
    public static String getFormattedDate(Timestamp dtDate,
                                          String strFormatTo) {
        if (dtDate == null) {
            return "";
        }
        if (dtDate.equals(new Timestamp(0))) {
            return "";
        }
        String newStrFormateTo = strFormatTo;
        newStrFormateTo = newStrFormateTo.replace('/', '-');
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        if (Integer.parseInt(formatter.format(dtDate)) < 1900) {
            return "";
        } else {
            formatter = new SimpleDateFormat(newStrFormateTo);
            return formatter.format(dtDate);
        }
    }

    /**
     * 获取当前时间年月日
     *
     * @return
     */
    public static String getCurrentDateYMR() {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN.YYYYMMDD);
        return sdf.format(System.currentTimeMillis());
    }

    /**
     * 根据字符串以及格式化方式获取date对象
     *
     * @param strDate
     * @param strFormat
     * @return
     * @throws ParseException
     */
    public static Date getDate(String strDate, String strFormat) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(strFormat);
        Date date = format.parse(strDate);
        return date;
    }

    /**
     * 根据字符串以及格式化方式获取时间戳
     *
     * @param strDate
     * @param strFormat
     * @return
     * @throws ParseException
     */
    public static Timestamp getTimestamp(String strDate, String strFormat) throws ParseException {
        Date date = getDate(strDate, strFormat);
        Timestamp timestamp = new Timestamp(date.getTime());

        return timestamp;
    }

    /**
     * 根据Date获取格式化后的字符串
     *
     * @param date
     * @param strFormat
     * @return
     * @throws ParseException
     */
    public static String getStringDate(Date date, String strFormat) throws ParseException {
        if (date == null) {
            return "";
        }
        SimpleDateFormat format = new SimpleDateFormat(strFormat);
        String strDate = format.format(date);
        return strDate;
    }

    /**
     * 根据时间戳格式化时间
     *
     * @param timestamp
     * @param strFormat
     * @return
     * @throws ParseException
     */
    public static String getStringTimestamp(Timestamp timestamp, String strFormat) throws ParseException {
        if (timestamp == null) {
            return "";
        }
        String strTimestamp = getStringDate((Date) timestamp, strFormat);
        return strTimestamp;
    }


    /**
     * 根据时间戳偏移几个月
     *
     * @param timestamp
     * @param months
     * @return
     * @throws ParseException
     */
    public static Timestamp addMonth(Timestamp timestamp, int months) throws ParseException {
        GregorianCalendar grc = new GregorianCalendar();
        grc.setTime((Date) timestamp);
        grc.add(GregorianCalendar.MONTH, months);
        return new Timestamp(grc.getTime().getTime());
    }

    /**
     * 根据时间戳偏移几年
     *
     * @param timestamp
     * @param years
     * @return
     * @throws ParseException
     */
    public static Timestamp addYear(Timestamp timestamp, int years) throws ParseException {
        GregorianCalendar grc = new GregorianCalendar();
        grc.setTime((Date) timestamp);
        grc.add(GregorianCalendar.YEAR, years);
        return new Timestamp(grc.getTime().getTime());
    }

    /**
     * 根据时间戳偏移几天
     *
     * @param timestamp
     * @param days
     * @return
     * @throws ParseException
     */
    public static Timestamp addDay(Timestamp timestamp, int days) throws ParseException {
        GregorianCalendar grc = new GregorianCalendar();
        grc.setTime((Date) timestamp);
        grc.add(GregorianCalendar.DAY_OF_MONTH, days);
        return new Timestamp(grc.getTime().getTime());
    }

    /**
     * 根据时间戳偏移几小时
     *
     * @param timestamp
     * @param hours
     * @return
     * @throws ParseException
     */
    public static Timestamp addHour(Timestamp timestamp, int hours) throws ParseException {
        GregorianCalendar grc = new GregorianCalendar();
        grc.setTime((Date) timestamp);
        grc.add(GregorianCalendar.HOUR_OF_DAY, hours);
        return new Timestamp(grc.getTime().getTime());
    }

    /**
     * 根据时间戳偏移几分钟
     *
     * @param timestamp
     * @param minutes
     * @return
     * @throws ParseException
     */
    public static Timestamp addMinute(Timestamp timestamp, int minutes) throws ParseException {
        GregorianCalendar grc = new GregorianCalendar();
        grc.setTime((Date) timestamp);
        grc.add(GregorianCalendar.MINUTE, minutes);
        return new Timestamp(grc.getTime().getTime());
    }

    /**
     * 根据时间戳偏移几秒钟
     *
     * @param timestamp
     * @param seconds
     * @return
     * @throws ParseException
     */
    public static Timestamp addSecond(Timestamp timestamp, int seconds) throws ParseException {
        GregorianCalendar grc = new GregorianCalendar();
        grc.setTime((Date) timestamp);
        grc.add(GregorianCalendar.SECOND, seconds);
        return new Timestamp(grc.getTime().getTime());
    }

    /**
     * 根据时间的毫秒值格式化时间
     *
     * @param time
     * @param strFormat
     * @return
     * @throws ParseException
     */
    public static String getTime(String time, String strFormat) throws ParseException {
        Timestamp endLogDateFormated = getTimestamp(time, strFormat);
        String sTime = getStringTimestamp(endLogDateFormated, DATE_PATTERN.YYYYMMDD);
        return sTime;
    }

    /**
     * 转换时间格式化方式
     *
     * @param time
     * @param strFormat
     * @return
     * @throws ParseException
     */
    public static String getTimeNew(String time, String strFormat) throws ParseException {
        Timestamp endLogDateFormated = getTimestamp(time, strFormat);
        String sTime = getStringTimestamp(endLogDateFormated, DATE_PATTERN.YYYY_MM_DD_HH_MM_SS);
        return sTime;
    }

    /**
     * 根据传入的日期字符串转换成相应的日期对象，
     * 如果字符串为空或不符合日期格式，则返回当前时间。
     * FrameWork使用
     *
     * @param strDate String 日期字符串
     * @return java.sql.Timestamp 日期对象
     */
    public static Timestamp getDateByString(String strDate) {
        if (strDate.trim().equals("")) {
            return getCurrentDate();
        }
        try {
            strDate = getFormattedDate(strDate, DATE_PATTERN.YYYY_MM_DD_HH_MM_SS) + ".000000000";
            return Timestamp.valueOf(strDate);
        } catch (Exception ex) {
            return getCurrentDate();
        }
    }

    /**
     * 获取当前数据库时间
     *
     * @return
     */
    public static Timestamp getCurrentDate() {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(DATE_PATTERN.YYYY_MM_DD_HH_MM_SS + ".0");
            return Timestamp.valueOf(formatter.format(new Date()));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 对输入的日期字符串进行格式化,
     * 如果输入的是0000/00/00 00:00:00则返回空串.
     * FrameWork使用
     *
     * @param strDate   String 需要进行格式化的日期字符串
     * @param strFormatTo String 要转换的日期格式
     * @return String 经过格式化后的字符串
     */
    public static String getFormattedDate(String strDate, String strFormatTo) {
        if ((strDate == null) || strDate.trim().equals("")) {
            return "";
        }
        strDate = strDate.replace('/', '-');
        strFormatTo = strFormatTo.replace('/', '-');
        if (strDate.equals("0000-00-00 00:00:00") ||
                strDate.equals("1800-01-01 00:00:00")) {
            return "";
        }
        String formatStr = strFormatTo; //"yyyyMMdd";
        if (strDate.trim().equals("")) { //(strDate == null) ||
            return "";
        }
        switch (strDate.trim().length()) {
            case 6:
                if (strDate.substring(0, 1).equals("0")) {
                    formatStr = "yyMMdd";
                } else {
                    formatStr = "yyyyMM";
                }
                break;
            case 8:
                formatStr = "yyyyMMdd";
                break;
            case 10:
                if (strDate.indexOf("-") == -1) {
                    formatStr = "yyyy/MM/dd";
                } else {
                    formatStr = "yyyy-MM-dd";
                }
                break;
            case 11:
                if (strDate.getBytes().length == 14) {
                    formatStr = "yyyy年MM月dd日";
                } else {
                    return "";
                }
                break;
            case 14:
                formatStr = "yyyyMMddHHmmss";
                break;
            case 19:
                if (strDate.indexOf("-") == -1) {
                    formatStr = "yyyy/MM/dd HH:mm:ss";
                } else {
                    formatStr = "yyyy-MM-dd HH:mm:ss";
                }
                break;
            case 21:
                if (strDate.indexOf("-") == -1) {
                    formatStr = "yyyy/MM/dd HH:mm:ss.S";
                } else {
                    formatStr = "yyyy-MM-dd HH:mm:ss.S";
                }
                break;
            default:
                return strDate.trim();
        }
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(formatStr);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(formatter.parse(strDate));
            formatter = new SimpleDateFormat(strFormatTo);
            return formatter.format(calendar.getTime());
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 处理微信日期
     *
     * @param date
     * @return
     */
    public static String dealWechatDate(String date) {
        String result;
        if (date == null || "".equals(date)) {
            result = date;
        } else if (date.indexOf(".") > -1) {
            result = date.replace(".", "-");
        } else if (date.indexOf("年") > -1) {
            result = date.replace("年", "-").replace("月", "-").replace("日", "");
        } else if (date.indexOf("-") > -1) {
            result = date.replace("年", "-").replace("月", "-").replace("日", "");
        } else {
            result = date;
        }
        return result;
    }

    /**
     * 获取两个日期相差的月数
     *
     * @param d1 较大的日期
     * @param d2 较小的日期
     * @return 如果d1>d2返回 月数差 否则返回0
     */
    public static int monthsBetween(Date d1, Date d2) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(d1);
        c2.setTime(d2);
        if (c1.getTimeInMillis() < c2.getTimeInMillis()) return 0;
        int year1 = c1.get(Calendar.YEAR);
        int year2 = c2.get(Calendar.YEAR);
        int month1 = c1.get(Calendar.MONTH);
        int month2 = c2.get(Calendar.MONTH);
        int day1 = c1.get(Calendar.DAY_OF_MONTH);
        int day2 = c2.get(Calendar.DAY_OF_MONTH);
        // 获取年的差值 假设 d1 = 2015-8-16 d2 = 2011-9-30
        int yearInterval = year1 - year2;
        // 如果 d1的 月-日 小于 d2的 月-日 那么 yearInterval-- 这样就得到了相差的年数
        if (month1 < month2 || month1 == month2 && day1 < day2) yearInterval--;
        // 获取月数差值
        int monthInterval = (month1 + 12) - month2;
        if (day1 < day2) monthInterval--;
        monthInterval %= 12;
        return yearInterval * 12 + monthInterval;
    }

    /**
     * 计算date2 - date1之间相差的天数
     *
     * @param date1
     * @param date2
     * @return 如果d1>d2返回 月数差 否则返回0
     */
    public static int daysBetween(Date date1, Date date2) {
        DateFormat sdf = new SimpleDateFormat(DATE_PATTERN.YYYYMMDD);
        Calendar cal = Calendar.getInstance();
        try {
            Date d1 = sdf.parse(date2String(date1, DATE_PATTERN.YYYYMMDD));
            Date d2 = sdf.parse(date2String(date2, DATE_PATTERN.YYYYMMDD));
            cal.setTime(d1);
            long time1 = cal.getTimeInMillis();
            cal.setTime(d2);
            long time2 = cal.getTimeInMillis();
            return Integer.parseInt(String.valueOf((time2 - time1) / 86400000L));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 计算date2 - date1之间相差的分钟
     *
     * @param date1
     * @param date2
     * @return
     */
    @SuppressWarnings("deprecation")
    public static int minutesBetween(Date date1, Date date2) {
        Calendar cal = Calendar.getInstance();
        // date1.setSeconds(0);
        cal.setTime(date1);
        long time1 = cal.getTimeInMillis();
        cal.setTime(date2);
        long time2 = cal.getTimeInMillis();
        if (time2 - time1 <= 0) {
            return 0;
        } else {
            return Integer.parseInt(String.valueOf((time2 - time1) / 60000L)) + 1;
        }
    }

    /**
     * 计算date2 - date1之间相差的秒
     *
     * @param date1
     * @param date2
     * @return
     */
    @SuppressWarnings("deprecation")
    public static int secondBetween(Date date1, Date date2) {
        Calendar cal = Calendar.getInstance();
        // date1.setSeconds(0);
        cal.setTime(date1);
        long time1 = cal.getTimeInMillis();
        cal.setTime(date2);
        long time2 = cal.getTimeInMillis();
        if (time2 - time1 <= 0) {
            return 0;
        } else {
            return Integer.parseInt(String.valueOf((time2 - time1) / 1000L)) + 1;
        }
    }

    /**
     * 计算date2 - date1之间相差的毫秒
     *
     * @param date1
     * @param date2
     * @return
     */
    @SuppressWarnings("deprecation")
    public static int millisecondBetween(Date date1, Date date2) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date1);
        long time1 = cal.getTimeInMillis();
        cal.setTime(date2);
        long time2 = cal.getTimeInMillis();
        if (time2 - time1 <= 0) {
            return 0;
        } else {
            return Integer.parseInt(String.valueOf((time2 - time1)));
        }
    }

}
