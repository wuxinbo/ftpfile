package com.wu.ftpfile.model;

/**
 * Created by Administrator on 2014/12/13.
 */
public class SetItemmodel {
    /**
     * listview item name
     */
    private String itemname;
    private String itemval;
    /**
     * 该项需要跳转的activity
     */
    private String activityname;

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getItemval() {
        return itemval;
    }

    public void setItemval(String itemval) {
        this.itemval = itemval;
    }

    public String getActivityname() {
        return activityname;
    }

    public void setActivityname(String activityname) {
        this.activityname = activityname;
    }
}
