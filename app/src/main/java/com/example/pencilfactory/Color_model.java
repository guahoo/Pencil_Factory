package com.example.pencilfactory;

public class Color_model {
    public int getColor() {
        return color;
    }

    private int color;

    Color_model(int color) {
        this.color=color;
    }



    static int[] getColors() {
        return colors;
    }

    private static int[]colors=new int[]{
            R.color.E80062,
            R.color.B2D1DE,
            R.color.G91EA00,
            R.color.L9839E3,
            R.color.BBF7744,
            R.color.FF7A00,
            R.color.B050000,
            R.color.B704025,
            R.color.G2C9308,
            R.color.FF0000,
            R.color.YFAFF00,
            R.color.colorDefault
           };







}
