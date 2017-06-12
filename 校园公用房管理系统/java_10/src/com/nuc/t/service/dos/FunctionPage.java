package com.nuc.t.service.dos;

import com.nuc.t.dao.FileDao;
import com.nuc.t.entity.SchoolUnit;
import com.nuc.t.entity.building.House;
import com.nuc.t.service.Manage;
import com.nuc.t.service.ShowMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by mxwbq on 2017/6/7.
 */
public class FunctionPage {
    Scanner sc = new Scanner(System.in);
    ShowMessage showMessage = new ShowMessage();
    Manage manage;

    public FunctionPage() {
        functionMain();
    }

    public void functionMain() {  // 系统主页
        int i = 0;
        do {
            System.out.print("——————————校园房屋管理系统——————————\n\n" +
                    "主功能模块如下：(请输入功能序号）\n" +
                    "1.查看二级单位；\n" +
                    "2.管理二级单位；\n" +
                    "3.统计所有公用房使用情况；\n" +
                    "4.统计所有公用房安全情况；\n" +
                    "5.退出系统。\n" +
                    "请选择：");
            try {
                i = Integer.valueOf(sc.nextLine());
            } catch (Exception e) {
                //e.printStackTrace();
                System.out.println("请正确输入！");
            }
            switch (i) {
                case 1: showSchoolUnit(); // 显示二级单位的信息
                    break;
                case 2: manageSchoolUnit(); // 管理二级单位
                    break;
                case 3: showMessage.showHouseService(); // 统计所有公用房使用情况
                    select();
                    break;
                case 4: showMessage.showHouseSafety(); // 统计所有公用房安全情况
                    select();
                    break;
                case 5: System.exit(1); // 退出系统
                default:{
                    System.out.println("输入错误，请重新输入！");
                }
            }
        } while (!(i > 0 && i < 6));
    }

    public void showSchoolUnit() {
        int i = 0;
        showMessage.showSchoolUnit();
        do {
            System.out.print("——————————选择功能——————————\n" +
                    "1.查看二级单位房屋详细信息；\n" +
                    "2.回退到主菜单；\n" +
                    "3.退出程序。\n" +
                    "请选择：");
            try {
                i = Integer.valueOf(sc.nextLine());
            } catch (Exception e) {
                //e.printStackTrace();
                System.out.println("请正确输入！");
            }
            switch (i) {
                case 1:
                    inquire(); // 输入要查询的二级单位名称
                    select();
                    break;
                case 2:
                    functionMain(); // 回退到主界面
                    break;
                case 3:
                    System.exit(1);
                default:
                    System.out.println("输入错误，请重新输入！");
            }
        }while (!(i > 0 && i < 4)) ;
    }

    private void inquire(){
        Manage manage = new Manage();
        int flag = 0;
        do {
            System.out.print("请输入要查看的二级单位名称：");
            String s = sc.nextLine();
            for (String name : showMessage.schoolName()) {
                if (name.equals(s)) {
                    flag = 1;
                }
            }
            if (flag == 1) {
                showMessage.showHouse(manage.foundSchoolUnit(s));
            }
        } while (flag == 0);
    }

    public void manageSchoolUnit() { // 二级单位管理界面
        int i = 0;
        manage = new Manage();
        System.out.print("——————————管理二级单位——————————\n\n" +
                "功能如下：(请输入功能序号）\n" +
                "1.查看二级单位房屋信息；\n" +
                "2.添加二级单位；\n" +
                "3.修改二级单位信息；\n" +
                "4.删除二级单位；\n" +
                "5.回到主菜单；\n" +
                "6.退出系统。\n");
        do {
            System.out.print("请选择：");
            try {
                i = Integer.valueOf(sc.nextLine());
            } catch (Exception e) {
                //e.printStackTrace();
                System.out.println("请正确输入！");
            }
            switch (i) {
                case 1: inquire();
                    break;
                case 2: addSchool(); // 添加二级单位
                    break;
                case 3: amendSchool(); // 修改二级单位
                    break;
                case 4: deleteSchool(); // 删除二级单位
                    break;
                case 5: select();
                    break;
                case 6:
                    System.exit(1); // 退出系统
                default:
                    System.out.println("输入错误，请重新输入！");
            }
        } while (true);
    }

    public void select() {  // 选择回到主菜单或退出
        do {
            System.out.print("是否退回到主菜单（Y or N）：");
            String sr = sc.nextLine();
            if (sr.equals("y") || sr.equals("Y")) {
                functionMain();
                break;
            } else if (sr.equals("n") || sr.equals("N")) {
                System.exit(1);
            } else {
                System.out.println("输入错误，请重新输入！");
            }
        } while (true);
    }

    // 添加二级单位界面
    private void addSchool() {
        System.out.println("开始创建二级单位！");
        InputData inputData = new InputData();
        SchoolUnit schoolUnit = inputData.inputSchoolUnit();
        List<House> list = new ArrayList<>();
        do {
            System.out.println("是否为该二级单位添加房屋(请输入 Y 或 N)：");
            String sr = sc.nextLine();
            if (sr.equals("y") || sr.equals("Y")) {
                list.add(inputData.inputHouse());
            } else if (sr.equals("n") || sr.equals("N")) {
                manage.addSchoolUnit(schoolUnit.getUnitName(), list);
                manageSchoolUnit();
                break;
            } else {
                System.out.println("输入错误，请重新输入！");
            }
        } while (true);
    }

    // 修改二级单位界面
    private void amendSchool() {
        System.out.println("开始修改二级单位！");
        InputData inputData = new InputData();
        SchoolUnit schoolUnit = inputData.inputSchoolUnit();
        FileDao fileDao = new FileDao();
        List<House> list = fileDao.inputFile().getMap().get(schoolUnit.getUnitName());
        do {
            System.out.println("请确认修改二级单位(请输入 Y 或 N)：");
            String sr = sc.nextLine();
            if (sr.equals("y") || sr.equals("Y")) {
                System.out.print("请输入要修改二级单位房屋的Id：");
                String s = sc.nextLine();
                if (manage.foundHouse(list, s) != -1) {
                    list.remove(manage.foundHouse(list, s));
                }
                list.add(inputData.inputHouse());
            } else if (sr.equals("n") || sr.equals("N")) {
                manage.amendSchoolUnit(schoolUnit.getUnitName(), list);
                manageSchoolUnit();
                break;
            } else {
                System.out.println("输入错误，请重新输入！");
            }
        } while (true);
    }

    // 删除二级单位界面
    private void deleteSchool() {
        System.out.println("开始删除二级单位！");
        InputData inputData = new InputData();
        do {
            SchoolUnit schoolUnit = inputData.inputSchoolUnit();
            String name = schoolUnit.getUnitName();
            System.out.println("请确认删除二级单位(请输入 Y 或 N)：");
            String sr = sc.nextLine();
            if (sr.equals("y") || sr.equals("Y")) {
                manage.deleteSchoolUnit(name);
            } else if (sr.equals("n") || sr.equals("N")) {
                manageSchoolUnit();
                break;
            } else {
                System.out.println("输入错误，请重新输入！");
            }
        } while (true);
    }
}
