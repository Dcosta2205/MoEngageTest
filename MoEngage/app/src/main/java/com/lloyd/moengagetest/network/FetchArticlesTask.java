package com.lloyd.moengagetest.network;

import android.os.AsyncTask;
import android.util.Log;

import com.lloyd.moengagetest.utils.Constants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class FetchArticlesTask extends AsyncTask<Void, Void, String> {
    private String server_response;
    private NetworkResponseListener listener;


    public FetchArticlesTask(NetworkResponseListener listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(Void... voids) {
        Log.d("Lloyd1 ", "thread name " + Thread.currentThread().getName());

        String baseUrl = Constants.BASE_URL;
        URL url;
        HttpURLConnection urlConnection = null;
        if (!isCancelled()) {
            try {
                url = new URL(baseUrl);
                urlConnection = (HttpURLConnection) url.openConnection();

                int responseCode = urlConnection.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    server_response = readStream(urlConnection.getInputStream());
                    if (server_response != null && listener != null) {
                        listener.onSuccess(server_response);
                    }
                } else if (listener != null) {
                    listener.onFailure(responseCode);
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    private String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuffer response = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return response.toString();
    }
}