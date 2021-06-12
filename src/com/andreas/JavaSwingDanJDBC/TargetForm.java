package com.andreas.JavaSwingDanJDBC;

import javax.swing.*;

public class TargetForm {
    private JPanel rootTarget;
    private JLabel txtAlamat;
    private JLabel txtUmur;
    private JLabel txtNama;


    public  void bikinLayout() {
        JFrame frame = new JFrame("TargetForm");
        frame.setContentPane(rootTarget);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void dapatkanPesan(String Nama, String Alamat, String Umur){
        txtNama.setText(Nama);
        txtAlamat.setText(Alamat);
        txtUmur.setText(Umur);
    }
}
