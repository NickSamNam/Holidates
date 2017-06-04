package com.nicknam.holidates;

import java.io.Serializable;
import java.util.List;

/**
 * Created by snick on 4-6-2017.
 */

public class Holiday implements Serializable {
    private String name;
    private boolean compulsory;
    private List<Period> periods;

    public Holiday(String name, boolean compulsory, List<Period> periods) {
        this.name = name;
        this.compulsory = compulsory;
        this.periods = periods;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCompulsory() {
        return compulsory;
    }

    public void setCompulsory(boolean compulsory) {
        this.compulsory = compulsory;
    }

    public List<Period> getPeriods() {
        return periods;
    }

    public void setPeriods(List<Period> periods) {
        this.periods = periods;
    }
}
