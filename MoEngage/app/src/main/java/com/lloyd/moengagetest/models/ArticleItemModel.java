package com.lloyd.moengagetest.models;

import android.text.Spannable;

public class ArticleItemModel {
    private Spannable author;

    private Spannable title;

    private Spannable description;

    private String url;

    private String urlToImage;

    private Spannable publishedAt;


    private long timeStamp;

    private String id;

    private Spannable content;


    public ArticleItemModel(ArticleItemModelBuilder articleItemModelBuilder) {
        this.author = articleItemModelBuilder.author;
        this.title = articleItemModelBuilder.title;
        this.description = articleItemModelBuilder.description;
        this.url = articleItemModelBuilder.url;
        this.urlToImage = articleItemModelBuilder.urlToImage;
        this.publishedAt = articleItemModelBuilder.publishedAt;
        this.timeStamp = articleItemModelBuilder.timeStamp;
        this.id = articleItemModelBuilder.id;
        this.content = articleItemModelBuilder.content;
    }

    public String getId() {
        return id;
    }


    public long getTimeStamp() {
        return timeStamp;
    }


    public Spannable getAuthor() {
        return author;
    }


    public Spannable getTitle() {
        return title;
    }


    public Spannable getDescription() {
        return description;
    }


    public String getUrl() {
        return url;
    }


    public String getUrlToImage() {
        return urlToImage;
    }


    public Spannable getPublishedAt() {
        return publishedAt;
    }


    public Spannable getContent() {
        return content;
    }


   public static class ArticleItemModelBuilder {
        private Spannable author;

        private Spannable title;

        private Spannable description;

        private String url;

        private String urlToImage;

        private Spannable publishedAt;


        private long timeStamp;

        private String id;

        private Spannable content;


        public ArticleItemModelBuilder setAuthor(Spannable author) {
            this.author = author;
            return this;
        }

        public ArticleItemModelBuilder setTitle(Spannable title) {
            this.title = title;
            return this;
        }

        public ArticleItemModelBuilder setDescription(Spannable description) {
            this.description = description;
            return this;
        }


        public ArticleItemModelBuilder setUrl(String url) {
            this.url = url;
            return this;
        }


        public ArticleItemModelBuilder setUrlToImage(String urlToImage) {
            this.urlToImage = urlToImage;
            return this;
        }

        public ArticleItemModelBuilder setPublishedAt(Spannable publishedAt) {
            this.publishedAt = publishedAt;
            return this;
        }

        public ArticleItemModelBuilder setTimeStamp(long timeStamp) {
            this.timeStamp = timeStamp;
            return this;
        }

        public ArticleItemModelBuilder setId(String id) {
            this.id = id;
            return this;
        }


        public ArticleItemModelBuilder setContent(Spannable content) {
            this.content = content;
            return this;
        }

        public ArticleItemModel build() {
            return new ArticleItemModel(this);
        }
    }

}
