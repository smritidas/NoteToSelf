package com.example.android.notetoself;

/**
 * Note to self app
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Note TempNote = new Note();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogShowNote dialog = new DialogShowNote();
                dialog.sendNoteSelected(TempNote);
                dialog.show(getFragmentManager(), "123");
            }
        });

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


}
