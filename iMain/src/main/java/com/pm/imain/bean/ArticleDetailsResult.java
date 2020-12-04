package com.pm.imain.bean;

import com.basics.repository.Result;

import java.util.List;

public class ArticleDetailsResult extends Result<ArticleDetailsResult.ArticleDetails> {


    public static class ArticleDetails {
        /**
         * detail : {"id":2,"title":"111","tag":"111","description":"1111222","article_cate_id":1,"thumb":"http://jiye.sdmiaobang.com\\uploads\\admin\\article_thumb\\20180409\\6478d845d53d05c4e51f65576595810a.png","images":"/uploads/admin/article_thumb/20191125/4e77f271a6b4c678d9f4cccb70bbb3b9.jpg;/uploads/admin/article_thumb/20191125/78ae3c013ef1097e2f77f440d56e1471.png;/uploads/admin/article_thumb/20191125/5ddc13d345b669ea9913db6cd4def82e.png;","content":"<p>111111<br/><\/p>","admin_id":1,"create_time":1523281120,"update_time":1574688403,"edit_admin_id":1,"status":1,"is_top":1,"good_num":0,"share_num":2,"read_num":8,"collect_num":0,"comment_num":0,"name":"家庭教育","nickname":"aaa","h5_url":"http://jiye.sdmiaobang.com/index/article/index?articleId=2&UserId=8"}
         * comment : [{"content":"11111111111111111111111111","ctime":"2019-11-21 13:52:31","uid":1,"nickname":"嘿嘿嘿"}]
         */

        private Details detail;
        private List<Comment> comment;

        public Details getDetail() {
            return detail;
        }

        public void setDetail(Details detail) {
            this.detail = detail;
        }

        public List<Comment> getComment() {
            return comment;
        }

        public void setComment(List<Comment> comment) {
            this.comment = comment;
        }

        public static class Details {
            /**
             * id : 2
             * title : 111
             * tag : 111
             * description : 1111222
             * article_cate_id : 1
             * thumb : 6478d845d53d05c4e51f65576595810a.png
             * images : /uploads/admin/article_thumb/20191125/4e77f271a6b4c678d9f4cccb70bbb3b9.jpg;/uploads/admin/article_thumb/20191125/78ae3c013ef1097e2f77f440d56e1471.png;/uploads/admin/article_thumb/20191125/5ddc13d345b669ea9913db6cd4def82e.png;
             * content : <p>111111<br/></p>
             * admin_id : 1
             * create_time : 1523281120
             * update_time : 1574688403
             * edit_admin_id : 1
             * status : 1
             * is_top : 1
             * good_num : 0
             * share_num : 2
             * read_num : 8
             * collect_num : 0
             * comment_num : 0
             * name : 家庭教育
             * nickname : aaa
             * h5_url : http://jiye.sdmiaobang.com/index/article/index?articleId=2&UserId=8
             */

            private int id;
            private String title;
            private String tag;
            private String description;
            private int article_cate_id;
            private String thumb;
            private String images;
            private String content;
            private int admin_id;
            private int create_time;
            private int update_time;
            private int edit_admin_id;
            private int status;
            private int is_top;
            private int good_num;
            private int share_num;
            private int read_num;
            private int collect_num;
            private int comment_num;
            private String name;
            private String nickname;
            private String h5_url;

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

            public String getThumb() {
                return thumb;
            }

            public void setThumb(String thumb) {
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

            public int getGood_num() {
                return good_num;
            }

            public void setGood_num(int good_num) {
                this.good_num = good_num;
            }

            public int getShare_num() {
                return share_num;
            }

            public void setShare_num(int share_num) {
                this.share_num = share_num;
            }

            public int getRead_num() {
                return read_num;
            }

            public void setRead_num(int read_num) {
                this.read_num = read_num;
            }

            public int getCollect_num() {
                return collect_num;
            }

            public void setCollect_num(int collect_num) {
                this.collect_num = collect_num;
            }

            public int getComment_num() {
                return comment_num;
            }

            public void setComment_num(int comment_num) {
                this.comment_num = comment_num;
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

            public String getH5_url() {
                return h5_url;
            }

            public void setH5_url(String h5_url) {
                this.h5_url = h5_url;
            }
        }

        public static class Comment {
            /**
             * content : 11111111111111111111111111
             * ctime : 2019-11-21 13:52:31
             * uid : 1
             * nickname : 嘿嘿嘿
             */

            private String content;
            private String ctime;
            private int uid;
            private String nickname;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getCtime() {
                return ctime;
            }

            public void setCtime(String ctime) {
                this.ctime = ctime;
            }

            public int getUid() {
                return uid;
            }

            public void setUid(int uid) {
                this.uid = uid;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }
        }
    }
}
