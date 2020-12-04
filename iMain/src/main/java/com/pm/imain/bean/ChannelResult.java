package com.pm.imain.bean;

import com.basics.repository.Result;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author pmcho
 */
public class ChannelResult extends Result<List<ChannelResult.Channel>> {

    public static class Channel {
        private long id;
        @SerializedName("name")
        private String title;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
