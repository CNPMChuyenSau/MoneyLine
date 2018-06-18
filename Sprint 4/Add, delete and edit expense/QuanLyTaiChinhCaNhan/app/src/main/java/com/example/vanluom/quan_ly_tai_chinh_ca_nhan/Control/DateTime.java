package com.example.vanluom.quan_ly_tai_chinh_ca_nhan.Control;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

// Tạo 1 class để lấy ngày giờ thứ trong tuần hay tháng cho tiện
// Tạo 1 class để dùng cho sau này...
// Còn đối với trong 1 app thì dễ hiểu và đỡ Copy qua lại giữa các Acitivy
public class DateTime {

    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_THU = "AA yyyy-MM-dd HH:mm:ss";
    private Date mDate;
    private Calendar mCalendar;

    public DateTime() {
        this(new Date());
    }

    public DateTime(Date date) {
        mDate = date;
        mCalendar = Calendar.getInstance();
        mCalendar.setTime(mDate);
    }

    // Đưa 1 String sẽ tự động parse String đó có thể get ngày tháng năm và thứ
    private DateTime(String dateFormat, String dateString) {
        mCalendar = Calendar.getInstance();
        SimpleDateFormat mFormat = new SimpleDateFormat(dateFormat);
        try {
            mDate = mFormat.parse(dateString);
            mCalendar.setTime(mDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public DateTime(String dateString) {
        this(DATE_FORMAT, dateString);
    }

    private DateTime(int year, int monthOfYear, int dayOfMonth,
                     int hourOfDay, int minuteOfHour, int secondOfMinute) {
        mCalendar = Calendar.getInstance();
        mCalendar.set(year, monthOfYear, dayOfMonth, hourOfDay, minuteOfHour, secondOfMinute);
        mDate = mCalendar.getTime();
    }


    public DateTime(int year, int monthOfYear, int dayOfMonth,
                    int hourOfDay, int minuteOfHour) {
        this(year, monthOfYear, dayOfMonth, hourOfDay, minuteOfHour, 0);

    }

    public DateTime(int year, int monthOfYear, int dayOfMonth) {
        this(year, monthOfYear, dayOfMonth, 0, 0, 0);
    }

    public Date getDate() {
        return mDate;
    }

    public Calendar getCalendar() {
        return mCalendar;
    }

    private String getDateString(String dateFormat) {
        SimpleDateFormat mFormat = new SimpleDateFormat(dateFormat);
        return mFormat.format(mDate);
    }

    public String getDateString() {

        return getDateString(DATE_FORMAT);

    }

    public int getYear() {
        return mCalendar.get(Calendar.YEAR);
    }

    public int getMonthOfYear() {
        return mCalendar.get(Calendar.MONTH);
    }

    public int getDayOfMonth() {
        return mCalendar.get(Calendar.DAY_OF_MONTH);
    }

    public int getHourOfDay() {
        return mCalendar.get(Calendar.HOUR_OF_DAY);
    }

    public int getMinuteOfHour() {
        return mCalendar.get(Calendar.MINUTE);
    }

    public int getSecondOfMinute() {
        return mCalendar.get(Calendar.SECOND);
    }

    // Lấy thứ trong tuần của tháng nha
    public String getThu() {
        switch (mCalendar.get(Calendar.DAY_OF_WEEK)) {
            case 1: {
                return "Chủ nhật";
            }
            case 2: {
                return "Thứ hai";
            }
            case 3: {
                return "Thứ ba";
            }
            case 4: {
                return "Thứ tư";
            }
            case 5: {
                return "Thứ năm";
            }
            case 6: {
                return "Thứ sáu";
            }
            case 7: {
                return "Thứ bảy";
            }
            default:
                return "";
        }
    }

    // Lấy số thứ tức là từ 1--->7
    public int getThu1() {
        return mCalendar.get(Calendar.DAY_OF_WEEK);
    }
}