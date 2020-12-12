package com.example.loadimagefrominternetusingasynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv=findViewById(R.id.imageView);
        String link = "https://www.thesprucepets.com/thmb/dCuBQtyCYUH9eoUQOkfEgToH95s=/1535x1151/smart/filters:no_upscale()/Stocksy_txp33a24e10lxw100_Medium_214761-5af9d6d7875db900360440a7.jpg?fbclid=IwAR1mBIQ-yY45CXT0lbEYTqxzZBV5RY1V_hcom4P-JCWbu63jNYmy1Lvl4cM";
        new imageLoadingTask().execute(link);


    }

    public class imageLoadingTask extends AsyncTask<String, Void, Bitmap>{

        @Override
        protected Bitmap doInBackground(String... strings) {
            //eta bg thread a run kore

            String image= strings[0];

            try {
                URL url = new URL(image);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                httpURLConnection.connect();
                Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
                return bitmap;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            //eta main thread a run kore

            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            //ui update korbo ekhane, etao main thread a run kore

            iv.setImageBitmap(bitmap);
            super.onPostExecute(bitmap);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            //main tread a run hy, doinbg er sathe prlly kaj klore
            super.onProgressUpdate(values);
        }
    }
}
