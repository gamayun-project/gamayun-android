package org.gamayun;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.DialogFragment;
import android.app.AlertDialog;
import android.os.Environment;
import android.util.Log;
import android.widget.EditText;

import java.io.File;

/**
 * Created by QX on 27.11.2015.
 */
public class NewNotebookDialogFragment extends DialogFragment {

    public static NewNotebookDialogFragment newInstance(int title) {
        NewNotebookDialogFragment frag = new NewNotebookDialogFragment();
        Bundle args = new Bundle();
        args.putInt("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int title = getArguments().getInt("title");
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity())
                .setIcon(R.drawable.ic_note_add_black_36dp)
                .setTitle(title)
                //.setView(R.layout.new_notebook_dialog)

                .setNegativeButton(R.string.cancel,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dialog.cancel();
                            }
                        }
                );


        final EditText editText = new EditText(alertDialogBuilder.getContext());
        //alert.setIcon(R.drawable.ic_note_add_black_36dp);
        //alert
        alertDialogBuilder.setView(editText);
        alertDialogBuilder.setPositiveButton(R.string.ok,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        //EditText editText = (EditText) getActivity().findViewById(R.id.new_infobase_name);
                        //Log.i("_", Environment.getExternalStorageDirectory().toString() + "/Gamayun/" + editText.getText().toString());
                        File pathDir = new File(
                                Environment.getExternalStorageDirectory().toString() +
                                        "/Gamayun/" +
                                        editText.getText().toString()
                        );
                        if (!pathDir.exists()) {
                            pathDir.mkdirs();
                            Log.i("_", "Trying");
                        }
                        if (pathDir.exists()) {
                            Log.i("_", "!!!");
                        } else {
                            Log.i("_", ":(");
                        }

                        //File newDir = new File(pathDir, editText.getText().toString());
                    }
                }
        );
        return alertDialogBuilder.create();
    }
}