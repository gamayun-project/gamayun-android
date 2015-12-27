package org.gamayun;

import android.app.DialogFragment;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String[] itemname;

    public final static String INFOBASE_NAME = "com.gamayun.INFOBASE_NAME";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ContextWrapper c = new ContextWrapper(this);

        String dir = c.getApplicationInfo().dataDir;
        String path = Environment.getExternalStorageDirectory().toString()+"/Gamayun";

        List<String> itemname_list = new ArrayList<>();
        List<Integer> imgid_list = new ArrayList<>();
        File f = new File(path);
        File file[] = f.listFiles();
        if (file != null) {
            for (File inFile : file) {
                if (inFile.isDirectory()) {
                    itemname_list.add(inFile.getName());
                    imgid_list.add(R.drawable.infobase);
                }
            }
        }

        this.itemname = new String[itemname_list.size()];
        itemname_list.toArray(itemname);

        Integer[] imgid = new Integer[imgid_list.size()];
        imgid_list.toArray(imgid);

        IconListAdapter adapter=new IconListAdapter(this, itemname, imgid);
        ListView list = (ListView) findViewById(R.id.list_main);
        list.setAdapter(adapter);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String infobaseName = itemname[+position];
                Intent i = new Intent(getApplicationContext(), InfobaseActivity.class);
                i.putExtra(INFOBASE_NAME, infobaseName);
                startActivity(i);
                //Toast.makeText(getApplicationContext(), SelectedItem, Toast.LENGTH_SHORT).show();

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
        // получим идентификатор выбранного пункта меню
        int id = item.getItemId();

        // Операции для выбранного пункта меню
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
