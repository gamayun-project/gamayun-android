package org.gamayun;

import android.app.DialogFragment;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String[] itemsNames;

    public final static String INFOBASE_NAME = "com.gamayun.INFOBASE_NAME";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ContextWrapper c = new ContextWrapper(this);

        String path = Environment.getExternalStorageDirectory().toString()+"/Gamayun";

        List<String> itemsNamesList = new ArrayList<>();
        List<Integer> imagesIdsList = new ArrayList<>();
        File f = new File(path);
        File file[] = f.listFiles();
        if (file != null) {
            for (File inFile : file) {
                if (inFile.isDirectory()) {
                    itemsNamesList.add(inFile.getName());
                    imagesIdsList.add(R.drawable.infobase);
                }
            }
        }

        this.itemsNames = new String[itemsNamesList.size()];
        itemsNamesList.toArray(itemsNames);

        Integer[] imagesIds = new Integer[imagesIdsList.size()];
        imagesIdsList.toArray(imagesIds);

        IconListAdapter adapter=new IconListAdapter(this, itemsNames, imagesIds);
        ListView list = (ListView) findViewById(R.id.list_main);
        list.setAdapter(adapter);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String infobaseName = itemsNames[+position];
                Intent i = new Intent(getApplicationContext(), InfobaseActivity.class);
                i.putExtra(INFOBASE_NAME, infobaseName);
                startActivity(i);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.main_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_new_item:
                return true;
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showDialog() {
        DialogFragment newFragment = NewNotebookDialogFragment.newInstance(
                R.string.new_notebook);
        newFragment.show(getFragmentManager(), "dialog");
    }

}
