package com.nuc.t.frame.messageFrame;

import com.nuc.t.dao.FileDao;
import com.nuc.t.entity.building.House;
import com.nuc.t.service.Manage;
import com.nuc.t.service.ShowMessage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * Created by mxwbq on 2017/6/12.
 */
public class HouseFrame {
    JFrame frame = new JFrame("房屋信息");

    public void houseInfo(String nameLast,String idLast) {
        Manage manage = new Manage();
        House house = manage.foundSchoolUnit(nameLast).get(manage.foundHouse(manage.foundSchoolUnit(nameLast),idLast));
        JLabel nameJl = new JLabel("所属单位:");
        JLabel idJl = new JLabel("建筑ID:");
        JLabel positionJl = new JLabel("位置:");
        JLabel areaJl = new JLabel("建筑面积（平方米）:");
        JLabel usageJl = new JLabel("使用方式（办公、教学、后勤）:");
        JLabel serviceConditionJl = new JLabel("使用情况（是或否):");
        JLabel safetyConditionJl = new JLabel("安全情况（安全或危险）:");

        JTextField name = new JTextField(nameLast);
        JTextField id = new JTextField(idLast);
        JTextField position = new JTextField(house.getPosition());
        JTextField area = new JTextField(house.getArea()+"");
        JTextField usage = new JTextField(house.getUsage());
        JTextField serviceCondition = new JTextField(house.getServiceCondition());
        JTextField safetyCondition = new JTextField(house.getSafetyCondition());

        JButton amend = new JButton("确认修改");
        JButton delete = new JButton("确认删除");

        amend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShowMessage showMessage = new ShowMessage();
                FileDao fileDao = new FileDao();
                java.util.List<House> list;
                Map<String, java.util.List<House>> map = fileDao.inputFile().getMap();
                House house = manage.foundSchoolUnit(nameLast).get(manage.foundHouse(manage.foundSchoolUnit(nameLast),idLast));
                map.get(nameLast).remove(house);

                int flag = 0;
                int key = 1;
                String hName = name.getText();
                String hId = id.getText();
                String hPosition = position.getText();
                double hArea = 0;
                try {
                    hArea = Double.valueOf(area.getText());
                } catch (Exception ex) {
                    area.setText("请输入正确面积，如：66.6");
                    key = 0; // 判断是否符合条件
                }
                String hUsage = usage.getText();
                String hServiceCondition = serviceCondition.getText();
                String hSafetyCondition = safetyCondition.getText();
                House h;
                h = new House(hId, hName, hPosition, hArea, hUsage, hServiceCondition, hSafetyCondition);
                for (String sName : showMessage.schoolName()) {
                    if (sName.equals(hName)) {
                        flag = 1;
                    }
                }
                if (flag == 1) {
                    list = map.get(hName);
                    list.add(h);
                } else {
                    list = new ArrayList<>();
                    list.add(h);
                }
                if (key == 1) {
                    map.put(hName, list);
                    map.get(nameLast).remove(manage.foundHouse(manage.foundSchoolUnit(nameLast), idLast));
                    fileDao.outputFile(map);
                    frame.dispose();
                    new PromptFrame().succeed("修改成功！");
                } else {
                    name.setText("");
                    id.setText("");
                    position.setText("");
                    usage.setText("");
                    serviceCondition.setText("");
                    safetyCondition.setText("");
                }
            }
        });

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    FileDao fileDao = new FileDao();
                    Map<String, java.util.List<House>> map = fileDao.inputFile().getMap();
                    map.get(nameLast).remove(manage.foundHouse(manage.foundSchoolUnit(nameLast), idLast));
                    fileDao.outputFile(map);
                    frame.dispose();
                    new PromptFrame().succeed("删除成功！");
                } catch (Exception e1) {
                    frame.dispose();
                    new PromptFrame().defeated("删除失败！");
                }
            }
        });

        frame.setLayout(new GridLayout(8, 2));
        frame.add(nameJl);
        frame.add(name);
        frame.add(idJl);
        frame.add(id);
        frame.add(positionJl);
        frame.add(position);
        frame.add(areaJl);
        frame.add(area);
        frame.add(usageJl);
        frame.add(usage);
        frame.add(serviceConditionJl);
        frame.add(serviceCondition);
        frame.add(safetyConditionJl);
        frame.add(safetyCondition);
        frame.add(amend);
        frame.add(delete);

        frame.setSize(500,350);
        frame.setLocation(600, 200);
        frame.setVisible(true);
    }
}
