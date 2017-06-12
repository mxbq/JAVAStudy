package com.nuc.t.frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by mxwbq on 2017/6/11.
 * 登陆窗口
 */
class LoginCheck {
    private String name;
    private String password;

    public LoginCheck(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public boolean validate() {
        if ("root".equals(name) && "root".equals(password)) {
            return true;
        } else {
            return false;
        }
    }
}

class ActionHandle {
    private JFrame frame = new JFrame("登录");
    private JButton submit = new JButton("登录");
    private JButton reset = new JButton("重置");
    private JLabel nameLab = new JLabel("用户名：");
    private JLabel passLab = new JLabel("密 码：");
    private JLabel infoLab = new JLabel("用户登录系统");
    private JTextField nameText = new JTextField();
    private JPasswordField passText = new JPasswordField();
    public ActionHandle() {
        Font fnt = new Font("Serief", Font.BOLD, 12);
        infoLab.setFont(fnt);
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == submit) {
                    String tname = nameText.getText();
                    String tpass = new String(passText.getPassword());
                    LoginCheck log = new LoginCheck(tname, tpass);
                    if (log.validate()) {
                        infoLab.setText("登录成功，欢迎使用！");
                        new MainFrame();
                        frame.dispose();
                    } else {
                        infoLab.setText("登陆失败，请再次输入！");
                    }
                }
            }
        });
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nameText.setText("");
                passText.setText("");
                infoLab.setText("用户登录系统");
            }
        });
        frame.setLayout(null);
        nameLab.setBounds(5, 5, 60, 20);
        passLab.setBounds(5,30,60,20);
        infoLab.setBounds(5,65,220,30);
        nameText.setBounds(65,5,100,20);
        passText.setBounds(65,30,100,20);
        submit.setBounds(165, 5, 60, 20);
        reset.setBounds(165, 30, 60, 20);
        frame.add(nameLab);
        frame.add(passLab);
        frame.add(infoLab);
        frame.add(nameText);
        frame.add(passText);
        frame.add(submit);
        frame.add(reset);
        frame.setSize(280, 150);
        frame.setLocation(600, 200);
        frame.setVisible(true);
    }
}


public class LocationFrame {
    public static void main(String[] args) {
        new ActionHandle();
    }
}
