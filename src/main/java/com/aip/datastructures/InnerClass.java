package com.aip.datastructures;

public class InnerClass {
    private boolean flag = true;

    private class Inner {
        public void sample() {
            if (InnerClass.this.flag) {
                System.out.println("Sample");
            }
        }
    }

    public InnerClass() {
        (new Inner()).sample();
    }
}
