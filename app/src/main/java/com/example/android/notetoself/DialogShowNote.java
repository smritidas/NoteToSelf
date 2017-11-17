package com.example.android.notetoself;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class DialogShowNote extends DialogFragment {

    private Note note;

    @Override
    public Dialog onCreateDialog(Bundle saveInstanceState){

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_show_note, null);

        TextView txtTitle = (TextView) dialogView.findViewById(R.id.txtTitle);
        TextView txtDescription = (TextView) dialogView.findViewById(R.id.txtDescription);
        txtTitle.setText(note.getTitle());
        txtDescription.setText(note.getDescription());

        ImageView imageImportant = (ImageView) dialogView.findViewById(R.id.imageViewImportant);
        ImageView imageTodo = (ImageView) dialogView.findViewById(R.id.imageViewToDo);
        ImageView imageIdea = (ImageView) dialogView.findViewById(R.id.imageViewIdea);

        if(!note.isImportant()){
            imageImportant.setVisibility(View.GONE);
        }

        if(!note.isImportant()){
            imageTodo.setVisibility(View.GONE);
        }

        if(!note.isImportant()){
            imageIdea.setVisibility(View.GONE);
        }

        Button btnOk = (Button) dialogView.findViewById(R.id.btnOK);

        builder.setView(dialogView).setMessage("Your note");

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        return builder.create();

    }

    public void sendNoteSelected(Note noteSelected){
        note = noteSelected;
    }


    public void show(FragmentManager fragmentManager, String s) {
    }
}
