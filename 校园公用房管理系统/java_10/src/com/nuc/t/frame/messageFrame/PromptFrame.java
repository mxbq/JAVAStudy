package com.nuc.t.frame.messageFrame;

import javax.swing.*;

/**
 * Created by mxwbq on 2017/6/11.
 */
public class PromptFrame {
    JFrame frame = new JFrame("提示");
    JLabel jLabel;

    public void succeed(String s) {
        jLabel = new JLabel(s,JLabel.CENTER);
        frame.add(jLabel);
        frame.setLocation(690, 300);
        frame.setSize(300,150);
        frame.setVisible(true);
    }

    public void defeated(String s) {
        jLabel = new JLabel(s,JLabel.CENTER);
        frame.add(jLabel);
        frame.setLocation(690, 300);
        frame.setSize(300,150);
        frame.setVisible(true);
    }
}
