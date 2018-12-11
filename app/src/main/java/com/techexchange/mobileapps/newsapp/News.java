package com.techexchange.mobileapps.newsapp;

public class News {

    /** name of topic */
    private String mSectionName;
    /** name of article */
    private String mWebTitle;
    /** url */
    private String mUrl;

    public News(String sectionName, String webTitle, String url) {
        mSectionName = sectionName;
        mWebTitle = webTitle;
        mUrl= url;
    }

    /**
     * Returns the topic of the news.
     */
    public String getSectionName() {
        return mSectionName;
    }

    /**
     * Returns the title of the news.
     */
    public String getWebTitle() {
        return mWebTitle;
    }

    /**
     * Returns the website URL to find more information about the news.
     */
    public String getUrl() {
        return mUrl;
    }
}
