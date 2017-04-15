package com.world.bolandian.implicitintents;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class Favorites extends AppCompatActivity implements View.OnClickListener {

   private ImageButton btnFacebook,btnLinkdin,btnInsta,btnStack,btnYoutube,btnAndroid;
   private EditText etSearch;
   private Button btnSearch,btnCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorits);

        btnFacebook = (ImageButton)findViewById(R.id.btnFacebook);
        btnLinkdin = (ImageButton)findViewById(R.id.btnLinkdin);
        btnInsta = (ImageButton)findViewById(R.id.btnInsta);
        btnStack = (ImageButton)findViewById(R.id.btnStack);
        btnYoutube = (ImageButton)findViewById(R.id.btnYoutube);
        btnAndroid = (ImageButton)findViewById(R.id.btnAndroid);
        etSearch = (EditText)findViewById(R.id.etSearch);
        btnSearch = (Button)findViewById(R.id.btnSearch);
        btnCalendar = (Button)findViewById(R.id.btnCalendar);

        btnSearch.setOnClickListener(this);
        btnCalendar.setOnClickListener(this);

    }


    public void linkClicked(View view) {
        switch (view.getId()){
            case(R.id.btnFacebook):
                Uri uri = Uri.parse("https://www.facebook.com");
                openWeb(uri);
                break;
            case(R.id.btnAndroid):
                Uri android = Uri.parse("https://developer.android.com/index.html");
                openWeb(android);
                break;
            case(R.id.btnInsta):
                Uri insta = Uri.parse("https://www.instagram.com");
                openWeb(insta);
                break;
            case(R.id.btnStack):
                Uri stack = Uri.parse("http://stackoverflow.com/");
                openWeb(stack);
                break;
            case(R.id.btnYoutube):
                Uri youtube = Uri.parse("https://www.youtube.com/");
                openWeb(youtube);
                break;
            case(R.id.btnLinkdin):
                Uri linkdin = Uri.parse("https://www.linkedin.com/");
                openWeb(linkdin);
                break;
        }
    }

    public void openWeb(Uri uri) {
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

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case(R.id.btnSearch):
                String search = etSearch.getText().toString();
                Uri webSearch = Uri.parse("https://www.google.co.il/?gfe_rd=cr&ei=TrXoWOiBOpL38AfpuYXwAQ#q="+search);
                openWeb(webSearch);
                break;

            case(R.id.btnCalendar):
                Intent i = new Intent(Favorites.this, CalendarIntent.class);
                startActivity(i);
        }


    }
}
