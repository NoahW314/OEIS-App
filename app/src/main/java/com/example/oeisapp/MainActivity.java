package com.example.oeisapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private Runnable oeisApiRunnable = new Runnable() {
        @Override
        public void run() {
            try {
                OeisApi.getSequenceData(MainActivity.this);
            } catch(IOException e){
                Log.e("OEIS_Activity", "IOException!", e);
            }
        }
    };
    public void handleSequenceButton(View v){
        Thread t = new Thread(oeisApiRunnable);
        t.start();
    }
}
