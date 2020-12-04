package com.pm.imain.bean;

import com.basics.repository.Result;

import java.util.List;

public class TaskResult extends Result<List<TaskResult.Task>> {

    public static class Task {
        /**
         * id : 3
         * tname : 11111
         * endtime : 2019-11-30
         * type : 1
         * jzday : 2
         * icon : http://jiye.sdmiaobang.com/uploads//admin/article_thumb/20191125/fdcb76c3d1b27c62ec8c81f0b67b7d21.png
         * is_wj : 0
         * h5_url :
         */

        private int id;
        private String tname;
        private String endtime;
        private int type;
        private int jzday;
        private String icon;
        private String is_wj;
        private String h5_url;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTname() {
            return tname;
        }

        public void setTname(String tname) {
            this.tname = tname;
        }

        public String getEndtime() {
            return endtime;
        }

        public void setEndtime(String endtime) {
            this.endtime = endtime;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getJzday() {
            return jzday;
        }

        public void setJzday(int jzday) {
            this.jzday = jzday;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getIs_wj() {
            return is_wj;
        }

        public void setIs_wj(String is_wj) {
            this.is_wj = is_wj;
        }

        public String getH5_url() {
            return h5_url;
        }

        public void setH5_url(String h5_url) {
            this.h5_url = h5_url;
        }
    }
}
