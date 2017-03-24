package fr.esilv.s8.mobile_app_project.models;

/**
 * Created by Nicolas on 23/03/2017.
 */

public class StatsVideo {
    private String kind;
    private String etag;
    private String id;
    private Stats statistics;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Stats getStatistics() {
        return statistics;
    }

    public void setStatistics(Stats statistics) {
        this.statistics = statistics;
    }
}
