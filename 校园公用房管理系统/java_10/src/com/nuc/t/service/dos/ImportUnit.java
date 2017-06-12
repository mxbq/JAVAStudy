package com.nuc.t.service.dos;

import java.util.Scanner;

/**
 * Created by mxwbq on 2017/6/7.
 */
public class ImportUnit {
    Scanner sc = new Scanner(System.in);

    public String importSUnitName() {
        System.out.println("请输入该二级单位名称：");
        return sc.nextLine();
    }

    public String importSUnitType() {
        System.out.println("请输入该二级单位类型（学院 或 后勤）：");
        return sc.nextLine();
    }

    public String importHouseId() {
        System.out.println("请输入该建筑编号：");
        return sc.nextLine();
    }

    public String importHouseBelong() {
        System.out.println("请输入该建筑所属单位：");
        return sc.nextLine();
    }

    public String importHouseUsage() {
        System.out.println("请输入建筑使用方式（请从办公、教学、后勤中选择一种）：");
        return sc.nextLine();
    }

    public String importHousePosition() {
        System.out.println("请输入该建筑所在位置：");
        return sc.nextLine();
    }

    public double importHouseArea() {
        System.out.println("请输入该建筑总面积（单位：平方米）：");
        return Double.valueOf(sc.nextLine());
    }

    public String importHouseService() {
        System.out.println("请输入该建筑使用情况（请输入“是”或“否”）：");
        return sc.nextLine();
    }

    public String importHouseSafety() {
        System.out.println("请输入该建筑安全情况（请输入“安全”或“危险”）：");
        return sc.nextLine();
    }

    public String importSelect() {
        System.out.println("是否默认房屋类型（不使用，不安全）（请输入“是”或“否”）：");
        return sc.nextLine();
    }
}
