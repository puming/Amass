package com.pm.amass.bean;

import com.basics.repository.Result;

import java.util.List;

public class NewResult extends Result<List<NewResult.News>> {

    public static class News {
        /**
         * id : 4
         * from_id : 2
         * to_id : 1
         * content : 1111
         * addtime : 2019-11-19 17:53:12
         * status : 0
         * dotime : 2019-11-19 17:53:15
         * from_name : 2
         * to_name : 1
         */

        private int id;
        private int from_id;
        private int to_id;
        private String content;
        private String addtime;
        private int status;
        private String dotime;
        private String from_name;
        private String to_name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getFrom_id() {
            return from_id;
        }

        public void setFrom_id(int from_id) {
            this.from_id = from_id;
        }

        public int getTo_id() {
            return to_id;
        }

        public void setTo_id(int to_id) {
            this.to_id = to_id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getDotime() {
            return dotime;
        }

        public void setDotime(String dotime) {
            this.dotime = dotime;
        }

        public String getFrom_name() {
            return from_name;
        }

        public void setFrom_name(String from_name) {
            this.from_name = from_name;
        }

        public String getTo_name() {
            return to_name;
        }

        public void setTo_name(String to_name) {
            this.to_name = to_name;
        }
    }
}
