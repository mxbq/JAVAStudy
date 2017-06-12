package com.nuc.t.frame;

import com.nuc.t.Data.Data;
import com.nuc.t.frame.messageFrame.AddSchool;
import com.nuc.t.frame.messageFrame.DeleteSchool;
import com.nuc.t.frame.messageFrame.StatisticsForm;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by mxwbq on 2017/6/11.
 * 主窗口界面
 */
public class MainFrame {
    public static void main(String[] args) {
        new MainFrame();
    }

    JFrame frame;
    private JTable table;

    public MainFrame() {
        frame = new JFrame("校园公用房管理系统——主界面");

        JPanel panel_1 = new JPanel();
        frame.getContentPane().add(panel_1, BorderLayout.SOUTH);  //下部

        JButton addButton = new JButton("增加");
        panel_1.add(addButton);

        JButton amendButton = new JButton("修改");
        panel_1.add(amendButton);

        JButton deleteButton = new JButton("删除");
        panel_1.add(deleteButton);

        JButton foundButton = new JButton("查找");
        panel_1.add(foundButton);

        JButton serviceStatisticsButton = new JButton("使用数据统计");
        panel_1.add(serviceStatisticsButton);

        JButton safetyStatisticsButton = new JButton("安全数据统计");
        panel_1.add(safetyStatisticsButton);

        JButton f5Button = new JButton("刷新");
        panel_1.add(f5Button);


        JPanel panel_2 = new JPanel();
        frame.getContentPane().add(panel_2, BorderLayout.CENTER); // 中部

        addButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddSchool();
            }
        });

        amendButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new StatisticsForm().foundHouse();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        deleteButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DeleteSchool();
            }
        });

        foundButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new StatisticsForm().foundSchool();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        serviceStatisticsButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StatisticsForm().serviceForm();
            }
        });

        safetyStatisticsButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StatisticsForm().safetyForm();
            }
        });

        f5Button.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new MainFrame();
            }
        });


        Data data = new Data();
        Object[][] object = data.getSchoolData();
        String[] s = {"名称","房屋总数","办公","教学","后勤"};
        table = new JTable(object,s);
        JScrollPane scr = new JScrollPane(table);
        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        panel_2.add(scr);
        frame.setSize(600,300);
        frame.setLocation(600, 200);
        frame.setVisible(true);
    }
}
