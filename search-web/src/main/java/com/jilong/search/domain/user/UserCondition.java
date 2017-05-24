package com.jilong.search.domain.user;

/**
 * @author jilong.qiu
 * @date 2017/4/28.
 */
public class UserCondition {

    private String name;
    private Double nameMatchRate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getNameMatchRate() {
        return nameMatchRate;
    }

    public void setNameMatchRate(Double nameMatchRate) {
        this.nameMatchRate = nameMatchRate;
    }
}
