package com.nicknam.holidates;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by snick on 4-6-2017.
 */

public class Period implements Serializable {
    private String region;
    private Date startDate;
    private Date endDate;

    public Period(String region, Date startDate, Date endDate) {
        this.region = region;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
