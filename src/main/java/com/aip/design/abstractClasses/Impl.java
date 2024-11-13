package com.aip.design.abstractClasses;

import java.util.Random;

public class Impl implements Name {

    @Override
    public void randomNumber() {
        Random random = new Random();
        System.out.println(random.nextInt());
    }

}
