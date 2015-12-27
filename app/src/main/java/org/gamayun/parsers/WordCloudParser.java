package org.gamayun.parsers;

import android.graphics.Color;

import org.gamayun.datatypes.wordcloud.WordCloudElement;
import org.gamayun.datatypes.wordcloud.WordCloudResult;
import org.gamayun.datatypes.wordcloud.WordCloudType;

/**
 * Created by QX on 19.12.2015.
 */
public class WordCloudParser {

    public static WordCloudResult parseString(String target) {
        WordCloudResult result = new WordCloudResult();
        String tempString = target;
        tempString = tempString.replace("\n", "");
        tempString = tempString.replaceAll("]", "]\n");
        String[] stringArray = tempString.split("\n");
        for (String s: stringArray) {
            if (s.startsWith("#type:")) {
                WordCloudType wcType = new WordCloudType();
                wcType.setTypeName(s.substring(6, s.lastIndexOf("[")));
                String[] params = s.
                        substring(s.lastIndexOf("[") + 1, s.lastIndexOf("]")).
                        split(",");
                for (String param: params) {
                    String[] parsedParam = param.split("=");
                    String paramName = parsedParam[0];
                    if (paramName.equals("s") || paramName.equals("size")) {
                        wcType.setSize(Integer.parseInt(parsedParam[1]));
                    } else if (paramName.equals("c") || paramName.equals("color") || paramName.equals("colour")) {
                        wcType.setColor(Color.parseColor(parsedParam[1]));
                    }
                }
                result.addType(wcType);
            } else {
                WordCloudElement wcElement = new WordCloudElement();
                wcElement.setText(s.substring(0, s.lastIndexOf("[")));
                String[] params = s.
                        substring(s.lastIndexOf("[") + 1, s.lastIndexOf("]")).
                        split(",");
                for (String param: params) {
                    String[] parsedParam = param.split("=");
                    String paramName = parsedParam[0];
                    switch (paramName) {
                        case "s":
                        case "size":
                            wcElement.setSize(Integer.parseInt(parsedParam[1]));
                            break;
                        case "c":
                        case "color":
                        case "colour":
                            wcElement.setColor(Color.parseColor(parsedParam[1]));
                            break;
                        case "x":
                            wcElement.setX(Integer.parseInt(parsedParam[1]));
                            break;
                        case "y":
                            wcElement.setY(Integer.parseInt(parsedParam[1]));
                            break;
                    }
                }
                result.addElement(wcElement);
            }
        }
        return result;
    }

}
