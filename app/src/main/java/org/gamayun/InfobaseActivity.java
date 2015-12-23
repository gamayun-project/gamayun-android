package org.gamayun;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by QX on 06.12.2015.
 */
public class InfobaseActivity extends AppCompatActivity {

    private ListView list;
    private String[] itemname;

    private Integer[] imgid;

    private String name;

    public final static String FILE_PATH = "com.gamayun.FILE_PATH";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infobase);

        ContextWrapper c = new ContextWrapper(this);

        Intent i = getIntent();
        name = i.getStringExtra(MainActivity.INFOBASE_NAME);

        String dir = c.getApplicationInfo().dataDir;
        String path = Environment.getExternalStorageDirectory().toString()+"/Gamayun/"+name;

        List<String> itemnameList = new ArrayList<>();
        List<Integer> imgidList = new ArrayList<>();
        File f = new File(path);
        File file[] = f.listFiles();
        if (file != null) {
            for (File inFile : file) {
                if (!inFile.isDirectory()) {
                    String[] parsedFilename = inFile.getName().split("\\.");
                    if (parsedFilename.length == 1) {
                        itemnameList.add(inFile.getName());
                        imgidList.add(R.drawable.ic_assignment_late_black_36dp);
                    }
                    else if (parsedFilename[parsedFilename.length - 1].equals(getString(R.string.type_markup))) {
                        itemnameList.add(inFile.getName());
                        imgidList.add(R.drawable.ic_class_black_24dp);
                    }
                    else if (parsedFilename[parsedFilename.length - 1].equals(getString(R.string.type_wordcloud))) {
                        itemnameList.add(inFile.getName());
                        imgidList.add(R.drawable.ic_backup_black_36dp);
                    }
                    else if (parsedFilename[parsedFilename.length - 1].equals(getString(R.string.type_graph))) {
                        itemnameList.add(inFile.getName());
                        imgidList.add(R.drawable.ic_change_history_black_36dp);
                    }
                }
            }
        }

        this.itemname = new String[itemnameList.size()];
        itemnameList.toArray(itemname);

        this.imgid = new Integer[imgidList.size()];
        imgidList.toArray(imgid);

        IconListAdapter adapter=new IconListAdapter(this, itemname, imgid);
        list=(ListView)findViewById(R.id.list_main);
        list.setAdapter(adapter);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String selectedItem = itemname[+position];
                if (selectedItem.endsWith("gwcl")) {
                    Intent i = new Intent(getApplicationContext(), WordCloudActivity.class);
                    i.putExtra(FILE_PATH, name + "/" + selectedItem);
                    startActivity(i);
                } else {
                    Toast.makeText(getApplicationContext(), selectedItem, Toast.LENGTH_SHORT).show();
                }

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
                Intent i = new Intent(getApplicationContext(), NewItemActivity.class);
                startActivity(i);
                return true;
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showDialog() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        DialogFragment newFragment = NewNoteDialogFragment.newInstance(
                R.string.new_note, name);
        newFragment.show(ft, "dialog");
    }

}

