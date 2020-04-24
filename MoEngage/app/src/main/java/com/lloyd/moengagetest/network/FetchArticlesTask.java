package com.lloyd.moengagetest.network;

import android.os.AsyncTask;

import com.lloyd.moengagetest.models.Article;
import com.lloyd.moengagetest.models.ArticleItemModel;
import com.lloyd.moengagetest.repository.DataMapper;
import com.lloyd.moengagetest.repository.JsonResponseParser;
import com.lloyd.moengagetest.utils.Constants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

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
        articleList = responseParser.getParsedResponse(serverResponse);
        articleItemModelList = dataMapper.buildData(articleList);
        return articleItemModelList;
    }

    private String openHttpsConnection() {
        String baseUrl = Constants.BASE_URL;
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
        if (listener != null && list!=null) {
            listener.onSuccess(list);
        }
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