package com.nuc.t.frame.messageFrame;

import com.nuc.t.service.Manage;
import com.nuc.t.service.ShowMessage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by mxwbq on 2017/6/11.
 */
public class StatisticsForm {
    JFrame frame;
    Manage manage;
    ShowMessage showMessage;

    // 查找二级单位
    public void foundSchool() throws Exception {
        frame = new JFrame("查询");

        JLabel jLabel = new JLabel("请输入要查询二级单位的名称：");
        JTextField name = new JTextField(20);
        JButton found = new JButton("查询");

        frame.setLayout(null);
        jLabel.setBounds(5,5,300,40);
        name.setBounds(5, 85,150,40);
        found.setBounds(200,85,100,40);
        frame.add(jLabel);
        frame.add(name);
        frame.add(found);

        found.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String n = name.getText();
                try {
                    new StatisticsForm().foundSchool(n);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        frame.setSize(320, 190);
        frame.setLocation(700, 300);
        frame.setVisible(true);
    }

    // 查找房屋
    public void foundHouse() throws Exception {
        frame = new JFrame("查询");

        JLabel jLabel_1 = new JLabel("请输入要查询房屋的归属单位：");
        JLabel jLabel_2 = new JLabel("请输入要查询房屋的ID：");
        JLabel jLabel_3 = new JLabel("查询");
        JTextField belong = new JTextField(20);
        JTextField id = new JTextField(20);
        JButton found = new JButton("查询");

        frame.setLayout(new GridLayout(3, 2));
        frame.add(jLabel_1);
        frame.add(belong);
        frame.add(jLabel_2);
        frame.add(id);
        frame.add(jLabel_3);
        frame.add(found);

        found.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String b = belong.getText();
                String d = id.getText();
                try {
                    new HouseFrame().houseInfo(b,d);
                } catch (Exception e1) {
                    new PromptFrame().defeated("抱歉没有找到该房屋，请重新输入！");
                }
            }
        });

        frame.setSize(400, 200);
        frame.setLocation(650, 250);
        frame.setVisible(true);
    }

    public void foundSchool(String name) throws Exception {
        frame = new JFrame("查询界面");
        String[] s = {"房屋编号","所属单位","位置 ","面积（平方米）","使用方式","使用情况","安全情况"};
        manage = new Manage();
        showMessage = new ShowMessage();
        JTable table = new JTable(showMessage.showHouse(manage.foundSchoolUnit(name)), s);
        JScrollPane scr = new JScrollPane(table);
        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        frame.add(scr);
        frame.setSize(550,300);
        frame.setLocation(600, 200);
        frame.setVisible(true);
    }

    public void serviceForm() {
        frame = new JFrame("使用情况统计");
        JPanel jPanel_1 = new JPanel();
        JPanel jPanel_2 = new JPanel();
        showMessage = new ShowMessage();
        frame.getContentPane().add(jPanel_1, BorderLayout.CENTER);
        frame.getContentPane().add(jPanel_2, BorderLayout.SOUTH);

        String[] s = {"所属单位", "编号", "使用情况"};
        Object[][] objects = showMessage.allServiceCondition();
        int[] i = showMessage.showHouseService();

        JTable table = new JTable(objects, s);
        JScrollPane scr = new JScrollPane(table);
        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JLabel jLabel = new JLabel("共有房屋:"+i[0]+"间；其中，在使用房屋"+i[1]+"间；未使用房屋"+i[2]+"间。");

        jPanel_1.add(scr);
        jPanel_2.add(jLabel);
        frame.setSize(500,500);
        frame.setLocation(600, 200);
        frame.setVisible(true);
    }

    public void safetyForm() {
        frame = new JFrame("安全情况统计");
        JPanel jPanel_1 = new JPanel();
        JPanel jPanel_2 = new JPanel();
        showMessage = new ShowMessage();
        frame.getContentPane().add(jPanel_1, BorderLayout.CENTER);
        frame.getContentPane().add(jPanel_2, BorderLayout.SOUTH);

        String[] s = {"所属单位", "编号", "安全情况"};
        Object[][] objects = showMessage.allSafetyConditon();
        int[] i = showMessage.showHouseSafety();

        JTable table = new JTable(objects, s);
        JScrollPane scr = new JScrollPane(table);
        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JLabel jLabel = new JLabel("共有房屋:"+i[0]+"间；其中，安全房屋"+i[1]+"间；危险房屋"+i[2]+"间。");

        jPanel_1.add(scr);
        jPanel_2.add(jLabel);
        frame.setSize(500,500);
        frame.setLocation(600, 200);
        frame.setVisible(true);
    }
}
