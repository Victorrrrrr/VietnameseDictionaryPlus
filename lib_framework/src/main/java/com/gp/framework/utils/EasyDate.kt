package com.gp.framework.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.GregorianCalendar
import java.util.Locale
import java.util.Objects


/**
 * EasyDate
 *
 * @author llw
 * @date 2021/07/01
 */
object EasyDate {
    const val STANDARD_TIME = "yyyy-MM-dd HH:mm:ss"
    const val FULL_TIME = "yyyy-MM-dd HH:mm:ss.SSS"
    const val YEAR_MONTH_DAY = "yyyy-MM-dd"
    const val YEAR_MONTH_DAY_CN = "yyyy年MM月dd号"
    const val HOUR_MINUTE_SECOND = "HH:mm:ss"
    const val HOUR_MINUTE_SECOND_CN = "HH时mm分ss秒"
    const val YEAR = "yyyy"
    const val MONTH = "MM"
    const val DAY = "dd"
    const val HOUR = "HH"
    const val MINUTE = "mm"
    const val SECOND = "ss"
    const val MILLISECOND = "SSS"
    const val YESTERDAY = "昨天"
    const val TODAY = "今天"
    const val TOMORROW = "明天"
    const val SUNDAY = "星期日"
    const val MONDAY = "星期一"
    const val TUESDAY = "星期二"
    const val WEDNESDAY = "星期三"
    const val THURSDAY = "星期四"
    const val FRIDAY = "星期五"
    const val SATURDAY = "星期六"
    val weekDays = arrayOf(SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY)
    val dateTime: String
        /**
         * 获取标准时间
         *
         * @return 例如 2021-07-01 10:35:53
         */
        get() = SimpleDateFormat(STANDARD_TIME, Locale.CHINESE).format(Date())
    val fullDateTime: String
        /**
         * 获取完整时间
         *
         * @return 例如 2021-07-01 10:37:00.748
         */
        get() = SimpleDateFormat(FULL_TIME, Locale.CHINESE).format(Date())
    val theYearMonthAndDay: String
        /**
         * 获取年月日(今天)
         *
         * @return 例如 2021-07-01
         */
        get() = SimpleDateFormat(YEAR_MONTH_DAY, Locale.CHINESE).format(Date())
    val theYearMonthAndDayCn: String
        /**
         * 获取年月日
         *
         * @return 例如 2021年07月01号
         */
        get() = SimpleDateFormat(YEAR_MONTH_DAY_CN, Locale.CHINESE).format(Date())

    /**
     * 获取年月日
     * @param delimiter 分隔符
     * @return 例如 2021年07月01号
     */
    fun getTheYearMonthAndDayDelimiter(delimiter: CharSequence): String {
        return SimpleDateFormat(YEAR + delimiter + MONTH + delimiter + DAY, Locale.CHINESE).format(
            Date()
        )
    }

    val hoursMinutesAndSeconds: String
        /**
         * 获取时分秒
         *
         * @return 例如 10:38:25
         */
        get() = SimpleDateFormat(HOUR_MINUTE_SECOND, Locale.CHINESE).format(Date())
    val hoursMinutesAndSecondsCn: String
        /**
         * 获取时分秒
         *
         * @return 例如 10时38分50秒
         */
        get() = SimpleDateFormat(HOUR_MINUTE_SECOND_CN, Locale.CHINESE).format(Date())

    /**
     * 获取时分秒
     * @param delimiter 分隔符
     * @return 例如 2021/07/01
     */
    fun getHoursMinutesAndSecondsDelimiter(delimiter: CharSequence): String {
        return SimpleDateFormat(
            HOUR + delimiter + MINUTE + delimiter + SECOND,
            Locale.CHINESE
        ).format(
            Date()
        )
    }

    val year: String
        /**
         * 获取年
         *
         * @return 例如 2021
         */
        get() = SimpleDateFormat(YEAR, Locale.CHINESE).format(Date())
    val month: String
        /**
         * 获取月
         *
         * @return 例如 07
         */
        get() = SimpleDateFormat(MONTH, Locale.CHINESE).format(Date())
    val day: String
        /**
         * 获取天
         *
         * @return 例如 01
         */
        get() = SimpleDateFormat(DAY, Locale.CHINESE).format(Date())
    val hour: String
        /**
         * 获取小时
         *
         * @return 例如 10
         */
        get() = SimpleDateFormat(HOUR, Locale.CHINESE).format(Date())
    val minute: String
        /**
         * 获取分钟
         *
         * @return 例如 40
         */
        get() = SimpleDateFormat(MINUTE, Locale.CHINESE).format(Date())
    val second: String
        /**
         * 获取秒
         *
         * @return 例如 58
         */
        get() = SimpleDateFormat(SECOND, Locale.CHINESE).format(Date())
    val milliSecond: String
        /**
         * 获取毫秒
         *
         * @return 例如 666
         */
        get() = SimpleDateFormat(MILLISECOND, Locale.CHINESE).format(Date())
    val timestamp: Long
        /**
         * 获取时间戳
         *
         * @return 例如 1625107306051
         */
        get() = System.currentTimeMillis()

    /**
     * 将时间转换为时间戳
     *
     * @param time 例如 2021-07-01 10:44:11
     * @return 1625107451000
     */
    fun dateToStamp(time: String?): Long {
        val simpleDateFormat = SimpleDateFormat(STANDARD_TIME, Locale.CHINESE)
        var date: Date? = null
        try {
            date = simpleDateFormat.parse(time)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return Objects.requireNonNull(date)!!.time
    }

    /**
     * 将时间戳转换为时间
     *
     * @param timeMillis 例如 1625107637084
     * @return 例如 2021-07-01 10:47:17
     */
    fun stampToDate(timeMillis: Long): String {
        return SimpleDateFormat(STANDARD_TIME, Locale.CHINESE).format(Date(timeMillis))
    }

    val todayOfWeek: String
        /**
         * 获取今天是星期几
         *
         * @return 例如 星期四
         */
        get() {
            val cal = Calendar.getInstance()
            cal.time = Date()
            var index = cal[Calendar.DAY_OF_WEEK] - 1
            if (index < 0) {
                index = 0
            }
            return weekDays[index]
        }

    /**
     * 根据输入的日期时间计算是星期几
     *
     * @param dateTime 例如 2021-06-20
     * @return 例如 星期日
     */
    fun getWeek(dateTime: String): String {
        val cal = Calendar.getInstance()
        if ("" == dateTime) {
            cal.time = Date(System.currentTimeMillis())
        } else {
            val sdf = SimpleDateFormat(YEAR_MONTH_DAY, Locale.getDefault())
            var date: Date?
            try {
                date = sdf.parse(dateTime)
            } catch (e: ParseException) {
                date = null
                e.printStackTrace()
            }
            if (date != null) {
                cal.time = Date(date.time)
            }
        }
        return weekDays[cal[Calendar.DAY_OF_WEEK] - 1]
    }

    /**
     * 获取输入日期的昨天
     *
     * @param date 例如 2021-07-01
     * @return 例如 2021-06-30
     */
    fun getYesterday(date: Date?): String {
        var date = date
        val calendar: Calendar = GregorianCalendar()
        calendar.time = date
        calendar.add(Calendar.DATE, -1)
        date = calendar.time
        return SimpleDateFormat(YEAR_MONTH_DAY, Locale.getDefault()).format(date)
    }

    /**
     * 获取输入日期的明天
     *
     * @param date 例如 2021-07-01
     * @return 例如 2021-07-02
     */
    fun getTomorrow(date: Date?): String {
        var date = date
        val calendar: Calendar = GregorianCalendar()
        calendar.time = date
        calendar.add(Calendar.DATE, +1)
        date = calendar.time
        return SimpleDateFormat(YEAR_MONTH_DAY, Locale.getDefault()).format(date)
    }

    /**
     * 根据年月日计算是星期几并与当前日期判断  非昨天、今天、明天 则以星期显示
     *
     * @param dateTime 例如 2021-07-03
     * @return 例如 星期六
     */
    fun getDayInfo(dateTime: String): String {
        val dayInfo: String
        val yesterday = getYesterday(Date())
        val today = theYearMonthAndDay
        val tomorrow = getTomorrow(Date())
        dayInfo = if (dateTime == yesterday) {
            YESTERDAY
        } else if (dateTime == today) {
            TODAY
        } else if (dateTime == tomorrow) {
            TOMORROW
        } else {
            getWeek(dateTime)
        }
        return dayInfo
    }

    val currentMonthDays: Int
        /**
         * 获取本月天数
         *
         * @return 例如 31
         */
        get() {
            val calendar = Calendar.getInstance()
            //把日期设置为当月第一天
            calendar[Calendar.DATE] = 1
            //日期回滚一天，也就是最后一天
            calendar.roll(Calendar.DATE, -1)
            return calendar[Calendar.DATE]
        }

    /**
     * 获得指定月的天数
     *
     * @param year  例如 2021
     * @param month 例如 7
     * @return 例如 31
     */
    fun getMonthDays(year: Int, month: Int): Int {
        val calendar = Calendar.getInstance()
        calendar[Calendar.YEAR] = year
        calendar[Calendar.MONTH] = month - 1
        //把日期设置为当月第一天
        calendar[Calendar.DATE] = 1
        //日期回滚一天，也就是最后一天
        calendar.roll(Calendar.DATE, -1)
        return calendar[Calendar.DATE]
    }
}