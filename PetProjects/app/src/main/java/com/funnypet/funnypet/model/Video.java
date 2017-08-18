package com.funnypet.funnypet.model;

import com.funnypet.funnypet.R;

/**
 * Created by thanhpv on 8/17/17.
 */

public class Video {
    private String title;
    private String url;

    public Video() {
        this.title = "Funny pet moments";
        this.url = "";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (!title.equals("")){
            this.title = title;
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        if (!title.equals("")){
            this.url = url;
        }
    }
}
