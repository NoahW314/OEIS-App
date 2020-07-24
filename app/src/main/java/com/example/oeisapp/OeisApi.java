package com.example.oeisapp;

import android.app.Activity;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class OeisApi {
    public static final String TAG = "OeisApi";
    private static final Gson gson = new Gson();

    public static void getSequenceData(final Activity activity) throws IOException {
        URL url = new URL("https://oeis.org/search?q=id:A000055&fmt=json");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        int status = con.getResponseCode();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String line;
        StringBuilder json = new StringBuilder();
        while((line = in.readLine()) != null){
            json.append(line);
        }
        con.disconnect();

        SequenceWrapperJson wrapper = gson.fromJson(json.toString(), SequenceWrapperJson.class);
        final Sequence seq = wrapper.getSequence();

        Log.v(TAG, "Code: "+status);
        Log.v(TAG, "Wrapper: "+wrapper);
        Log.v(TAG, "Seq: "+seq);
        Log.v(TAG, "Comments: "+seq.comments);

        activity.runOnUiThread(new Runnable(){
            @Override
            public void run() {
                seq.createAndSetTextViews(activity);
            }
        });
    }
}
