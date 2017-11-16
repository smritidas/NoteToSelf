package com.example.android.notetoself;


import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class DialogNewNote extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_new_note, null);

        final EditText editTitle = (EditText) dialogView.findViewById(R.id.editTitle);
        final EditText editDescription = (EditText) dialogView.findViewById(R.id.editDescription);
        final CheckBox checkBoxIdea = (CheckBox) dialogView.findViewById(R.id.checkBoxIdea);
        final CheckBox checkBoxTodo = (CheckBox) dialogView.findViewById(R.id.checkBoxToDo);
        final CheckBox checkBoxImp = (CheckBox) dialogView.findViewById(R.id.checkBoxImportant);
        Button btnCancel = (Button) dialogView.findViewById(R.id.btnCancel);
        Button btnOk = (Button) dialogView.findViewById(R.id.btnOK);

        builder.setView(dialogView).setMessage("Add a new note");

        //Handle the cancel button
        btnCancel.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                dismiss();
            }

        });

        btnOk.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Note note = new Note();

                //Variables from note to the form
                note.setTitle(editTitle.getText().toString());
                note.setDescription(editDescription.getText().toString());
                note.setIdea(checkBoxIdea.isChecked());
                note.setTodo(checkBoxTodo.isChecked());
                note.setImportant(checkBoxImp.isChecked());

                MainActivity activity = (MainActivity) getActivity();

                //activity.createNote(note);

                dismiss();

            }
                                 });


        return builder.create();
    }
}
