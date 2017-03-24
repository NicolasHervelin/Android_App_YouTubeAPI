package fr.esilv.s8.mobile_app_project.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Nicolas on 17/03/2017.
 */

public class Thumbnail implements Serializable {
    @SerializedName("default")
    private Default _default;
    private Medium medium;
    private High high;

    public Default get_default() {
        return _default;
    }

    public void set_default(Default _default) {
        this._default = _default;
    }

    public Medium getMedium() {
        return medium;
    }

    public void setMedium(Medium medium) {
        this.medium = medium;
    }

    public High getHigh() {
        return high;
    }

    public void setHigh(High high) {
        this.high = high;
    }
}
