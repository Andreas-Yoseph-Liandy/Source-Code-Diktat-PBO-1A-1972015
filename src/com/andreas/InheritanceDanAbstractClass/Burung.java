package com.andreas.InheritanceDanAbstractClass;

class Burung extends Enemy {
    @Override
    public void walk(){
        System.out.println("Burung mau berjalan");
    }
    public void jump(){
        System.out.println("Burung mau loncat-loncat");
    }
    public void fly(){
        System.out.println("Burung mau Terbang...");
    }
}