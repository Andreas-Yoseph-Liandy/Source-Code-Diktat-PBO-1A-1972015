package com.andreas.PengenalanJava;
import java.util.Scanner;



public class MobilDemo {
    public static void main(String[] args) {
        Mobil namaMobil = new Mobil();
        Scanner inputan = new Scanner(System.in);
        String x = inputan.next();

        namaMobil.setNama(x);

    }
}
}
