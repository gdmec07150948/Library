package com.example.z_h_q.mycollege.bean;

import java.util.List;

/**
 * Created by Z-H-Q on 2017/5/3.
 */
public class BookListBean {

    /**
     * book : [{"book_about":"《汉武大帝》以刘彻的幼年开篇，通过风险继位，掌握大权；用贤变法，尊王攘夷；大战匈奴，出使西域；巫蛊为乱，罪己示民等重大事件，围绕和与战、治与乱、忠与叛、生与死、得与失、情与恨的矛盾，向读者全景式地展现了汉武帝纵横跌宕的一生和那个风云变幻、英雄辈出的时代。","book_author":"杨焕亭","book_category":"历史传记","book_house":"长江文艺出版社","book_id":"6","book_img":"category6.jpg","book_name":"汉武大帝","book_num":"12","book_price":"10"}]
     * success : 1
     */

    public int success;
    public List<BookBean> book;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public List<BookBean> getBook() {
        return book;
    }

    public void setBook(List<BookBean> book) {
        this.book = book;
    }

    public static class BookBean {
        /**
         * book_about : 《汉武大帝》以刘彻的幼年开篇，通过风险继位，掌握大权；用贤变法，尊王攘夷；大战匈奴，出使西域；巫蛊为乱，罪己示民等重大事件，围绕和与战、治与乱、忠与叛、生与死、得与失、情与恨的矛盾，向读者全景式地展现了汉武帝纵横跌宕的一生和那个风云变幻、英雄辈出的时代。
         * book_author : 杨焕亭
         * book_category : 历史传记
         * book_house : 长江文艺出版社
         * book_id : 6
         * book_img : category6.jpg
         * book_name : 汉武大帝
         * book_num : 12
         * book_price : 10
         */

        public String book_about;
        public String book_author;
        public String book_category;
        public String book_house;
        public String book_id;
        public String book_img;
        public String book_name;
        public String book_num;
        public String book_price;

        public String getBook_about() {
            return book_about;
        }

        public void setBook_about(String book_about) {
            this.book_about = book_about;
        }

        public String getBook_author() {
            return book_author;
        }

        public void setBook_author(String book_author) {
            this.book_author = book_author;
        }

        public String getBook_category() {
            return book_category;
        }

        public void setBook_category(String book_category) {
            this.book_category = book_category;
        }

        public String getBook_house() {
            return book_house;
        }

        public void setBook_house(String book_house) {
            this.book_house = book_house;
        }

        public String getBook_id() {
            return book_id;
        }

        public void setBook_id(String book_id) {
            this.book_id = book_id;
        }

        public String getBook_img() {
            return book_img;
        }

        public void setBook_img(String book_img) {
            this.book_img = book_img;
        }

        public String getBook_name() {
            return book_name;
        }

        public void setBook_name(String book_name) {
            this.book_name = book_name;
        }

        public String getBook_num() {
            return book_num;
        }

        public void setBook_num(String book_num) {
            this.book_num = book_num;
        }

        public String getBook_price() {
            return book_price;
        }

        public void setBook_price(String book_price) {
            this.book_price = book_price;
        }
    }
}
