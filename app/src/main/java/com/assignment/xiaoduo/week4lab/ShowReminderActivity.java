package com.assignment.xiaoduo.week4lab;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;


public class ShowReminderActivity extends Activity {

    EditText title_et, description_et;
    DatePicker dueDate_dp;
    CheckBox completed_cb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_reminder);

        Intent intent = getIntent();
        Reminder reminder = (Reminder) intent.getSerializableExtra("reminder");
        title_et = (EditText)this.findViewById(R.id.title_et);
        description_et = (EditText)this.findViewById(R.id.description_et);
        dueDate_dp = (DatePicker)this.findViewById(R.id.due_date_tp);
        completed_cb = (CheckBox) this.findViewById(R.id.completed_cb);
//        submit_bt = (Button)this.findViewById(R.id.submit_bt);
//        submit_bt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//            }
//
//        });

        title_et.setText(reminder.getTitle());
        description_et.setText(reminder.getDescription());
        Date date = reminder.getDueDate();
        dueDate_dp.init(date.getYear(),date.getMonth(), date.getDay(), null);
        completed_cb.setChecked(reminder.isCompleted());

        title_et.setEnabled(false);
        description_et.setEnabled(false);
        dueDate_dp.setEnabled(false);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_reminder, menu);
        return true;
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
        }else if(id == R.id.action_save)
        {
            Toast.makeText(ShowReminderActivity.this, "Button Clicked", Toast.LENGTH_SHORT).show();
            Reminder r = new Reminder();
            r.setTitle(title_et.getText().toString());
            r.setDescription(description_et.getText().toString());
            r.setDueDate(new Date(dueDate_dp.getYear(),dueDate_dp.getMonth(),dueDate_dp.getDayOfMonth()));
            r.setCompleted(completed_cb.isChecked());

            Intent returnIntent = new Intent();
            returnIntent.putExtra("reminder",r);
            setResult(RESULT_OK, returnIntent);
            finish();

            Log.i("AddReminderActivity", "submit");
        }

        return super.onOptionsItemSelected(item);
    }
}
