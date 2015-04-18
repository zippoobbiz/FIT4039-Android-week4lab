package com.assignment.xiaoduo.week4lab;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class ReminderListActivity extends Activity {

    public final int ADD_REMINDER_CODE = 1;
    List<Reminder> ReminderList;
    ListView reminderList_lv;
    ReminderAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_list);
        reminderList_lv = (ListView) this.findViewById(R.id.reminder_list_lv);
        ReminderList = new ArrayList<Reminder>();
        adapter = new ReminderAdapter(this, ReminderList);
        reminderList_lv.setAdapter(adapter);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_reminder_list, menu);
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
        }else if(id ==R.id.action_add)
        {
            Intent intent = new Intent(ReminderListActivity.this, AddReminderActivity.class);
            startActivityForResult(intent, ADD_REMINDER_CODE);
        }

        return super.onOptionsItemSelected(item);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == ADD_REMINDER_CODE) {
            if(resultCode == RESULT_OK){
                Reminder r = (Reminder)data.getSerializableExtra("reminder");
                ReminderList.add(r);
                adapter.notifyDataSetChanged();
                Log.i("ReminderListActivity", "onActivityResult ADD_REMINDER_CODE RESULT_OK ReminderList.size = " + ReminderList.size());
            }
            if (resultCode == RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }
}
