package com.nuc.t.frame.messageFrame;

import com.nuc.t.dao.FileDao;
import com.nuc.t.entity.building.House;
import com.nuc.t.service.ShowMessage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/**
 * Created by mxwbq on 2017/6/12.
 */
public class DeleteSchool {
    JFrame frame;
    ShowMessage showMessage;
    FileDao fileDao;
    Map<String, List<House>> map;

    public DeleteSchool() {
        frame = new JFrame("删除二级单位");
        JLabel name = new JLabel("要删除二级单位的名称");
        JTextField nameT = new JTextField();
        JButton delete = new JButton("删除");
        JButton cancel = new JButton("取消");

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int flag = 0;
                showMessage = new ShowMessage();
                fileDao = new FileDao();
                String n = nameT.getText();
                String[] ns= showMessage.schoolName();
                for (String s : ns) {
                    if (s.equals(n)) {
                        flag = 1;
                    }
                }

                if (flag == 1) {
                    map = fileDao.inputFile().getMap();
                    map.remove(n);
                    fileDao.outputFile(map);
                    new PromptFrame().succeed("删除成功！");
                    frame.dispose();
                } else {
                    new PromptFrame().defeated("无该单位，无法删除！");
                }
            }
        });

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nameT.setText("");
            }
        });

        frame.setLayout(new GridLayout(2, 2));
        frame.add(name);
        frame.add(nameT);
        frame.add(delete);
        frame.add(cancel);

        frame.setSize(300,150);
        frame.setLocation(650, 250);
        frame.setVisible(true);
    }
}
