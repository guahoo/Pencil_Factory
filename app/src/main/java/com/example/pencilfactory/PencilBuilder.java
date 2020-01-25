package com.example.pencilfactory;

public class PencilBuilder {
    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getCorners() {
        return corners;
    }

    public void setCorners(int corners) {
        this.corners = corners;
    }

    public int getPencilBodyColour() {
        return pencilBodyColour;
    }

    public void setPencilBodyColour(int pencilBodyColour) {
        this.pencilBodyColour = pencilBodyColour;
    }

    public int getStripColor() {
        return stripColor;
    }

    public void setStripColor(int stripColor) {
        this.stripColor = stripColor;
    }

    public int getDipStripColor() {
        return dipStripColor;
    }

    public void setDipStripColor(int dipStripColor) {
        this.dipStripColor = dipStripColor;
    }

    public int getEraserColor() {
        return eraserColor;
    }

    public void setEraserColor(int eraserColor) {
        this.eraserColor = eraserColor;
    }

    public boolean isSharpening() {
        return sharpening;
    }

    public void setSharpening(boolean sharpening) {
        this.sharpening = sharpening;
    }

    public boolean isStrips() {
        return strips;
    }

    public void setStrips(boolean strips) {
        this.strips = strips;
    }

    public boolean isEraser() {
        return eraser;
    }

    public void setEraser(boolean eraser) {
        this.eraser = eraser;
    }

    public boolean isDip() {
        return dip;
    }

    public void setDip(boolean dip) {
        this.dip = dip;
    }

    public boolean isDoiStrip() {
        return doiStrip;
    }

    public void setDoiStrip(boolean doiStrip) {
        this.doiStrip = doiStrip;
    }

    public boolean isText() {
        return text;
    }

    public void setText(boolean text) {
        this.text = text;
    }

    public String getPencilText() {
        return pencilText;
    }

    public void setPencilText(String pencilText) {
        this.pencilText = pencilText;
    }

    private int length = 177;
    private int corners = 6;
    private int pencilBodyColour;
    private int stripColor;
    private int dipStripColor;
    private int eraserColor;

    private boolean sharpening=true;
    private boolean strips;
    private boolean eraser;
    private boolean dip;
    private boolean doiStrip;
    private boolean text;
    private String pencilText;

}
