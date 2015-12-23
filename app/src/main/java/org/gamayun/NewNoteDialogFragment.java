package org.gamayun;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by QX on 12.12.2015.
 */
public class NewNoteDialogFragment extends DialogFragment {

    public static NewNoteDialogFragment newInstance(int title, String infobase_name) {
        NewNoteDialogFragment frag = new NewNoteDialogFragment();
        Bundle args = new Bundle();
        args.putInt("title", title);
        args.putString("infobase_name", infobase_name);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int title = getArguments().getInt("title");
        final String infobase_name = getArguments().getString("infobase_name");
        //AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity())
                .setIcon(R.drawable.ic_note_add_black_36dp)
                .setTitle(title)


                .setNegativeButton(R.string.cancel,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dialog.cancel();
                            }
                        }
                );
        View view = getActivity().getLayoutInflater().inflate(R.layout.new_note_dialog, null);
        //View v = inflater.inflate(R.layout.new_note_dialog, container, false);

        //LinearLayout layout = new LinearLayout(alertDialogBuilder.getContext());
        //final EditText editText = new EditText(alertDialogBuilder.getContext());
        //final Spinner typeSpinner = new Spinner(alertDialogBuilder.getContext());
        final EditText editText = (EditText) view.findViewById(R.id.new_note_name);
        final Spinner typeSpinner = (Spinner) view.findViewById(R.id.note_type_spinner);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                getActivity(),
                R.array.note_types,
                android.R.layout.simple_spinner_dropdown_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(adapter);

        alertDialogBuilder.setView(view);

        //alertDialogBuilder.setView(layout);
        alertDialogBuilder.setPositiveButton(R.string.ok,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        //EditText editText = (EditText) getActivity().findViewById(R.id.new_infobase_name);
                        //Log.i("_", Environment.getExternalStorageDirectory().toString() + "/Gamayun/" + editText.getText().toString());
                        String type = "";
                        String typename = typeSpinner.getSelectedItem().toString();
                        if (typename.equals(getString(R.string.name_markup))) {
                            type = getString(R.string.type_markup);
                        }
                        else if (typename.equals(getString(R.string.name_wordcloud))) {
                            type = getString(R.string.type_wordcloud);
                        }
                        else if (typename.equals(getString(R.string.name_graph))) {
                            type = getString(R.string.type_graph);
                        }
                        else if (typename.equals(getString(R.string.name_handwrite))) {
                            type = getString(R.string.type_handwrite);
                        }
                        else if (typename.equals(getString(R.string.name_journal))) {
                            type = getString(R.string.type_journal);
                        }
                        else if (typename.equals(getString(R.string.name_calendar))) {
                            type = getString(R.string.type_calendar);
                        }
                        else if (typename.equals(getString(R.string.name_person))) {
                            type = getString(R.string.type_person);
                        }
                        else if (typename.equals(getString(R.string.name_organization))) {
                            type = getString(R.string.type_organization);
                        }

                        //////////////////////////////////////////////////////
                        File pathDir = new File(
                                Environment.getExternalStorageDirectory().toString() +
                                        "/Gamayun/" + infobase_name
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

                        try {
                            File newFile = new File(pathDir, editText.getText().toString() + "." + type);
                            FileWriter writer = new FileWriter(newFile);
                            writer.append("");
                            writer.flush();
                            writer.close();
                        }
                        catch(IOException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }
        );
        return alertDialogBuilder.create();
    }

}
