package com.educationportal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Iterator;

/**
 * Created by johnearle on 9/27/15.
 */
public class MultipleChoiceTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_choice_task);

        LinearLayout myLayout = (LinearLayout)findViewById(R.id.taskLinear2);

        Intent intent = getIntent();

        String question = intent.getStringExtra("question");
        String[] options = intent.getStringArrayExtra("options");

        for(int i = 0; i < options.length; i++) {
            TextView myButton = new TextView(this);
            myButton.setText(options[i]);

            myLayout.addView(myButton);
        }
    }
}
