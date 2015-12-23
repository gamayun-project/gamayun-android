package org.gamayun;

import android.app.DialogFragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class NewItemActivity extends AppCompatActivity {

    ListView list;
    String[] itemname ={
            "Note",
            "Word cloud",
            "Mindmap",
            "Handwrite",
    };

    Integer[] imgid={
            R.drawable.note,
            R.drawable.mindmap,
            R.drawable.note,
            R.drawable.mindmap,
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);

        IconListAdapter adapter=new IconListAdapter(this, itemname, imgid);
        list=(ListView)findViewById(R.id.list_new_item);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String SelectedItem = itemname[+position];
                if (SelectedItem.equals("Word cloud")) {
                    Intent i = new Intent(getApplicationContext(), WordCloudActivity.class);
                    //TODO: Add note name selection
                    //File sdcard = Environment.getExternalStorageDirectory();
                    //File file = new File(sdcard, "/GMN/WC.txt");
                    //showDialog();
                    //startActivity(i);
                } else {
                    Toast.makeText(getApplicationContext(), SelectedItem, Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

    protected void showDialog() {
        DialogFragment newFragment = NewNotebookDialogFragment.newInstance(
                R.string.new_notebook);
        newFragment.show(getFragmentManager(), "dialog");
    }

}
