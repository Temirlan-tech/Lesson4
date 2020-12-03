package com.example.lesson4;

import androidx.annotation.LongDef;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    public static final String EXTRA_TEXT = "btn";
    public static final String EXTRA_TEXT1 = "text";
    private EditText editText;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent = getIntent();
        String text = intent.getStringExtra(EXTRA_TEXT);
        TextView textView = findViewById(R.id.textView);
        textView.setText(text);
        init();
        Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendFirstActivity();
            }
        });
    }

    public void init(){
        editText = findViewById(R.id.etText);
    }

    public void sendFirstActivity (){
        String messageText = editText.getText().toString();
        Intent intent = new Intent();
        intent.putExtra(MainActivity.EXTRA_TEXT1, messageText);
        setResult(RESULT_OK,intent);
        finish();
    }
}