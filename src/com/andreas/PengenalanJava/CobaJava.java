package com.andreas.PengenalanJava;
import java.util.Scanner;

public class CobaJava {
    public static void main(String[] args) {
        System.out.println("Hello Diktat PBO 1A");


        /** Coba IF **/
        int x;
        Scanner inputan = new Scanner(System.in);
        System.out.print("x :");
        x = inputan.nextInt();

        if(x > 20){
            System.out.println("Nilai x lebih dari 20");
        }
        else if(x < 20){
            System.out.println("Nilai x lebih kecil dari 20");
        }
        else{
            System.out.println("Nilai x adalah 10");
        }

        /** Cobain Do While **/
        do{
            System.out.println("x harus sama dengan 0 !");
            x = inputan.nextInt();
        } while(x!=0);

        /** Cobain For **/
        for(int i=0;i<=10;i++){
            System.out.print(i+" ");
        }

        /** Print **/
        System.out.println("Cobain print yah!");



    }


}
