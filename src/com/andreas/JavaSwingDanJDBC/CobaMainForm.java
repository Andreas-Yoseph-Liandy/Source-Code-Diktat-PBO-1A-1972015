package com.andreas.JavaSwingDanJDBC;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CobaMainForm {
    private JPasswordField passwordField1;
    private JButton andreasButton;
    private JButton gantengButton;
    private JButton cobaGUIButton;
    private JButton resetButton;
    private JButton updateButton;
    private JTable table1;
    private JSplitPane rootPanel;
    private JLabel textNama;
    private JLabel textAlamat;
    private JLabel textCoba;

    public CobaMainForm() {
        andreasButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(rootPanel,"Hello coba JOptionPane","Hai semua",JOptionPane.INFORMATION_MESSAGE);
            JOptionPane.showMessageDialog(rootPanel,"Cobain EROR Gak Ya","Eror",JOptionPane.ERROR_MESSAGE);
            int hasil=JOptionPane.showConfirmDialog(rootPanel,"Mau Lanjut atau Gak?");
            if(hasil==JOptionPane.YES_OPTION){
                String pesan = JOptionPane.showInputDialog("Masukan Pesan : ");
                JOptionPane.showMessageDialog(rootPanel,"Masukan Pesan :"+pesan,"Kabar",JOptionPane.INFORMATION_MESSAGE);
            }
        });

        cobaGUIButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TargetForm targetForm = new TargetForm();
                targetForm.bikinLayout();
                targetForm.dapatkanPesan(textNama.getText(),textAlamat.getText(),textCoba.getText());

            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MainForm");
        frame.setContentPane(new CobaMainForm().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
