package com.abc12366.gateway.util;

import com.abc12366.gateway.exception.ServiceException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 日期工具类
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-05-03 10:57 AM
 * @since 1.0.0
 */
public class DateUtils {

    public static String getDateFormat(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 添加年份
     *
     * @param date 需要添加年份的日期
     * @param year 需要添加的年份数
     * @return Date 添加年份数之后的日期
     */
    public static Date addYears(Date date, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, year);
        return calendar.getTime();
    }

    /**
     * 添加天
     *
     * @param date 需要添加天的日期
     * @param day  需要添加的天数
     * @return Date 添加天数之后的日期
     */
    public static Date addDays(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, day);
        return calendar.getTime();
    }

    /**
     * 添加小时
     *
     * @param date 需要添加小时的日期
     * @param hour 需要添加的小时数
     * @return Date 添加小时数之后的日期
     */
    public static Date addHours(Date date, int hour) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, hour);
        return calendar.getTime();
    }

    /**
     * 当前年月日字符串
     *
     * @return yyyyMMdd
     */
    public static String getDataString() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        return df.format(new Date());
    }

    /**
     * 获取当前时间戳
     *
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String getDataLong(int datalong, int i) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long time = datalong + i * 1000;
        return format.format(time);
    }

    /**
     * 字符串转换成日期
     *
     * @param str
     * @return date
     */
    public static Date strToDate(String str) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new ServiceException(4805);
        }
        return date;
    }

    /**
     * 时间戳转时间
     * @param millSec
     * @return
     */
    public static Date transferLongToDate(Long millSec) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(millSec);
        return date;
    }

    /**
     * 字符串转换成日期
     *
     * @param str
     * @return date
     */
    public static Date strToDateHHMMSS(String str) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new ServiceException(4805);
        }
        return date;
    }

    /**
     * 字符串转换成日期，yyyy-MM
     *
     * @param str
     * @return date
     */
    public static Date strToDateMonth(String str) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Date date = null;
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 字符串转换成日期
     *
     * @param str 日期字符串
     * @param format 格式
     * @return date
     */
    public static Date strToDate(String str, String format) {

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new ServiceException(4805);
        }
        return date;
    }

    public static String dateToStr(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    public static String dateToString(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    public static String dateToString(Date date,String str) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat(str);
        return format.format(date);
    }

    /**
     * 时间转String，yyyy-MM
     * @param date
     * @return
     */
    public static String dateToMonth(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        return format.format(date);
    }

    /**
     * 获取日期年份
     */
    public static String dateYearToString(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        return format.format(date);
    }

    /**
     * 获取当月最早时间
     *
     * @return
     */
    public static Date getFirstDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);

        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    /**
     * 获取次月最早时间
     *
     * @return
     */
    public static Date getFirstDayOfLastMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);

        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    /**
     * 获取当天最早时间
     *
     * @return
     */
    public static Date getFirstHourOfDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);

        return calendar.getTime();
    }

    /**
     * 获取次日最早时间
     *
     * @return
     */
    public static Date getFirstHourOfLastDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);

        calendar.add(Calendar.DAY_OF_YEAR, 1);
        return calendar.getTime();
    }

    /**
     * 获取当年最早时间
     *
     * @return
     */
    public static Date getFirstMonthOfYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);

        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    /**
     * 获取次年最早时间
     *
     * @return
     */
    public static Date getFirstMonthOfLastYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);

        calendar.add(Calendar.YEAR, 1);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    /**
     * 获取当前时间（+/-）天数
     */
    public static String getDateToYYYYMMDD(int num){
        SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
        Date today = new Date();

        Calendar c = Calendar.getInstance();
        c.setTime(today);
        c.add(Calendar.DAY_OF_MONTH, num);

        Date tomorrow = c.getTime();
        System.out.println("明天" + f.format(tomorrow));

        return f.format(tomorrow);
    }
    /**
     * yyyyMMddHHmmssSSS+三位随机数
     *
     * @return
     */
    public static String getDateToString() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        StringBuffer buffer = new StringBuffer();
        buffer.append("DD");
        buffer.append(df.format(new Date()));
        buffer.append(getRandom());
        return buffer.toString();
    }

    /**
     * yyyyMMddHHmmssSSS+三位随机数
     *
     * @return
     */
    public static String getFPQQLSH() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        StringBuffer buffer = new StringBuffer();
        buffer.append(df.format(new Date()));
        buffer.append(getRandom());
        return buffer.toString();
    }
    /**
     * yyyyMMddHHmmssSSS+三位随机数
     *
     * @return
     */
    public static String getJYLSH() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        StringBuffer buffer = new StringBuffer();
        buffer.append("JYLSH");
        buffer.append(df.format(new Date()));
        buffer.append(getRandom());
        return buffer.toString();
    }

    /**
     * 获取用户订单号
     *
     * @return
     */
    public static String getGiftIdString() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        StringBuffer buffer = new StringBuffer();
        buffer.append("LW");
        buffer.append(df.format(new Date()));
        //buffer.append(getRandom());
        return buffer.toString();
    }

    /**
     * 获取用户订单号
     *
     * @return
     */
    public static String getInvoiceOrderString() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        StringBuffer buffer = new StringBuffer();
        buffer.append("FPDDH");
        buffer.append(df.format(new Date()));
        buffer.append(getRandom());
        return buffer.toString();
    }

    /**
     * 返回一个三位随机数的字符串
     *
     * @return
     */
    public static String getRandom() {
        int number = 0;
        while (true) {
            number = (int) (Math.random() * 1000);
            if (number >= 100 && number < 1000) {
                break;
            }
        }
        return String.valueOf(number);
    }

    /**
     * 日期往后减去num天
     *
     * @param num
     * @return
     */
    public static Date getAddDate(int num) {
        // 取时间
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        // 把日期往后增加一天.整数往后推,负数往前移动
        calendar.add(Calendar.DATE, -num);
        // 这个时间就是日期往后推一天的结果
        date = calendar.getTime();
        return date;
    }

    /**
     * 日期往后减去num月
     *
     * @param num
     * @return
     */
    public static Date getAddMonth(int num) {
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -num);
        date = calendar.getTime();
        return date;
    }

    /**
     * 日期往后减去num月
     * @param num
     * @return
     */
    public static Date getAddMonth(Date date,int num) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -num);
        date = calendar.getTime();
        return date;
    }

    /**
     * 日期往后减去num年
     *
     * @param num
     * @return
     */
    public static Date getAddYear(int num) {
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        // 把日期往后增加一年.整数往后推,负数往前移动
        calendar.add(Calendar.YEAR, -num);
        // 这个时间就是日期往后推一年的结果
        date = calendar.getTime();
        return date;
    }

    /**
     * 日期往后减num个小时
     *
     * @param num
     * @return
     */
    public static Date getAddTime(int num) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.HOUR_OF_DAY, -num);
        System.out.println(sdf.format(ca.getTime()));
        return ca.getTime();
    }

    /**
     * 时间是否在时间段里
     *
     * @param start
     * @param end
     * @param date
     * @return
     */
    public static boolean dateIn(Date start, Date end, Date date) {
        if (start == null || end == null) {
            return true;
        }
        if (date == null) {
            return false;
        }
        if (date.getTime() >= start.getTime() && date.getTime() <= end.getTime()) {
            return true;
        }
        return false;
    }

    /**
     * 根据时间获取时间毫秒
     *
     * @param date
     * @return long 返回毫秒数
     */
    public static long getDateStringToLong(Date date) {
        Date temp = null;
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        String inVal = inputFormat.format(date);
        try {
            // 将字符型转换成日期型
            temp = inputFormat.parse(inVal);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp.getTime();
    }

    /**
     * 将时间戳转换为时间
     *
     * @param lt 毫秒数
     * @return Date 日期类型
     */
    public static Date getLongToDate(long lt) {
        return new Date(lt);
    }

    /**
     * 获取当前系统当天日期String
     *
     * @return String
     */
    public static String getTodaySting() {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    /**
     * 获得当前时间yyyy-MM-dd
     *
     * @return String
     */
    public static Date getToday() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String s = sdf.format(new Date());
        Date date = null;
        try {
            date = sdf.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 获取当前系统当天日期
     *
     * @param date Date
     * @return Date
     */
    public static Date getToday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, 0);
        date = calendar.getTime();
        return date;
    }

    /**
     * 获取当前系统前一天日期
     *
     * @param date Date
     * @return Date
     */
    public static Date getYesterDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        date = calendar.getTime();
        return date;
    }

    /**
     * 获取当前系统下一天日期
     *
     * @param date Date
     * @return Date
     */
    public static Date getNextDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        date = calendar.getTime();
        return date;
    }
    /**
     *给日期 加上 " 23:59:59";
     */
    public static Date put23h23m59s(String endTime){
        if(endTime == null)return null;
        if(endTime != null && !endTime.isEmpty())     {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date eTime = sdf.parse(endTime);
                endTime = sdf.format(eTime) + " 23:59:59";
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                eTime = sdf2.parse(endTime);
                return eTime;
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    /**
     * 通过时间秒毫秒数判断两个时间的间隔
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDaysByMillisecond(Date date1,Date date2)
    {
        int days = (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));
        return days;
    }


    /**
     *
     * @param minDate 最小时间  2015-01
     * @param maxDate 最大时间 2015-10
     * @return 日期集合 格式为 年-月
     * @throws Exception
     */
    public static List<Date> getMonthBetween(String minDate, String maxDate){
        ArrayList<Date> result = new ArrayList<Date>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");//格式化为年月

        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();
        try {
            min.setTime(sdf.parse(minDate));
            min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

            max.setTime(sdf.parse(maxDate));
            max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar curr = min;
        while (curr.before(max)) {
            getLastDayOfMonth(curr.getTime(), "yyyy-MM-dd");
            result.add(getLastDayOfMonth(curr.getTime(), "yyyy-MM-dd"));
            curr.add(Calendar.MONTH, 1);
        }

        return result;
    }

    /**
     * 获取指定日期月份的最后一天，patter为返回的日期的字符串格式
     * @param date
     * @param patter
     * @return
     */
    public static Date getLastDayOfMonth(Date date,String patter) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.roll(Calendar.DAY_OF_MONTH, -1);
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat(patter);
        return cal.getTime();
    }

    /**
     * 获取某段时间内的所有日期
     * @param dBegin
     * @param dEnd
     * @return
     */
    public static List<Date> findDates(Date dBegin, Date dEnd) {
        List lDate = new ArrayList();
        lDate.add(dBegin);
        Calendar calBegin = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calBegin.setTime(dBegin);
        Calendar calEnd = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calEnd.setTime(dEnd);
        // 测试此日期是否在指定日期之后
        while (dEnd.after(calBegin.getTime())) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            lDate.add(calBegin.getTime());
        }
        return lDate;
    }

    /**
     * 获取具体时间的下个月最早时间
     * @param date 具体时间
     * @return Date
     */
    public static Date getNextMonthFirstByCertianDate(Date date){
        if(date==null){
            return getNextMonthFirstByCertianDate(new Date());
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH,1);
        calendar.set(Calendar.DAY_OF_MONTH,1);
        calendar.set(Calendar.HOUR,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        return calendar.getTime();
    }

    /**
     * 计算两个时间的毫秒差
     * @param date1
     * @param date2
     * @return long
     */
    public static long milliSecondsBetween(Date date1,Date date2)
    {
        return date2.getTime() - date1.getTime();
    }

}
