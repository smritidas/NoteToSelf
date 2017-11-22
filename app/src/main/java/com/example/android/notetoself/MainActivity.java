package com.example.android.notetoself;


import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.button;
import static android.R.attr.preferenceStyle;
import static android.os.Build.VERSION_CODES.N;



public class MainActivity extends AppCompatActivity {

    private NoteAdapter NoteAdapter;
    private boolean sound;
    private int animOption;
    private SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       NoteAdapter = new NoteAdapter();


        //TODO Wire this up properly
        ListView listNote = (ListView) findViewById(R.id.listview);

        listNote.setAdapter(NoteAdapter);

        listNote.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int whichItem, long id) {

				/*
					Create  a temporary Note
					Which is a reference to the Note
					that has just been clicked
				*/
                Note tempNote = NoteAdapter.getItem(whichItem);

                // Create a new dialog window
                DialogShowNote dialog = new DialogShowNote();

                // Send in a reference to the note to be shown
                dialog.sendNoteSelected(tempNote);

                // Show the dialog window with the note in it
                dialog.show(getFragmentManager(), "");

            }
        });
    }


    public void createNewNote(Note n){

        NoteAdapter.addNote(n);

    }

    @Override
    protected void onResume(){
        super.onResume();
        preferences = getSharedPreferences("Note to self", MODE_PRIVATE);
        sound = preferences.getBoolean("sound", true);
        animOption = preferences.getInt("anim option", SettingsActivity.fast);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }


        if(id == R.id.action_add){
            DialogNewNote dialog = new DialogNewNote();
            dialog.show(getFragmentManager(), "");
            return true;
        }

        return onOptionsItemSelected(item);

    }


    public class NoteAdapter extends BaseAdapter{
        private JSONSerializer serializer;

        List<Note> noteList = new ArrayList<Note>();

        public NoteAdapter(){

            serializer = new JSONSerializer("NoteToSelf.json", MainActivity.this.getApplicationContext());

            try {
                noteList = serializer.load();
            } catch (Exception e) {
                noteList = new ArrayList<Note>();
                Log.e("Error loading notes: ", "", e);
            }

        }

        public void saveNotes(){
            try{
               serializer.save(noteList);

            }catch(Exception e){
                Log.e("Error Saving Notes","", e);
            }
        }

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

            if(view == null){

                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                view = inflater.inflate(R.layout.listitem, viewGroup, false);

            }

            TextView txtTitle = (TextView) view.findViewById(R.id.txtTitle);
            TextView txtDescription = (TextView) view.findViewById(R.id.txtDescription);
            ImageView imageImportant = (ImageView) view.findViewById(R.id.imageViewImportant);
            ImageView imageToDo = (ImageView) view.findViewById(R.id.imageViewToDo);
            ImageView imageIdea = (ImageView) view.findViewById(R.id.imageViewIdea);

            Note tempNote = noteList.get(whichItem);

            if(!tempNote.isImportant()){
                imageImportant.setVisibility(View.GONE);
            }

            if(!tempNote.isImportant()){
                imageToDo.setVisibility(View.GONE);
            }

            if(!tempNote.isImportant()){
                imageIdea.setVisibility(view.GONE);
            }

            txtTitle.setText(tempNote.getTitle());
            txtDescription.setText(tempNote.getDescription());

            return view;


        }

        public void addNote(Note n){

            noteList.add(n);
            notifyDataSetChanged();

        }


    }

    @Override
    protected void onPause(){
        super.onPause();

        NoteAdapter.saveNotes();

    }


}
