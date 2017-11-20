package com.example.android.notetoself;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SettingsActivity extends AppCompatActivity {

    private SharedPreferences.Editor editor;
    private SharedPreferences preferences;

    private boolean sound;

    public static final int fast = 0;
    public static final int slow = 1;
    public static final int none = 2;
    private int animOption;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        preferences = getSharedPreferences("Note to self", MODE_PRIVATE);
        editor = preferences.edit();
        sound =  preferences.getBoolean("sound", true);

        CheckBox checkBoxSound = (CheckBox) findViewById(R.id.checkBoxSound);
        if(sound){
            checkBoxSound.setChecked(true);
        }else{
            checkBoxSound.setChecked(false);
        }

        checkBoxSound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                Log.i("sound = ", "" + sound);
                Log.i("isChecked = ", "" + isChecked);
                sound = ! sound;
                editor.putBoolean("sound", sound);


            }
        });

        animOption = preferences.getInt("anim option", fast);

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.clearCheck();
        switch(animOption){

            case fast:
                radioGroup.check(R.id.radioFast);
                break;

            case slow:
                radioGroup.check(R.id.radioSlow);
                break;

            case none:
                radioGroup.check(R.id.radioNone);
                break;

        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {

                    switch (rb.getId()){

                        case R.id.radioFast:
                            animOption = fast;
                            break;

                        case R.id.radioSlow:
                            animOption = slow;
                            break;

                        case R.id.radioNone:
                            animOption = none;
                            break;


                    }
                    // End switch block

                    editor.putInt("anim option", animOption);

                }

            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemsSelected(MenuItem item){
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Save the settings here
        editor.commit();
    }

}
