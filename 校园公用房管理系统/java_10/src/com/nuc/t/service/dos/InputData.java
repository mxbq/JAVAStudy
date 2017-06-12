package com.nuc.t.service.dos;

import com.nuc.t.entity.SchoolUnit;
import com.nuc.t.entity.building.House;

/**
 * Created by mxwbq on 2017/6/7.
 */
public class InputData {
    ImportUnit importUnit = new ImportUnit();

    House house;

    SchoolUnit schoolUnit;

    public House inputHouse() {  // 添加House单位
        String id = importUnit.importHouseId();
        String belong = importUnit.importHouseBelong();
        String position = importUnit.importHousePosition();
        double area = importUnit.importHouseArea();
        String usage = importUnit.importHouseUsage();
        if (importUnit.importSelect().equals("否")) {
            String service = importUnit.importHouseService();
            String safety = importUnit.importHouseSafety();
            house = new House(id, belong, position, area, usage, service, safety);
        } else {
            house = new House(id, belong, position, area, usage, "否", "危险");
        }
        return house;
    }

    public SchoolUnit inputSchoolUnit() { // 添加School单位
        String name = importUnit.importSUnitName();
        schoolUnit = new SchoolUnit(name);
        return schoolUnit;
    }
}