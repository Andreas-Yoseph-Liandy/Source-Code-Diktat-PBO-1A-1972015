package com.andreas.ArrayOfObject;

import java.util.Arrays;
import java.lang.reflect.Array;

public class CobaArray {
    public static void main(String[] args) { ;
        String [] namaHewan ={"Kucing", "Anjing","Kodok"};
        System.out.println(Arrays.toString(namaHewan));

        for(int i=0;i<namaHewan.length;i++){
            System.out.println(namaHewan[i]);
        }


        Integer[]angka = (Integer[]) Array.newInstance(Integer.class,5);
        Array.set(angka,0,10);
        Array.set(angka,1,15);
        Array.set(angka,2,20);
        Array.set(angka,3,25);
        Array.set(angka,4,30);

        for(Integer number : angka){
            System.out.println(angka);
        }
    }
}
