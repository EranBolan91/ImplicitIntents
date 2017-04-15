package com.world.bolandian.implicitintents;

import android.annotation.TargetApi;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;


public class CalendarIntent extends AppCompatActivity {

    private CalendarView calc;
    private Button btnSet,btnFavorit;
    private EditText etEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        setTitle("CalendarIntent");

        calc = (CalendarView) findViewById(R.id.calendarView);
        btnSet = (Button)findViewById(R.id.btnSet);
        etEvent = (EditText)findViewById(R.id.etEvent);
        btnFavorit = (Button)findViewById(R.id.btnFavorit);

        btnFavorit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CalendarIntent.this,ContactIntent.class);
                startActivity(i);
            }
        });

        btnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEvent(etEvent.getText().toString(),"holon",3,4);
            }
        });

    }

    @TargetApi(24)
    public void addEvent(String title,String location, long begin, long end) {
        Calendar c = Calendar.getInstance();
        Intent intent = new Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.Events.TITLE, title)
                .putExtra(CalendarContract.Events.EVENT_LOCATION, location)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, c.getTimeInMillis())
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, c.getTimeInMillis()+60*60*1000);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}


