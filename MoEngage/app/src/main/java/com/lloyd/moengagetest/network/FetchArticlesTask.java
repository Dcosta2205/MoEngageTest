package com.lloyd.moengagetest.network;

import android.os.AsyncTask;

import com.lloyd.moengagetest.models.Article;
import com.lloyd.moengagetest.models.ArticleItemModel;
import com.lloyd.moengagetest.repository.DataMapper;
import com.lloyd.moengagetest.repository.JsonResponseParser;
import com.lloyd.moengagetest.utils.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Async task to fetch the API response from the server.
 */
public class FetchArticlesTask extends AsyncTask<Void, Void, List<ArticleItemModel>> {
    private List<Article> articleList;
    private List<ArticleItemModel> articleItemModelList;
    private DataMapper dataMapper = new DataMapper();
    private NetworkResponseListener listener;
    private JsonResponseParser responseParser = new JsonResponseParser();

    public FetchArticlesTask(NetworkResponseListener listener) {
        this.listener = listener;
    }

    @Override
    protected List<ArticleItemModel> doInBackground(Void... voids) {
        String serverResponse = openHttpsConnection();
        /*
        Parses the JSON response into the required model class.
         */
        articleList = responseParser.getParsedResponse(serverResponse);

        /*
        Builds the data to display in the UI.
         */
        articleItemModelList = dataMapper.buildData(articleList);
        return articleItemModelList;
    }

    /**
     * Fetches the response from the server.
     *
     * @return returns a json response in the form of string.
     */
    private String openHttpsConnection() {
        String baseUrl = Utils.BASE_URL;
        String server_response = "";
        URL url;
        HttpURLConnection urlConnection = null;
        if (!isCancelled()) {
            try {
                url = new URL(baseUrl);
                urlConnection = (HttpURLConnection) url.openConnection();

                int responseCode = urlConnection.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    server_response = readStream(urlConnection.getInputStream());
                } else {
                    listener.onFailure(responseCode);
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return server_response;
    }

    @Override
    protected void onPostExecute(List<ArticleItemModel> list) {
        super.onPostExecute(list);

        /*
        Post the data to the repository class to update viewmodel about the changes.
         */
        if (listener != null && list != null) {
            listener.onSuccess(list);
        }
    }

    /**
     * Reads the input stream of data from the server and converts it to string format.
     *
     * @param in stream of data
     * @return returns input stream in the string format.
     */
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