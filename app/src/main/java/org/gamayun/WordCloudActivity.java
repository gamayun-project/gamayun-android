package org.gamayun;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class WordCloudActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        String filePath = i.getStringExtra(InfobaseActivity.FILE_PATH);
        setContentView(new WordCloudViewer(this, filePath));

        //WordCloudViewer viewer=new WordCloudViewer(this);
    }


}
