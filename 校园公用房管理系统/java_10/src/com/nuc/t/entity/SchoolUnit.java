package com.nuc.t.entity;

import com.nuc.t.entity.building.House;

import java.util.List;

/**
 * Created by mxwbq on 2017/5/31.
 */
public class SchoolUnit {
    private String unitName = null;
    private List<House> houses = null;

    public SchoolUnit() {
    }

    public SchoolUnit(String unitName) {
        this.unitName = unitName;
    }

    public SchoolUnit(String unitName, List<House> houses) {
        this.unitName = unitName;
        this.houses = houses;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }
}
