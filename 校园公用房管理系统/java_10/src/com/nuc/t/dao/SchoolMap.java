package com.nuc.t.dao;

import com.nuc.t.entity.building.House;
import java.util.List;
import java.util.Map;

/**
 * Created by mxwbq on 2017/6/7.
 */
public class SchoolMap {
    private Map<String, List<House>> map;

    public SchoolMap() {
    }

    public SchoolMap(Map<String, List<House>> map) {
        this.map = map;
    }

    public void increasekData(String name, List<House> houses){  // 向二级单位添加房屋List
        if (map.containsKey(name)) {
            System.out.println("该单位已存在");
        } else {
            map.put(name, houses);
        }
    }

    public void amendUnit(String name, List<House> houses) { // 修改二级单位的房屋List
        if (map.containsKey(name)) {
            map.put(name, houses);
        } else {
            System.out.println("无该单位");
        }
    }

    public void deleteUnit(String name) { // 删除二级单位的房屋List
        if (map.containsKey(name)) {
            map.remove(name);
        } else {
            System.out.println("无该单位");
        }
    }

    public Map<String, List<House>> getMap() {
        return map;
    }

    public void setMap(Map<String, List<House>> map) {
        this.map = map;
    }
}
