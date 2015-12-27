package org.gamayun;

import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileHandler {

    public static String readSDCardFile(String localPath) {
        File sdcard = Environment.getExternalStorageDirectory();
        File file = new File(sdcard, "/Gamayun/" + localPath);
        StringBuilder text = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
            br.close();
        } catch (IOException e) {
        }

        return text.toString();
    }

}
