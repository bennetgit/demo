package com.wfc.cxf.runntime;

public class PgRunntime {

    private void runtimeTest() {

        Runtime runtime = Runtime.getRuntime();
    }

    public static void main(String[] args) {
        String sdf = "sdf";
        Boolean sdf1 = false;
        boolean out = sdf != null && sdf1;
        System.out.println(out);

        double t1 = -1;
        System.out.println(t1 < 0);
    }
}
