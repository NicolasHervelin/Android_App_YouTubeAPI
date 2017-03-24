package fr.esilv.s8.mobile_app_project.models;

import android.media.Image;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Nicolas on 17/03/2017.
 */

public class Video implements Serializable {
    private String kind;
    private String etag;
    private VideoID id;
    private Snippet snippet;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public VideoID getId() {
        return id;
    }

    public void setId(VideoID id) {
        this.id = id;
    }

    public Snippet getSnippet() {
        return snippet;
    }

    public void setSnippet(Snippet snippet) {
        this.snippet = snippet;
    }
}
