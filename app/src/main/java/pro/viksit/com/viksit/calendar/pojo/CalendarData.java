package pro.viksit.com.viksit.calendar.pojo;

import java.util.Date;

/**
 * Created by Feroz on 22-03-2017.
 */

public class CalendarData {

    private Date event_date;
    private String event_name;
    private String status;
    private String college;

    public CalendarData() {
    }

    public CalendarData(Date event_date, String event_name, String status, String college) {
        this.event_date = event_date;
        this.event_name = event_name;
        this.status = status;
        this.college = college;
    }

    public Date getEvent_date() {
        return event_date;
    }

    public void setEvent_date(Date event_date) {
        this.event_date = event_date;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }
}
