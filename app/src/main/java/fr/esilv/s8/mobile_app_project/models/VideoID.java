package fr.esilv.s8.mobile_app_project.models;

import java.io.Serializable;

/**
 * Created by Nicolas on 17/03/2017.
 */

public class VideoID implements Serializable{
    private String kind;
    private String videoId;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }
}
