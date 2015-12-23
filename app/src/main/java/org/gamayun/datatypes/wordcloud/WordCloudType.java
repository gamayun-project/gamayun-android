package org.gamayun.datatypes.wordcloud;

/**
 * Created by QX on 19.12.2015.
 */
public class WordCloudType {

    private String typeName;
    private int size = 16;
    private int color = 0;

    public String getTypeName() {
        return typeName;
    }

    public int getSize() {
        return size;
    }

    public int getColor() {
        return color;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
