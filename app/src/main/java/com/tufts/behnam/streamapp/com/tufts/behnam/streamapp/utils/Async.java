package com.tufts.behnam.streamapp.com.tufts.behnam.streamapp.utils;

import android.os.AsyncTask;
import android.util.Log;

import com.tufts.behnam.streamapp.WelcomeActivity;

import java.net.URL;

/**
 * Created by behnam on 5/23/16.
 */
public  class Async extends AsyncTask<URL, Integer, String> {
    protected String doInBackground(URL... urls) {
        int count = urls.length;
        for (int i = 0; i < count; i++) {
            publishProgress((int) ((i / (float) count) * 100));
            // Escape early if cancel() is called
            if (isCancelled()) break;
        }
        return "result";
    }

    protected void onProgressUpdate(Integer... progress) {
    }

    protected void onPostExecute(String result) {
        Log.d(WelcomeActivity.TAG, "OUTPOUT: " + result);
    }
}
