package ca.ualberta.cs.yifu3_CardioBook;

import java.util.Date;

public class Information {
    private String time;
    private Date date;
    private int systolicpressure ;
    private int diastolicpressure;
    private int heartrate;
    private String comment;

    /**
     * get time value
     * @return
     */
    public String getTime() {
        return time;
    }

    /**
     * set time value
     * @param time
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * get data value
     * @return date
     */
    public Date getDate() {
        return date;
    }

    /**
     * set time value
     * @param date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * getSystolic pressure value
     * @return systolicpressure
     */
    public int getSystolicpressure() {
        return systolicpressure;
    }

    /**
     * set getSystolic pressure value
     * @param systolicpressure
     */
    public void setSystolicpressure(int systolicpressure) {
        this.systolicpressure = systolicpressure;
    }

    /**
     * get diastolic pressure value
     * @return diastolicpressure
     */
    public int getDiastolicpressure() {
        return diastolicpressure;
    }

    /**
     * set diastolic pressure value
     * @param diastolicpressure
     */
    public void setDiastolicpressure(int diastolicpressure) {
        this.diastolicpressure = diastolicpressure;
    }

    /**
     * get heartrate value
     * @return heartrate
     */
    public int getHeartrate() {
        return heartrate;
    }

    /**
     * setHeart rate
     * @param heartrate
     */
    public void setHeartrate(int heartrate) {
        this.heartrate = heartrate;
    }

    /**
     * getComment
     * @return comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * set comment
     * @param comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }
}
