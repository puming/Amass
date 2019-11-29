package com.pm.amass.bean;

import com.basics.repository.Result;

public class TaskDetailsResult extends Result<TaskDetailsResult.TaskDetails> {

    /**
     * data : {"get":1,"over":0,"detail":{"id":1,"teacher_id":1,"class_id":1,"tname":"wewrwrwer","type":2,"target":2,"starttime":"2019-11-13 12:00:00","endtime":"2019-11-30 12:00:00","content":"11212121212121212","reward_zk":11,"reward_hnt":11,"reward_gj":11,"reward_jb":11,"desc":"12121212","ctime":"2019-11-23 17:52:40","status":0,"is_wj":"0","h5_url":""}}
     */
    public static class TaskDetails {
        /**
         * get : 1
         * over : 0
         * detail : {"id":1,"teacher_id":1,"class_id":1,"tname":"wewrwrwer","type":2,"target":2,"starttime":"2019-11-13 12:00:00","endtime":"2019-11-30 12:00:00","content":"11212121212121212","reward_zk":11,"reward_hnt":11,"reward_gj":11,"reward_jb":11,"desc":"12121212","ctime":"2019-11-23 17:52:40","status":0,"is_wj":"0","h5_url":""}
         */

        private int get;
        private int over;
        private DetailBean detail;

        public int getGet() {
            return get;
        }

        public void setGet(int get) {
            this.get = get;
        }

        public int getOver() {
            return over;
        }

        public void setOver(int over) {
            this.over = over;
        }

        public DetailBean getDetail() {
            return detail;
        }

        public void setDetail(DetailBean detail) {
            this.detail = detail;
        }

        public static class DetailBean {
            /**
             * id : 1
             * teacher_id : 1
             * class_id : 1
             * tname : wewrwrwer
             * type : 2
             * target : 2
             * starttime : 2019-11-13 12:00:00
             * endtime : 2019-11-30 12:00:00
             * content : 11212121212121212
             * reward_zk : 11
             * reward_hnt : 11
             * reward_gj : 11
             * reward_jb : 11
             * desc : 12121212
             * ctime : 2019-11-23 17:52:40
             * status : 0
             * is_wj : 0
             * h5_url :
             */

            private int id;
            private int teacher_id;
            private int class_id;
            private String tname;
            private int type;
            private int target;
            private String starttime;
            private String endtime;
            private String content;
            private int reward_zk;
            private int reward_hnt;
            private int reward_gj;
            private int reward_jb;
            private String desc;
            private String ctime;
            private int status;
            private String is_wj;
            private String h5_url;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getTeacher_id() {
                return teacher_id;
            }

            public void setTeacher_id(int teacher_id) {
                this.teacher_id = teacher_id;
            }

            public int getClass_id() {
                return class_id;
            }

            public void setClass_id(int class_id) {
                this.class_id = class_id;
            }

            public String getTname() {
                return tname;
            }

            public void setTname(String tname) {
                this.tname = tname;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getTarget() {
                return target;
            }

            public void setTarget(int target) {
                this.target = target;
            }

            public String getStarttime() {
                return starttime;
            }

            public void setStarttime(String starttime) {
                this.starttime = starttime;
            }

            public String getEndtime() {
                return endtime;
            }

            public void setEndtime(String endtime) {
                this.endtime = endtime;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getReward_zk() {
                return reward_zk;
            }

            public void setReward_zk(int reward_zk) {
                this.reward_zk = reward_zk;
            }

            public int getReward_hnt() {
                return reward_hnt;
            }

            public void setReward_hnt(int reward_hnt) {
                this.reward_hnt = reward_hnt;
            }

            public int getReward_gj() {
                return reward_gj;
            }

            public void setReward_gj(int reward_gj) {
                this.reward_gj = reward_gj;
            }

            public int getReward_jb() {
                return reward_jb;
            }

            public void setReward_jb(int reward_jb) {
                this.reward_jb = reward_jb;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getCtime() {
                return ctime;
            }

            public void setCtime(String ctime) {
                this.ctime = ctime;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
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
}
