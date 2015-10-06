package com.educationportal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView l1;

    String Q1 = "What color is the sky?";
    String[] Q1options = {"yellow", "green", "red", "blue"};
    int Q1answer = 3;

    String Q2 = "How many inches in one foot?";
    String[] Q2options = {"1", "30", "100", "12", "10"};
    int Q2answer = 3;

    MultipleChoiceTask task1 = new MultipleChoiceTask(Q1, Q1options, Q1answer);
    MultipleChoiceTask task2 = new MultipleChoiceTask(Q2, Q2options, Q2answer);

    ArrayList<MultipleChoiceTask> mcTasks = new ArrayList<MultipleChoiceTask>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mcTasks.add(task1);
        mcTasks.add(task2);
        l1=(ListView)findViewById(R.id.list);
        l1.setAdapter(new dataListAdapter(mcTasks));
    }

    class dataListAdapter extends BaseAdapter {
        ArrayList<MultipleChoiceTask> tasks;

        dataListAdapter() {
            tasks = null;
        }

        public dataListAdapter(ArrayList<MultipleChoiceTask> t) {
            tasks = t;

        }

        public int getCount() {
            // TODO Auto-generated method stub
            return tasks.size();
        }

        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return null;
        }

        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            final int POS = position;
            LayoutInflater inflater = getLayoutInflater();
            View row;
            row = inflater.inflate(R.layout.task_row_layout, parent, false);
            TextView title;
            title = (TextView) row.findViewById(R.id.taskTitle);
            title.setText(tasks.get(position).question);

            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goToTask(v, tasks.get(POS));
                }
            });

            return (row);
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void goToTask(View view, MultipleChoiceTask task) {
        Intent intent = new Intent(this, MultipleChoiceTaskActivity.class);
        intent.putExtra("question", task.question);
        intent.putExtra("options", task.options);
        intent.putExtra("answer", task.answer);


        startActivityForResult(intent, 1);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
