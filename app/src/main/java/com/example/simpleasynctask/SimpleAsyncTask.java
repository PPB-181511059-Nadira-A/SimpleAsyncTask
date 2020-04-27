package com.example.simpleasynctask;

import android.os.AsyncTask;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

public class SimpleAsyncTask
        extends AsyncTask <String, Integer, String>{
    private WeakReference<TextView> mTextView;
    private WeakReference<TextView> mTextView2;
    SimpleAsyncTask(TextView tv) {
        mTextView = new WeakReference<>(tv);
        mTextView2 = new WeakReference<>(tv);
    }
    @Override
    protected String doInBackground(String... voids) {
        // Generate a random number between 0 and 10
        Random r = new Random();
        int n = r.nextInt(11);

        // Make the task take long enough that we have
        // time to rotate the phone while it is running
        int s = n * 200;

        // Sleep for the random amount of time
        try {
            for (int i = 0; i < s; i++){
                Thread.sleep(s);
                publishProgress(i);

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Return a String result
        return "Awake at last after sleeping for " + s + " milliseconds!";
    }


    protected void onPostExecute(String result) {
        mTextView.get().setText(result);
    }

    protected void onProgressUpdate(Integer... values) {
        mTextView2.get().setText("completed.."+ values[0]+"%");
    }

}

