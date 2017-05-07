package com.example.z_h_q.mycollege.bean;

import java.util.List;

/**
 * Created by Z-H-Q on 2017/4/24.
 */
public class BookCategoryBean {

    /**
     * categorys : [{"category_id":"1","category_img":"category1.jpg","category_name":"cbxs"},{"category_id":"2","category_img":"category2.jpg","category_name":"lzcg"},{"category_id":"3","category_img":"category3.jpg","category_name":"wxzp"},{"category_id":"4","category_img":"category4.jpg","category_name":"shjk"},{"category_id":"5","category_img":"category5.jpg","category_name":"shkx"},{"category_id":"6","category_img":"category6.jpg","category_name":"lszj"},{"category_id":"7","category_img":"category7.jpg","category_name":"jjgl"},{"category_id":"8","category_img":"category8.jpg","category_name":"zzjj"},{"category_id":"9","category_img":"category9.jpg","category_name":"lyms"},{"category_id":"10","category_img":"category10.jpg","category_name":"tzlc"},{"category_id":"11","category_img":"category11.jpg","category_name":"gbjd"},{"category_id":"12","category_img":"category12.jpg","category_name":"wyxx"}]
     * success : 1
     */

    private int success;
    private List<CategorysBean> categorys;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public List<CategorysBean> getCategorys() {
        return categorys;
    }

    public void setCategorys(List<CategorysBean> categorys) {
        this.categorys = categorys;
    }

    public static class CategorysBean {
        /**
         * category_id : 1
         * category_img : category1.jpg
         * category_name : cbxs
         */

        private String category_id;
        private String category_img;
        private String category_name;

        public String getCategory_id() {
            return category_id;
        }

        public void setCategory_id(String category_id) {
            this.category_id = category_id;
        }

        public String getCategory_img() {
            return category_img;
        }

        public void setCategory_img(String category_img) {
            this.category_img = category_img;
        }

        public String getCategory_name() {
            return category_name;
        }

        public void setCategory_name(String category_name) {
            this.category_name = category_name;
        }
    }
}
