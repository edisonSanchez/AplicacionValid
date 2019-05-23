package com.poc.myapplication;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class DetailsTop extends AppCompatActivity {

    private ArrayList<ItemTop> lista;
    private String title;
    private DetailsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_top);

        lista = new ArrayList<>();
        title = getIntent().getStringExtra("title");
        String url = getIntent().getStringExtra("url");
        ListView listView = findViewById(R.id.list);
        TextView txtTitle = findViewById(R.id.txtTitleList);
        txtTitle.setText(title);
        adapter = new DetailsAdapter(this, new ArrayList<ItemTop>());
        listView.setAdapter(adapter);
        AsynckTasckDeatils asynckTask = new AsynckTasckDeatils();
        asynckTask.execute(url);
    }

    public class AsynckTasckDeatils extends AsyncTask<String, Void, ArrayList<ItemTop>>{

        @Override
        protected ArrayList<ItemTop> doInBackground(String... strings) {

            HttpURLConnection connection = null;
            InputStreamReader reader = null;
            InputStream stream = null;
            try{
                URL url = new URL(strings[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setReadTimeout(10000);
                connection.setConnectTimeout(10000);
                connection.connect();
                int code = connection.getResponseCode();
                stream = connection.getInputStream();
                reader = new InputStreamReader(stream, Charset.forName("UTF-8"));
                BufferedReader read = new BufferedReader(reader);
                StringBuilder builder = new StringBuilder();
                String line = read.readLine();
                while(line != null){
                    builder.append(line);
                    line = read.readLine();
                }
                JSONObject object = new JSONObject(builder.toString());
                ItemTop item;
                if(title.equals("Top Artistas")){
                    JSONObject topArtist = object.getJSONObject("topartists");
                    JSONArray array = topArtist.getJSONArray("artist");
                    for(int i=0; i<array.length(); i++){
                        JSONObject artist = array.getJSONObject(i);
                        String name = artist.getString("name");
                        String oyentes = artist.getString("listeners");
                        String urlJ = artist.getString("url");
                        item = new ItemTop(name,oyentes,urlJ);
                        lista.add(item);
                    }
                }else if(title.equals("Top Canciones")){
                    JSONObject tracks = object.getJSONObject("tracks");
                    JSONArray array = tracks.getJSONArray("track");
                    for(int i=0; i<array.length(); i++){
                        JSONObject artist = array.getJSONObject(i);
                        String name = artist.getString("name");
                        String oyentes = artist.getString("listeners");
                        String urlJ = artist.getString("url");
                        item = new ItemTop(name,oyentes,urlJ);
                        lista.add(item);
                    }
                }

            }catch(IOException | JSONException io){
                io.getStackTrace();
            }finally {
                if(connection != null){
                    connection.disconnect();
                }
                if(stream != null && reader != null){
                    try {
                        stream.close();
                        reader.close();
                    }catch(IOException io){
                        io.getStackTrace();
                    }
                }
            }
            return lista;
        }

        @Override
        protected void onPostExecute(ArrayList<ItemTop> itemTops) {
            super.onPostExecute(itemTops);
            adapter.clear();
            adapter.addAll(itemTops);
        }
    }
}
