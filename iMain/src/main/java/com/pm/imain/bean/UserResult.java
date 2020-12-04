package com.pm.imain.bean;

import com.basics.repository.Result;

/**
 * @author pm
 * @date 2019/11/26
 * @email puming@zdsoft.cn
 */
public class UserResult extends Result<UserResult.User> {


    public static class User {
        /**
         * id : 8
         * invite_code :
         * password : 48028e7eb3282307e3c20f1f3f79952a
         * name : Marvin
         * relation : 爸爸
         * phone : 13693302061
         * qq_openid :
         * wx_openid :
         * nickname : irv
         * headimg :
         * sex : 1
         * birthday : 0000-00-00
         * birth_place :
         * address :
         * reg_time : 0000-00-00 00:00:00
         * level : 1
         * source : student
         * from :
         * update_time : 0000-00-00 00:00:00
         * total_score : 0
         * use_score : 0
         * reward_zk : 0
         * reward_hnt : 0
         * reward_gj : 0
         */

        private int id;
        private String invite_code;
        private String password;
        private String name;
        private String relation;
        private String phone;
        private String qq_openid;
        private String wx_openid;
        private String nickname;
        private String headimg;
        private int sex;
        private String birthday;
        private String birth_place;
        private String address;
        private String reg_time;
        private int level;
        private String source;
        private String from;
        private String update_time;
        private int total_score;
        private int use_score;
        private int reward_zk;
        private int reward_hnt;
        private int reward_gj;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getInvite_code() {
            return invite_code;
        }

        public void setInvite_code(String invite_code) {
            this.invite_code = invite_code;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRelation() {
            return relation;
        }

        public void setRelation(String relation) {
            this.relation = relation;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getQq_openid() {
            return qq_openid;
        }

        public void setQq_openid(String qq_openid) {
            this.qq_openid = qq_openid;
        }

        public String getWx_openid() {
            return wx_openid;
        }

        public void setWx_openid(String wx_openid) {
            this.wx_openid = wx_openid;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getHeadimg() {
            return headimg;
        }

        public void setHeadimg(String headimg) {
            this.headimg = headimg;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getBirth_place() {
            return birth_place;
        }

        public void setBirth_place(String birth_place) {
            this.birth_place = birth_place;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getReg_time() {
            return reg_time;
        }

        public void setReg_time(String reg_time) {
            this.reg_time = reg_time;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public int getTotal_score() {
            return total_score;
        }

        public void setTotal_score(int total_score) {
            this.total_score = total_score;
        }

        public int getUse_score() {
            return use_score;
        }

        public void setUse_score(int use_score) {
            this.use_score = use_score;
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
    }
}
