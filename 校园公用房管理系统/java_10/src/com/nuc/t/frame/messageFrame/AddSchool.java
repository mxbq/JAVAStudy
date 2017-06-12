package com.nuc.t.frame.messageFrame;

import com.nuc.t.frame.MainFrame;
import com.nuc.t.dao.FileDao;
import com.nuc.t.entity.building.House;
import com.nuc.t.service.ShowMessage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.*;
import java.util.List;

/**
 * Created by mxwbq on 2017/6/11.
 */
public class AddSchool extends JFrame {
    public AddSchool() throws HeadlessException {
        JFrame frame = new JFrame("添加二级单位");
        JTextField name = new JTextField("请输入二级单位名称",30);
        JButton houses = new JButton("录入房屋");
        JLabel nameLab = new JLabel("二级单位名称");
        JLabel housesLab = new JLabel("录入房屋");
        JButton ok = new JButton("确定");
        JButton exit = new JButton("退出");
        frame.setLayout(new GridLayout(3, 2));
        frame.add(nameLab);
        frame.add(name);
        frame.add(housesLab);
        frame.add(houses);
        frame.add(ok);
        frame.add(exit);

        houses.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddHouse();
            }
        });

        ok.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String n = name.getText();
                int flag = 0;
                ShowMessage showMessage = new ShowMessage();
                for (String na : showMessage.schoolName()) {
                    if (na.equals(n)) {
                        flag = 1;
                    }
                }
                if (flag == 0) {
                    List<House> list = new ArrayList<>();
                    FileDao fileDao = new FileDao();
                    House house = new House("0", n, "默认房屋", 0, "办公", "否", "安全");
                    list.add(house);
                    Map<String, List<House>> map = fileDao.inputFile().getMap();
                    map.put(n, list);
                    fileDao.outputFile(map);
                    new PromptFrame().succeed("录入成功！");
                } else {
                    new PromptFrame().defeated("录入失败，该单位已存在！");
                }
                frame.dispose();
            }
        });

        exit.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        frame.setSize(500,350);
        frame.setLocation(600, 200);
        frame.setVisible(true);
    }
}
