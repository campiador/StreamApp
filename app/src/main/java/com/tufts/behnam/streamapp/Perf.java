package com.tufts.behnam.streamapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by behnam on 5/23/16.
 */
public class Perf {

    public Perf() {

    }

    public String record() {

        try {
            Process proc = Runtime.getRuntime().exec("/data/perf record -a -F 1000 sleep 5");
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(proc.getInputStream()));
            return bufferedReader.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    public String stat() {
        try {
            String output = "";
            Runtime.getRuntime().exec("su");
            Process proc = Runtime.getRuntime()
                    .exec("/data/perf stat -B dd if=/dev/zero of=/dev/null count=1");
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



}
