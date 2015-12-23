package org.gamayun.datatypes.wordcloud;

/**
 * Created by QX on 19.12.2015.
 */
public class WordCloudElement {

    private String text;
    private int size = 16;
    private int color = 0;
    private int x;
    private int y;
    private String type = "default";

    public String getText() {
        return text;
    }

    public int getSize() {
        return size;
    }

    public int getColor() {
        return color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getType() {
        return type;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
