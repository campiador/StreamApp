package com.tufts.behnam.streamapp.com.tufts.behnam.streamapp.utils;

import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.util.Log;

import com.tufts.behnam.streamapp.WelcomeActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by behnam on 5/23/16.
 */



public class Perf {

    public Perf() {

    }

    public String record() {
//asd
        try {
            String output = "";
            Runtime.getRuntime().exec("su");
            //BUGFIX: have to switch to the /data directory, and it does not work with cd data
            Process proc=Runtime.getRuntime().exec("./perf record -v " +
                    "-e 'skb:consume_skb' -o /data/data/com.tufts.behnam.streamapp/perf.data " +
                    "-a -F -g 1000 sleep 5",
                    null, new File("/data/"));
            proc.waitFor();

            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(proc.getInputStream()));
            BufferedReader stdError = new BufferedReader(new
                    InputStreamReader(proc.getErrorStream()));

            String line;
            output = output + "\nSTDOUT:\n";

            while((line = bufferedReader.readLine() )!= null) {
                output = output + line;
            }

            output = output + "\nSTDERROR:\n";

            while((line = stdError.readLine() )!= null) {
                output = output + line;
            }


            return output;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            Log.e(WelcomeActivity.TAG, "Error while waiting for proc");

            e.printStackTrace();

        }
        return null;

    }

    public String stat() {
        try {
            String output = "";
            Runtime.getRuntime().exec("su");
//            Runtime.getRuntime().exec("ls", null, new File("/data/"));
            Process proc = Runtime.getRuntime()
                    .exec("./perf stat -B dd if=/dev/zero of=/dev/null count=1000",
//                    .exec("pwd",
                            null, new File("/data/"));
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(proc.getInputStream()));

            BufferedReader stdError = new BufferedReader(new
                    InputStreamReader(proc.getErrorStream()));

            String line;
            output = output + "\nSTDOUT:\n";

            while((line = bufferedReader.readLine() )!= null) {
                output = output + line;
            }

            output = output + "\nSTDERR:\n";

            while((line = stdError.readLine() )!= null) {
                output = output + line;
            }

            return output;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String echo(String text){
        try {
            String output = "";
            Runtime.getRuntime().exec("su");
            Process proc = Runtime.getRuntime()
                    .exec("echo 'text from perf java object:' " + text);
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(proc.getInputStream()));
            String line;
            while((line = bufferedReader.readLine() )!= null) {
                output = output + line;
            }

            return output;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void test() {
        new AsyncStat().execute("command");

    }

    public  class AsyncStat extends AsyncTask<String, Integer, String> {
        protected String doInBackground(String... commands) {
            String res = "";
            for (int i = 0; i < commands.length; i++) {
                publishProgress((int) ((i / (float) commands.length) * 100));
                res = stat();

                // Escape early if cancel() is called
                if (isCancelled()) break;
            }
            return res;
        }

        protected void onProgressUpdate(Integer... progress) {
        }

        protected void onPostExecute(String result) {
            Log.d(WelcomeActivity.TAG, "OUTPOUT: " + result);
        }
    }

    @Nullable
    private String perf_test(){
        try {
            String output = "";
            Runtime.getRuntime().exec("su");
            Process proc = Runtime.getRuntime()
                    .exec("/data/perf");
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(proc.getInputStream()));
            String line;
            while((line = bufferedReader.readLine() )!= null) {
                output = output + line;
            }

            return output;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String report() {
        try {
            String output = "";
            Runtime.getRuntime().exec("su");
    //            Runtime.getRuntime().exec("ls", null, new File("/data/"));
            Process proc = Runtime.getRuntime()
                    .exec("./perf report -i /data/data/com.tufts.behnam.streamapp/perf.data",
    //                    .exec("pwd",
                            null, new File("/data/"));
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(proc.getInputStream()));

            BufferedReader stdError = new BufferedReader(new
                    InputStreamReader(proc.getErrorStream()));

            String line;
            output = output + "\nSTDOUT:\n";

            while((line = bufferedReader.readLine() )!= null) {
                output = output + line;
            }

            output = output + "\nSTDERR:\n";

            while((line = stdError.readLine() )!= null) {
                output = output + line;
            }

            return output;
    } catch (IOException e) {
            e.printStackTrace();
        }
    return null;
    }

    public String skbTest(){
        try {
            String output = "";
            Runtime.getRuntime().exec("su");
            //            Runtime.getRuntime().exec("ls", null, new File("/data/"));
            Process proc = Runtime.getRuntime()
                    .exec("./perf report -i /data/data/com.tufts.behnam.streamapp/perf.data" +
                            "-e 'skb:consume_skb'",

                            //                    .exec("pwd",
                            null, new File("/data/"));
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(proc.getInputStream()));

            BufferedReader stdError = new BufferedReader(new
                    InputStreamReader(proc.getErrorStream()));

            String line;
            output = output + "\nSTDOUT:\n";

            while((line = bufferedReader.readLine() )!= null) {
                output = output + line;
            }

            output = output + "\nSTDERR:\n";

            while((line = stdError.readLine() )!= null) {
                output = output + line;
            }

            return output;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }




}
