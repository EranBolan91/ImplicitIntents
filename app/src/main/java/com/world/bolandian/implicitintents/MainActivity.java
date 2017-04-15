package com.world.bolandian.implicitintents;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TimePicker timePicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timePicker = (TimePicker)findViewById(R.id.timePicker);

        Intent intent = getIntent();
        if(intent != null){
           int hour = intent.getIntExtra(AlarmClock.EXTRA_HOUR,0);
           int minutes = intent.getIntExtra(AlarmClock.EXTRA_MINUTES,0);
            timePicker.setCurrentHour(hour);
            timePicker.setCurrentMinute(minutes);
        }

    }

    public void Dial(View view) {
        //this is the data to add to the intent action
        Uri tel = Uri.parse("tel:0524755343");

        Intent dial = new Intent(Intent.ACTION_DIAL,/*action */tel /*data*/);
        startActivity(dial);

    }

    public void website(View view) {
        Uri uri = Uri.parse("https://www.google.com");
        Intent webIntent = new Intent(Intent.ACTION_VIEW,uri);
        if(canOpen(webIntent)) {
            startActivity(webIntent);
        }else{
            Toast.makeText(this, "No browser to use", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean canOpen(Intent intent) {
        return intent.resolveActivity(getPackageManager()) != null;
    }

    public void setTime(View view) {
        int minutes = 0;
        int hours = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            minutes = timePicker.getMinute();
            hours = timePicker.getHour();
        }else{
            hours = timePicker.getCurrentHour();
            minutes = timePicker.getCurrentMinute();
        }
        createAlarm("Hello World",hours,minutes);

    }

    public void createAlarm(String message,int hour, int minutes){
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                .putExtra(AlarmClock.EXTRA_MESSAGE,message)
                .putExtra(AlarmClock.EXTRA_HOUR,hour)
                .putExtra(AlarmClock.EXTRA_VIBRATE,true)
                .putExtra(AlarmClock.EXTRA_MINUTES,minutes)
                .putExtra(AlarmClock.EXTRA_SKIP_UI,true);
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }
        Toast.makeText(this, "Time has set", Toast.LENGTH_SHORT).show();
    }
}
