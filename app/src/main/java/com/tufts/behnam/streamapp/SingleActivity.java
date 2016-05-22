package com.tufts.behnam.streamapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class SingleActivity extends AppCompatActivity {

    private final static String SAMPLE_HLS_URL =
            "http://qthttp.apple.com.edgesuite.net/1010qwoeiuryfg/sl.m3u8";
    private final static String SAMPLE_RTSP_URL =
            "rtsp://184.72.239.149/vod/mp4:BigBuckBunny_115k.mov";



    private Button bHLS;
    private Button bRTSP;
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bHLS = (Button) findViewById(R.id.button2);
        bRTSP = (Button) findViewById(R.id.button);

        videoView = (VideoView) findViewById(R.id.videoView);

        bHLS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(SingleActivity.this, "Loading HLS Stream", Toast.LENGTH_LONG).show();
                videoView.setVideoPath(SAMPLE_HLS_URL);
                videoView.setMediaController(new MediaController(SingleActivity.this));
            }
        });

        bHLS.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(SingleActivity.this, "Long click on HLS!", Toast.LENGTH_LONG).show();

                return false;
            }
        });

        bRTSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SingleActivity.this, "Loading RTSP Stream", Toast.LENGTH_LONG).show();

                videoView.setVideoPath(SAMPLE_RTSP_URL);
                videoView.setMediaController(new MediaController(SingleActivity.this));
            }
        });


    }



    public static String getUrlVideoRTSP(String urlYoutube) {

        try {
            String gdy = "http://gdata.youtube.com/feeds/base/videos/-/justinbieber?" +
                    "orderby=published&alt=rss&client=ytapi-youtube-rss-redirect&v=2";
            DocumentBuilder documentBuilder = DocumentBuilderFactory
                    .newInstance().newDocumentBuilder();
            String id = extractYoutubeId(urlYoutube);
            URL url = new URL(gdy + id);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            Document doc = documentBuilder.parse(connection.getInputStream());
            Element el = doc.getDocumentElement();
            NodeList list = el.getElementsByTagName("media:content");// /media:content
            String cursor = urlYoutube;
            for (int i = 0; i < list.getLength(); i++) {
                Node node = list.item(i);
                if (node != null) {
                    NamedNodeMap nodeMap = node.getAttributes();
                    HashMap<String, String> maps = new HashMap<String, String>();
                    for (int j = 0; j < nodeMap.getLength(); j++) {
                        Attr att = (Attr) nodeMap.item(j);
                        maps.put(att.getName(), att.getValue());
                    }
                    if (maps.containsKey("yt:format")) {
                        String f = maps.get("yt:format");
                        if (maps.containsKey("url")) {
                            cursor = maps.get("url");
                        }
                        if (f.equals("1"))
                            return cursor;
                    }
                }
            }
            return cursor;
        } catch (Exception ex) {
            Log.e("Get RTSP Exception ", ex.toString());
        }
        return urlYoutube;
    }

    private static String extractYoutubeId(String url) {

        return url;
    }
}