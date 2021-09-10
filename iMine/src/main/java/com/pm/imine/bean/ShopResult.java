package com.pm.imine.bean;

import com.basics.repository.Result;

import java.util.List;

/**
 * @author pmcho
 */
public class ShopResult extends Result<List<ShopResult.Shop>> {


    public static class Shop {
        /**
         * id : 4
         * title : 1212
         * thumb : http://jiye.sdmiaobang.com/uploads/admin/article_thumb/20191125/8e46df1c54c5f429d83631cb7aae2eaa.jpg
         * images : /uploads/admin/article_thumb/20191125/0750e9a6c8814bc26e3e4cb605c69f7f.jpg;
         * shop_cate_id : 1
         * price_form : 1
         * price_points : 10
         * price_money : 0
         * name : 555
         */

        private int id;
        private String title;
        private String thumb;
        private String images;
        private int shop_cate_id;
        private int price_form;
        private int price_points;
        private int price_money;
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
