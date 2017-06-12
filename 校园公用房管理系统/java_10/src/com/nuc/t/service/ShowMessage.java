package com.nuc.t.service;

import com.nuc.t.dao.FileDao;
import com.nuc.t.entity.building.House;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by mxwbq on 2017/6/7.
 */
public class ShowMessage {
    private FileDao fileDao = new FileDao();
    private Manage manage = new Manage();

    /*
    统计二级单位的名称
     */
    public String[] schoolName() {
        Set<String> set = fileDao.inputFile().getMap().keySet();
        String[] name = new String[set.size()];
        Iterator i = set.iterator();
        int n = 0;
        while (i.hasNext()) {
            name[n] = String.valueOf(i.next());
            n++;
        }
        return name;
    }

    /*
    统计二级单位的数量
     */
    public int schoolUnitAmount() {
        return fileDao.inputFile().getMap().size();
    }

    /*
    统计某二级单位房屋总数量
     */
    public int houseAmount(String name) {
        return fileDao.inputFile().getMap().get(name).size();
    }

    /*
    统计某单位各种房屋数量
     */
    public int[] housesNumber(String name) {
        int[] number = new int[3];
        List<House> list = fileDao.inputFile().getMap().get(name);
        for (House h : list) {
            if (h.getUsage().equals("办公")) {
                number[0]++;
            } else if (h.getUsage().equals("教学")) {
                number[1]++;
            } else {
                number[2]++;
            }
        }
        return number;
    }

    /*
    统计所有单位房屋数量
     */
    public int allHousesNumber() {
        int n = 0;
        for (String name : schoolName()) {
            n += houseAmount(name);
        }
        return n;
    }

    /*
    显示二级单位的名称，房屋总量，各种房屋数量（办公、教学、后勤）
     */
    public Object[][] showSchoolUnit() {
        String[] n = schoolName();
        Object[][] objects = new Object[schoolUnitAmount()][5];
        System.out.println("——————————二级单位信息——————————\n" +
                "  单位名称  |  房屋总量  |  办公  |  教学  |  后勤  ");
        for (int i = 0; i < schoolUnitAmount(); i++) {
            objects[i][0] = n[i];
            objects[i][1] = houseAmount(n[i]);
            objects[i][2] = housesNumber(n[i])[0];
            objects[i][3] = housesNumber(n[i])[1];
            objects[i][4] = housesNumber(n[i])[2];
            System.out.printf("%6s  |%7d     |%5d   |%5d   |%5d   \n",objects[i][0],objects[i][1],objects[i][2],objects[i][3],objects[i][4]);
        }
        return objects;
    }

    /*
    显示某单位所有房屋信息（房屋编号、所属单位、房屋位置、建筑面积、使用方式（办公、教学、后勤）、使用情况（是or否）、安全情况（安全or危险））
     */
    public Object[][] showHouse(List<House> list) {
        Object[][] objects;
        System.out.println("——————————房屋使用信息——————————\n" +
                "  房屋编号  |  所属单位  |         位置        |面积（平方米）|使用方式|使用情况|安全情况");
        Iterator i = list.iterator();
        House h;
        objects = new Object[houseAmount(list.get(0).getBelong())][7];
        int n = 0;
        while (i.hasNext()) {
            h = (House) i.next();
            objects[n][0] = h.getId();
            objects[n][1] = h.getBelong();
            objects[n][2] = h.getPosition();
            objects[n][3] = h.getArea();
            objects[n][4] = h.getUsage();
            objects[n][5] = h.getServiceCondition();
            objects[n][6] = h.getSafetyCondition();
            n++;
            System.out.printf("%8s    |%7s |%12s      |%10.2f    |%4s  |%4s   |%4s  \n", h.getId(), h.getBelong(), h.getPosition(), h.getArea(), h.getUsage(), h.getServiceCondition(), h.getSafetyCondition());
        }
        return objects;
    }

    /*
    显示所有房屋使用情况（返回值：房屋总数|是|否）
     */
    public int[] showHouseService() {
        int[] i = new int[3];
        System.out.println("  所属单位  |  编号  |  使用情况");
        for (String name : schoolName()) {
            for (House house : manage.foundSchoolUnit(name)) {
                System.out.printf("%6s  |%4s    |%4s\n", house.getBelong(), house.getId(), house.getServiceCondition());
                if (house.getServiceCondition().equals("是")) {
                    i[1]++;
                } else {
                    i[2]++;
                }
                i[0]++;
            }
        }
        System.out.println("统计结果：共有房屋数量为" + i[0] + ";其中，在使用数量为：" + i[1] + ",未使用数量为：" + i[2] + "。");
        return i;
    }

    /*
    显示所有房屋使用情况（返回值：所属单位,编号,使用情况）
     */
    public Object[][] allServiceCondition() {
        Object[][] objects = new Object[allHousesNumber()][3];
        int i = 0;
        for (String name : schoolName()) {
            for (House house : manage.foundSchoolUnit(name)) {
                objects[i][0] = house.getBelong();
                objects[i][1] = house.getId();
                objects[i][2] = house.getServiceCondition();
                i++;
            }
        }
        return objects;
    }

    /*
    显示所有房屋安全情况(返回值：房屋总数|安全数量|危险数量）
     */
    public int[] showHouseSafety() {
        int[] i = new int[3];
        System.out.println("  所属单位  |  编号  |  安全情况");
        for (String name : schoolName()) {
            for (House house : manage.foundSchoolUnit(name)) {
                System.out.printf("%6s  |%4s    |%4s\n", house.getBelong(), house.getId(), house.getSafetyCondition());
                if (house.getSafetyCondition().equals("安全")) {
                    i[1]++;
                } else {
                    i[2]++;
                }
                i[0]++;
            }
        }
        System.out.println("统计结果：共有房屋数量为：" + i[0] + ";其中，安全数量为：" + i[1] + ",危险数量为：" + i[2] + "。");
        return i;
    }

    /*
    显示所有房屋使用情况（返回值：所属单位,编号,使用情况）
     */
    public Object[][] allSafetyConditon() {
        Object[][] objects = new Object[allHousesNumber()][3];
        int i = 0;
        for (String name : schoolName()) {
            for (House house : manage.foundSchoolUnit(name)) {
                objects[i][0] = house.getBelong();
                objects[i][1] = house.getId();
                objects[i][2] = house.getSafetyCondition();
                i++;
            }
        }
        return objects;
    }
}
