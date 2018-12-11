package com.techexchange.mobileapps.newsapp;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

/**
 * Helper methods related to requesting and receiving earthquake data from USGS.
 */
public final class QueryUtils {

    /** Sample JSON response for a USGS query */
    private static final String SAMPLE_JSON_RESPONSE = "{\"results\":[" +
            "{\"id\":\"commentisfree/2018/dec/11/bad-santa-scott-morrison-decides-who-has-been-naughty-or-nice\",\"type\":\"article\",\"sectionId\":\"commentisfree\",\"sectionName\":\"Opinion\",\"webPublicationDate\":\"2018-12-11T00:42:12Z\",\"webTitle\":\"Bad Santa: Scott Morrison gets to decide who has been naughty or nice | Brad Chilcott\",\"webUrl\":\"https://www.theguardian.com/commentisfree/2018/dec/11/bad-santa-scott-morrison-decides-who-has-been-naughty-or-nice\",\"apiUrl\":\"https://content.guardianapis.com/commentisfree/2018/dec/11/bad-santa-scott-morrison-decides-who-has-been-naughty-or-nice\",\"isHosted\":false,\"pillarId\":\"pillar/opinion\",\"pillarName\":\"Opinion\"}," +
            "{\"id\":\"media/2018/dec/11/guardians-amelia-gentleman-named-journalist-of-the-year\",\"type\":\"article\",\"sectionId\":\"media\",\"sectionName\":\"Media\",\"webPublicationDate\":\"2018-12-11T00:32:32Z\",\"webTitle\":\"Guardian’s Amelia Gentleman named journalist of the year\",\"webUrl\":\"https://www.theguardian.com/media/2018/dec/11/guardians-amelia-gentleman-named-journalist-of-the-year\",\"apiUrl\":\"https://content.guardianapis.com/media/2018/dec/11/guardians-amelia-gentleman-named-journalist-of-the-year\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"politics/2018/dec/11/north-south-economic-divide-set-to-narrow-as-brexit-hits-london-growth\",\"type\":\"article\",\"sectionId\":\"politics\",\"sectionName\":\"Politics\",\"webPublicationDate\":\"2018-12-11T00:01:15Z\",\"webTitle\":\"North-south economic divide set to narrow as Brexit hits London growth\",\"webUrl\":\"https://www.theguardian.com/politics/2018/dec/11/north-south-economic-divide-set-to-narrow-as-brexit-hits-london-growth\",\"apiUrl\":\"https://content.guardianapis.com/politics/2018/dec/11/north-south-economic-divide-set-to-narrow-as-brexit-hits-london-growth\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"}," +
            "{\"id\":\"business/2018/dec/11/e-receipts-from-major-retailers-may-break-data-protection-rules-which\",\"type\":\"article\",\"sectionId\":\"business\",\"sectionName\":\"Business\",\"webPublicationDate\":\"2018-12-11T00:01:15Z\",\"webTitle\":\"E-receipts from leading retailers 'may break data protection rules'\",\"webUrl\":\"https://www.theguardian.com/business/2018/dec/11/e-receipts-from-major-retailers-may-break-data-protection-rules-which\",\"apiUrl\":\"https://content.guardianapis.com/business/2018/dec/11/e-receipts-from-major-retailers-may-break-data-protection-rules-which\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"}," +
            "{\"id\":\"society/2018/dec/11/young-adults-most-pessimistic-on-uk-social-mobility-poll\",\"type\":\"article\",\"sectionId\":\"society\",\"sectionName\":\"Society\",\"webPublicationDate\":\"2018-12-11T00:01:14Z\",\"webTitle\":\"Young adults most pessimistic on UK social mobility – poll\",\"webUrl\":\"https://www.theguardian.com/society/2018/dec/11/young-adults-most-pessimistic-on-uk-social-mobility-poll\",\"apiUrl\":\"https://content.guardianapis.com/society/2018/dec/11/young-adults-most-pessimistic-on-uk-social-mobility-poll\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"}," +
            "{\"id\":\"society/2018/dec/11/nhs-staff-demands-rise-as-eu-applicants-drop-off-a-cliff\",\"type\":\"article\",\"sectionId\":\"society\",\"sectionName\":\"Society\",\"webPublicationDate\":\"2018-12-11T00:01:14Z\",\"webTitle\":\"Demands on NHS staff rise as EU applicants 'drop off a cliff'\",\"webUrl\":\"https://www.theguardian.com/society/2018/dec/11/nhs-staff-demands-rise-as-eu-applicants-drop-off-a-cliff\",\"apiUrl\":\"https://content.guardianapis.com/society/2018/dec/11/nhs-staff-demands-rise-as-eu-applicants-drop-off-a-cliff\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"}," +
            "{\"id\":\"crosswords/cryptic/27689\",\"type\":\"crossword\",\"sectionId\":\"crosswords\",\"sectionName\":\"Crosswords\",\"webPublicationDate\":\"2018-12-11T00:00:14Z\",\"webTitle\":\"Cryptic crossword No 27,689\",\"webUrl\":\"https://www.theguardian.com/crosswords/cryptic/27689\",\"apiUrl\":\"https://content.guardianapis.com/crosswords/cryptic/27689\",\"isHosted\":false,\"pillarId\":\"pillar/lifestyle\",\"pillarName\":\"Lifestyle\"}," +
            "{\"id\":\"politics/blog/live/2018/dec/10/brexit-deal-vote-latest-theresa-may-ecj-government-says-ecj-ruling-irrelevant-because-uk-leaving-eu-anyway-politics-live\",\"type\":\"liveblog\",\"sectionId\":\"politics\",\"sectionName\":\"Politics\",\"webPublicationDate\":\"2018-12-10T23:32:58Z\",\"webTitle\":\"May roundly condemned by MPs after delaying Brexit vote to seek fresh backstop assurances – as it happened\",\"webUrl\":\"https://www.theguardian.com/politics/blog/live/2018/dec/10/brexit-deal-vote-latest-theresa-may-ecj-government-says-ecj-ruling-irrelevant-because-uk-leaving-eu-anyway-politics-live\",\"apiUrl\":\"https://content.guardianapis.com/politics/blog/live/2018/dec/10/brexit-deal-vote-latest-theresa-may-ecj-government-says-ecj-ruling-irrelevant-because-uk-leaving-eu-anyway-politics-live\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"}," +
            "{\"id\":\"australia-news/2018/dec/11/energy-impasse-is-australias-largest-failure-in-public-policy-business-council-says\",\"type\":\"article\",\"sectionId\":\"australia-news\",\"sectionName\":\"Australia news\",\"webPublicationDate\":\"2018-12-10T23:13:12Z\",\"webTitle\":\"Energy impasse is Australia's 'largest failure in public policy', Business Council says\",\"webUrl\":\"https://www.theguardian.com/australia-news/2018/dec/11/energy-impasse-is-australias-largest-failure-in-public-policy-business-council-says\",\"apiUrl\":\"https://content.guardianapis.com/australia-news/2018/dec/11/energy-impasse-is-australias-largest-failure-in-public-policy-business-council-says\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"}," +
            "{\"id\":\"fashion/2018/dec/10/clare-waight-keller-triumphs-at-2018-fashion-awards\",\"type\":\"article\",\"sectionId\":\"fashion\",\"sectionName\":\"Fashion\",\"webPublicationDate\":\"2018-12-10T23:00:13Z\",\"webTitle\":\"Clare Waight Keller triumphs at 2018 Fashion Awards\",\"webUrl\":\"https://www.theguardian.com/fashion/2018/dec/10/clare-waight-keller-triumphs-at-2018-fashion-awards\",\"apiUrl\":\"https://content.guardianapis.com/fashion/2018/dec/10/clare-waight-keller-triumphs-at-2018-fashion-awards\",\"isHosted\":false,\"pillarId\":\"pillar/lifestyle\",\"pillarName\":\"Lifestyle\"}]}}";

    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {
    }

    public static ArrayList<News> extractNews() {

        // Create an empty ArrayList that we can start adding earthquakes to
        ArrayList<News> news = new ArrayList<>();

        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            JSONObject baseJsonResponse=new JSONObject(SAMPLE_JSON_RESPONSE);
            JSONArray newsArray = baseJsonResponse.getJSONArray("results");
            // build up a list of Earthquake objects with the corresponding data.
            for (int i=0;i<newsArray.length();i++){
                JSONObject currentNews = newsArray.getJSONObject(i);
                String webTitle = currentNews.getString("webTitle");
                String sectionName = currentNews.getString("sectionName");
                String url = currentNews.getString("webUrl");
                News news1 = new News(webTitle, sectionName, url);
                news.add(news1);
            }

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the news JSON results", e);
        }

        // Return the list of earthquakes
        return news;
    }

}