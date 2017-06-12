package com.nuc.t.frame.messageFrame;

import com.nuc.t.dao.FileDao;
import com.nuc.t.entity.building.House;
import com.nuc.t.service.ShowMessage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by mxwbq on 2017/6/11.
 */
public class AddHouse {
    public AddHouse() {
        JFrame frame = new JFrame("添加房屋");

        JLabel nameJl = new JLabel("所属单位:");
        JLabel idJl = new JLabel("建筑ID:");
        JLabel positionJl = new JLabel("位置:");
        JLabel areaJl = new JLabel("建筑面积（平方米）:");
        JLabel usageJl = new JLabel("使用方式（办公、教学、后勤）:");
        JLabel serviceConditionJl = new JLabel("使用情况（是或否):");
        JLabel safetyConditionJl = new JLabel("安全情况（安全或危险）:");

        JTextField name = new JTextField("请输入单位名称");
        JTextField id = new JTextField("请输入建筑ID");
        JTextField position = new JTextField("请输入建筑位置");
        JTextField area = new JTextField("请输入建筑面积（平方米）");
        JTextField usage = new JTextField("请输入建筑使用方式（办公、教学、后勤）");
        JTextField serviceCondition = new JTextField("请输入建筑使用情况（是或否)");
        JTextField safetyCondition = new JTextField("请输入建筑安全情况（安全或危险）");

        JButton ok = new JButton("确认");
        JButton cancel = new JButton("重新录入");

        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                House house;
                ShowMessage showMessage = new ShowMessage();
                FileDao fileDao = new FileDao();
                List<House> list;
                Map<String, List<House>> map = fileDao.inputFile().getMap();
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
                house = new House(hId, hName, hPosition, hArea, hUsage, hServiceCondition, hSafetyCondition);
                for (String sName : showMessage.schoolName()) {
                    if (sName.equals(hName)) {
                        flag = 1;
                    }
                }
                if (flag == 1) {
                    list = map.get(hName);
                    list.add(house);
                } else {
                    list = new ArrayList<>();
                    list.add(house);
                }
                if (key == 1) {
                    map.put(hName, list);
                    fileDao.outputFile(map);
                    frame.dispose();
                    new PromptFrame().succeed("录入成功！");
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

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                name.setText("");
                id.setText("");
                position.setText("");
                area.setText("");
                usage.setText("");
                serviceCondition.setText("");
                safetyCondition.setText("");
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
        frame.add(ok);
        frame.add(cancel);

        frame.setSize(500,350);
        frame.setLocation(600, 200);
        frame.setVisible(true);
    }
}
