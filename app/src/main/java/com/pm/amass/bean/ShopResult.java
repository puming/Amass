package com.pm.amass.bean;

import com.basics.repository.Result;

import java.util.List;

public class ShopResult extends Result<List<ShopResult.Shop>> {

    public static class Shop {
        /**
         * id : 3
         * title : 111
         * tag : 111
         * description : 111
         * shop_cate_id : 2
         * price_form : 2
         * price_points : 111
         * price_money : 121212
         * stock : 1111111
         * thumb : 40
         * images : 4b7b8304c443dc16d2744337fb74d54b.png;
         * content : <p>111</p>
         * admin_id : 1
         * create_time : 1573703734
         * update_time : 1573711832
         * edit_admin_id : 1
         * status : 1
         * is_top : 0
         * name : 12
         */

        private int id;
        private String title;
        private String tag;
        private String description;
        private int shop_cate_id;
        private int price_form;
        private int price_points;
        private int price_money;
        private int stock;
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

        public int getShop_cate_id() {
            return shop_cate_id;
        }

        public void setShop_cate_id(int shop_cate_id) {
            this.shop_cate_id = shop_cate_id;
        }

        public int getPrice_form() {
            return price_form;
        }

        public void setPrice_form(int price_form) {
            this.price_form = price_form;
        }

        public int getPrice_points() {
            return price_points;
        }

        public void setPrice_points(int price_points) {
            this.price_points = price_points;
        }

        public int getPrice_money() {
            return price_money;
        }

        public void setPrice_money(int price_money) {
            this.price_money = price_money;
        }

        public int getStock() {
            return stock;
        }

        public void setStock(int stock) {
            this.stock = stock;
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
    }
}
