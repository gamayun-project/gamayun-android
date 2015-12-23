package org.gamayun.datatypes.wordcloud;

import java.util.ArrayList;

/**
 * Created by QX on 19.12.2015.
 */
public class WordCloudResult {

    private ArrayList<WordCloudType> types;
    private ArrayList<WordCloudElement> elements;

    public WordCloudResult() {
        this.types = new ArrayList<>();
        this.elements = new ArrayList<>();
    }

    public void addType(WordCloudType type) {
        this.types.add(type);
    }

    public void addElement(WordCloudElement element) {
        this.elements.add(element);
    }

    public ArrayList<WordCloudType> getTypes() {
        return types;
    }

    public ArrayList<WordCloudElement> getElements() {
        return elements;
    }
}
