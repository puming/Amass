package com.pm.amass.bean;

import com.google.gson.annotations.SerializedName;

public class TileInfo {
    @SerializedName("iconUrl")
    private String iconUrl;
    @SerializedName("icon")
    private int icon;
    @SerializedName("title")
    private String title;
    @SerializedName("boundary")
    private boolean boundary;

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isBoundary() {
        return boundary;
    }

    public void setBoundary(boolean boundary) {
        this.boundary = boundary;
    }
}
