package com.nuc.t.service;

import com.nuc.t.dao.FileDao;
import com.nuc.t.entity.building.House;
import java.util.List;
import java.util.Map;

/**
 * Created by mxwbq on 2017/6/7.
 */
public class Manage {
    FileDao fileDao = new FileDao();
    Map<String, List<House>> map;
    List<House> list;

    /*
    添加二级单位
     */
    public boolean addSchoolUnit(String name,List<House> list) {
        try {
            map = fileDao.inputFile().getMap();
            map.put(name, list);
            fileDao.outputFile(map);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /*
    修改二级单位信息
     */
    public boolean amendSchoolUnit(String name, List<House> list) {
        try {
            map = fileDao.inputFile().getMap();
            map.remove(name);
            map.put(name, list);
            fileDao.outputFile(map);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean amendSchoolUnit(String nameLost,String nameNow) {  // 修改二级单位名称
        try {
            map = fileDao.inputFile().getMap();
            list = map.get(nameLost);
            map.remove(nameLost);
            map.put(nameNow, list);
            fileDao.outputFile(map);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /*
    删除二级单位
     */
    public boolean deleteSchoolUnit(String name) {
        try {
            map = fileDao.inputFile().getMap();
            map.remove(name);
            fileDao.outputFile(map);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /*
    根据名称查找二级单位
     */
    public List<House> foundSchoolUnit(String name) {
        return fileDao.inputFile().getMap().get(name);
    }

    /*
    根据Id 查找房屋List位置
     */
    public int foundHouse(List<House> list,String id) {
        int i = -1;

        for (House h : list) {
            if (h.getId().equals(id)) {
                i = list.indexOf(h);
            }
        }
        return i;
    }
}
