package com.pm.imain.bean;

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
         * images : 008691d151d212cd9697f5ddcbb776ec.png;
         * read_num : 0
         * comment_num : 0
         * article_cate_id : 1
         * name : 555
         * nickname : jy
         * admin_id :
         */

        private int id;
        private String title;
        private String images;
        private int read_num;
        private int comment_num;
        private int article_cate_id;
        private String name;
        private String nickname;
        private String admin_id;

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

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
        }

        public int getRead_num() {
            return read_num;
        }

        public void setRead_num(int read_num) {
            this.read_num = read_num;
        }

        public int getComment_num() {
            return comment_num;
        }

        public void setComment_num(int comment_num) {
            this.comment_num = comment_num;
        }

        public int getArticle_cate_id() {
            return article_cate_id;
        }

        public void setArticle_cate_id(int article_cate_id) {
            this.article_cate_id = article_cate_id;
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

        public String getAdmin_id() {
            return admin_id;
        }

        public void setAdmin_id(String admin_id) {
            this.admin_id = admin_id;
        }
    }

}
