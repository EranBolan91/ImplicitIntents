package com.world.bolandian.implicitintents;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class ContactIntent extends AppCompatActivity {

    private Button btnDone,btnFavorit;
    private RadioGroup rg;
    private EditText etName,etPhone,etEmail;
    static final int REQUEST_SELECT_PHONE_NUMBER = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_intent);
        setTitle("Contact");

        rg = (RadioGroup)findViewById(R.id.radioGroup);
        etName = (EditText)findViewById(R.id.etName);
        etPhone = (EditText)findViewById(R.id.etPhone);
        etEmail = (EditText)findViewById(R.id.etEmail);
        btnDone = (Button)findViewById(R.id.btnDone);
        btnFavorit = (Button)findViewById(R.id.btnFavorits);

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (rg.getCheckedRadioButtonId()){
                    case(R.id.radioAdd):
                        insertContact(etName.getText().toString(),
                                       etEmail.getText().toString(),
                                       etPhone.getText().toString());
                        break;
                    case(R.id.radioUpdate):
                        selectContact();
                        break;
                }
            }
        });

        btnFavorit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ContactIntent.this,Favorites.class);
                startActivity(i);
            }
        });
    }

    public void selectContact() {
        // Start an activity for the user to pick a phone number from contacts
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_SELECT_PHONE_NUMBER);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SELECT_PHONE_NUMBER && resultCode == RESULT_OK) {
            // Get the URI and query the content provider for the phone number
            Uri contactUri = data.getData();
            String[] projection = new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER};
            Cursor cursor = getContentResolver().query(contactUri, projection,
                    null, null, null);
            // If the cursor returned is valid, get the phone number
            if (cursor != null && cursor.moveToFirst()) {
                int numberIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                String number = cursor.getString(numberIndex);
                editContact(contactUri ,"walla");
            }
        }
    }

    public void insertContact(String name, String email , String phone) {
        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setType(ContactsContract.Contacts.CONTENT_TYPE);
        intent.putExtra(ContactsContract.Intents.Insert.PHONE, phone);
        intent.putExtra(ContactsContract.Intents.Insert.NAME, name);
        intent.putExtra(ContactsContract.Intents.Insert.EMAIL, email);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
        clean();
    }

    public void editContact(Uri contactUri, String email) {
        Intent intent = new Intent(Intent.ACTION_EDIT);
        intent.setData(contactUri);
        intent.putExtra(ContactsContract.Intents.Insert.EMAIL, email);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void clean(){
        etEmail.setText("");
        etName.setText("");
        etPhone.setText("");
    }
}
