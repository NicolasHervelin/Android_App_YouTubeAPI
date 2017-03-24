package fr.esilv.s8.mobile_app_project.models;

/**
 * Created by Nicolas on 23/03/2017.
 */

public class ReturnStatsObject {
    private String kind;
    private String etag;
    private PageInfo pageInfo;
    private StatsVideos items;

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public StatsVideos getItems() {
        return items;
    }

    public void setItems(StatsVideos items) {
        this.items = items;
    }
}
