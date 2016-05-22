package com.tufts.behnam.streamapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class WelcomeActivity extends AppCompatActivity {

    public static final String EXTRA_STREAM_COUNT = "extra_stream_count";
    private Button bSingle;
    private Button bMulti;
    private EditText etStreamCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bSingle = (Button) findViewById(R.id.button3);
        bMulti = (Button) findViewById(R.id.button4);
        etStreamCount = (EditText) findViewById(R.id.editText_streamcount);

        final Intent single = new Intent(this, SingleActivity.class);
        final Intent multi = new Intent(this, MultiStream.class);

        bSingle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(single);
            }
        });

        bMulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = etStreamCount.getText().toString();
                if (text.isEmpty()) {
                    Toast.makeText(WelcomeActivity.this, "Please enter a stream count number",
                            Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        int streamCount = Integer.parseInt(text);
                        if (0 < streamCount && streamCount < 100) {
                            multi.putExtra(EXTRA_STREAM_COUNT, streamCount);
                            startActivity(multi);
                        }  else {
                            Toast.makeText(WelcomeActivity.this, "The number of streams should be" +
                                    "less than 100 and greater than zero.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    } catch (IllegalArgumentException exception) {

                        Toast.makeText(WelcomeActivity.this,
                                "Please enter a valid integer",
                                Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Nothing yet!", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                bMulti.callOnClick();
            }
        });
    }

}
