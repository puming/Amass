package com.pm.amass.bean;

import com.basics.repository.Result;

import java.util.List;

public class ArticleResult extends Result<List<ArticleResult.Article>> {

    /**
     * result : true
     * msg : 调用成功
     * data : [{"id":1,"title":"fty","tag":"ugyug","description":"gyg","article_cate_id":1,"thumb":3,"images":"\\uploads\\admin\\article_thumb\\20191114\\441ef37213604626e73dda7bf295ce7c.png;","content":"<p><img src=\"/ueditor/php/upload/image/20191114/1573715595508334.png\" title=\"1573715595508334.png\" alt=\"3.png\"/>guyg<br/><\/p>","admin_id":1,"create_time":1523262079,"update_time":1573715609,"edit_admin_id":1,"status":1,"is_top":1,"name":"555","nickname":"jy"}]
     */

    public static class Article {
        /**
         * id : 1
         * title : fty
         * tag : ugyug
         * description : gyg
         * article_cate_id : 1
         * thumb : 3
         * images : 441ef37213604626e73dda7bf295ce7c.png;
         * content : <p><img src="/ueditor/php/upload/image/20191114/1573715595508334.png" title="1573715595508334.png" alt="3.png"/>guyg<br/></p>
         * admin_id : 1
         * create_time : 1523262079
         * update_time : 1573715609
         * edit_admin_id : 1
         * status : 1
         * is_top : 1
         * name : 555
         * nickname : jy
         */

        private int id;
        private String title;
        private String tag;
        private String description;
        private int article_cate_id;
        private int thumb;
        private String images;
        private String content;
        private int admin_id;
        private int create_time;
        private int update_time;
        private int edit_admin_id;
        private int status;
        private int is_top;
        private String name;
        private String nickname;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getArticle_cate_id() {
            return article_cate_id;
        }

        public void setArticle_cate_id(int article_cate_id) {
            this.article_cate_id = article_cate_id;
        }

        public int getThumb() {
            return thumb;
        }

        public void setThumb(int thumb) {
            this.thumb = thumb;
        }

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getAdmin_id() {
            return admin_id;
        }

        public void setAdmin_id(int admin_id) {
            this.admin_id = admin_id;
        }

        public int getCreate_time() {
            return create_time;
        }

        public void setCreate_time(int create_time) {
            this.create_time = create_time;
        }

        public int getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(int update_time) {
            this.update_time = update_time;
        }

        public int getEdit_admin_id() {
            return edit_admin_id;
        }

        public void setEdit_admin_id(int edit_admin_id) {
            this.edit_admin_id = edit_admin_id;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getIs_top() {
            return is_top;
        }

        public void setIs_top(int is_top) {
            this.is_top = is_top;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }
    }
}
