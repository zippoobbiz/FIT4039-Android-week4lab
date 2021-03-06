package com.assignment.xiaoduo.week4lab;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class ReminderListActivity extends Activity {

    public final int ADD_REMINDER_CODE = 1;
    public final int EDIT_REMINDER_CODE = 2;
    List<Reminder> ReminderList;
    ListView reminderList_lv;
    ReminderAdapter adapter;
    private int currentPosition = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_list);
        reminderList_lv = (ListView) this.findViewById(R.id.reminder_list_lv);
        ReminderList = new ArrayList<Reminder>();
        adapter = new ReminderAdapter(this, ReminderList);
        reminderList_lv.setAdapter(adapter);
        reminderList_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                currentPosition = arg2;
                Intent intent = new Intent(ReminderListActivity.this, ShowReminderActivity.class);
                intent.putExtra("reminder",ReminderList.get(arg2));
                startActivityForResult(intent, EDIT_REMINDER_CODE);
            }

        });


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

                Collections.sort(ReminderList, new Comparator<Reminder>() {
                    @Override
                    public int compare(Reminder reminder1, Reminder reminder2) {

                        return reminder1.getDueDate().compareTo(reminder2.getDueDate());
                    }
                });
                adapter.notifyDataSetChanged();
                Log.i("ReminderListActivity", "onActivityResult ADD_REMINDER_CODE RESULT_OK ReminderList.size = " + ReminderList.size());
            }
            if (resultCode == RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }else if(requestCode == EDIT_REMINDER_CODE)
        {
            if(resultCode == RESULT_OK){
                Reminder r = (Reminder)data.getSerializableExtra("reminder");
                ReminderList.set(currentPosition,r);
                adapter.notifyDataSetChanged();
                Log.i("ReminderListActivity", "onActivityResult ADD_REMINDER_CODE RESULT_OK ReminderList.size = " + ReminderList.size());
            }
            if (resultCode == RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }
}
