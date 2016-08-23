package com.example.foobar.heloeworldrest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

public class DisplayUserInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_user_info);

        Intent intent = getIntent();
        String username = intent.getStringExtra(MainActivity.EXTRA_USERNAME);
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText(username);

        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_display_user_info);
        layout.addView(textView);

    }
}
