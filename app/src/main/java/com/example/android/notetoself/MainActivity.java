package com.example.android.notetoself;

/**
 * Note to self app
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.button;

public class MainActivity extends AppCompatActivity {

    Note TempNote = new Note();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





//        Button button = (Button) findViewById(R.id.button);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                DialogShowNote dialog = new DialogShowNote();
//                dialog.sendNoteSelected(TempNote);
//                dialog.show(getFragmentManager(), "123");
//            }
//        });

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

//        if (id == R.id.action_settings){
//            return true;
//        }

        if(id == R.id.action_add){
            DialogNewNote dialog = new DialogNewNote();
            dialog.show(getFragmentManager(), "");
            return true;
        }

        return onOptionsItemSelected(item);

    }


    public class NoteAdapter extends BaseAdapter{

        List<Note> noteList = new ArrayList<Note>();

        @Override
        public int getCount(){
            return noteList.size();
        }

        @Override
        public Note getItem(int whichItem){
            return noteList.get(whichItem);
        }

        @Override
        public long getItemId(int whichItem){
            return whichItem;
        }

        @Override
        public View getView(int whichItem, View view, ViewGroup viewGroup){
            return view;
        }

    }

}
