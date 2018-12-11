package com.techexchange.mobileapps.newsapp;

/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;


public class NewsAdapter extends ArrayAdapter<News> {



    public NewsAdapter(Context context, List<News> news) {
        super(context, 0, news);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.news_list_item, parent, false);
        }

        // Find the earthquake at the given position in the list of earthquakes
        News currentNews = getItem(position);

        // Find the TextView with view ID magnitude
        TextView titleView = (TextView) listItemView.findViewById(R.id.webTitle);
        // Format the magnitude to show 1 decimal place
        String formattedTitleName = currentNews.getWebTitle();
        // Display the magnitude of the current earthquake in that TextView
        titleView.setText(formattedTitleName);

        TextView sectionView = (TextView) listItemView.findViewById(R.id.webSection);
        // Format the magnitude to show 1 decimal place
        String formattedSectionView = currentNews.getSectionName();
        // Display the magnitude of the current earthquake in that TextView
        sectionView.setText(formattedSectionView);

        TextView urlView = (TextView) listItemView.findViewById(R.id.webUrl);
        // Format the magnitude to show 1 decimal place
        String formattedUrlView = currentNews.getSectionName();
        // Display the magnitude of the current earthquake in that TextView
        urlView.setText(formattedUrlView);
        // Return the list item view that is now showing the appropriate data
        return listItemView;
    }


}

