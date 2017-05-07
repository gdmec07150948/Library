package com.example.z_h_q.mycollege.bean;

import java.util.List;

/**
 * Created by Z-H-Q on 2017/4/23.
 */
public class ProductsListBean {

    /**
     * products : [{"pid":"9","name":"vivo","price":"4000.00","description":"This is vivo","created_at":"2017-04-21 00:23:27","updated_at":"0000-00-00 00:00:00"},{"pid":"10","name":"oppo","price":"9999.00","description":"This is oppo","created_at":"2017-04-21 00:29:38","updated_at":"0000-00-00 00:00:00"},{"pid":"11","name":"ss","price":"33.00","description":"This is ss","created_at":"2017-04-21 00:33:22","updated_at":"0000-00-00 00:00:00"},{"pid":"12","name":"rr","price":"122.00","description":"This is rr","created_at":"2017-04-22 18:45:08","updated_at":"2017-04-22 18:45:08"},{"pid":"13","name":"yy","price":"500.00","description":"This is yy","created_at":"2017-04-22 18:45:45","updated_at":"0000-00-00 00:00:00"},{"pid":"14","name":"uu","price":"600.00","description":"This uu","created_at":"2017-04-22 18:47:43","updated_at":"2017-04-22 18:47:43"},{"pid":"15","name":"oo","price":"222.00","description":"This is oo","created_at":"2017-04-22 18:48:53","updated_at":"0000-00-00 00:00:00"},{"pid":"16","name":"ii","price":"676.00","description":"This is ii","created_at":"2017-04-22 18:49:25","updated_at":"0000-00-00 00:00:00"},{"pid":"17","name":"hh","price":"800.00","description":"This is hh","created_at":"2017-04-22 18:50:43","updated_at":"2017-04-22 18:50:43"},{"pid":"18","name":"jj","price":"454.00","description":"This is jj","created_at":"2017-04-22 18:51:29","updated_at":"0000-00-00 00:00:00"},{"pid":"19","name":"io","price":"5.00","description":"io","created_at":"2017-04-22 19:25:41","updated_at":"0000-00-00 00:00:00"},{"pid":"20","name":"rt","price":"55.00","description":"rt","created_at":"2017-04-22 19:27:46","updated_at":"0000-00-00 00:00:00"},{"pid":"21","name":"we","price":"23.00","description":"we","created_at":"2017-04-22 19:28:54","updated_at":"0000-00-00 00:00:00"},{"pid":"22","name":"gg","price":"34.00","description":"gg","created_at":"2017-04-22 19:33:01","updated_at":"2017-04-22 19:33:01"},{"pid":"23","name":"ghh","price":"455.00","description":"ghh","created_at":"2017-04-22 19:34:03","updated_at":"0000-00-00 00:00:00"},{"pid":"24","name":"yu","price":"56.00","description":"yu","created_at":"2017-04-22 19:48:14","updated_at":"0000-00-00 00:00:00"},{"pid":"25","name":"fg","price":"45.00","description":"fg","created_at":"2017-04-22 19:52:29","updated_at":"0000-00-00 00:00:00"},{"pid":"26","name":"qqq","price":"23.00","description":"qqq","created_at":"2017-04-22 19:53:30","updated_at":"2017-04-22 19:53:30"},{"pid":"27","name":"www","price":"23.00","description":"www","created_at":"2017-04-22 19:53:43","updated_at":"2017-04-22 19:53:43"},{"pid":"28","name":"eee","price":"233.00","description":"eee","created_at":"2017-04-22 19:54:04","updated_at":"2017-04-22 19:54:04"},{"pid":"29","name":"zz","price":"32.00","description":"zz","created_at":"2017-04-22 20:08:12","updated_at":"2017-04-22 20:08:12"},{"pid":"30","name":"xx","price":"34.00","description":"xx","created_at":"2017-04-22 20:15:08","updated_at":"2017-04-22 20:15:08"},{"pid":"31","name":"cc","price":"23.00","description":"cc","created_at":"2017-04-22 20:16:15","updated_at":"2017-04-22 20:16:15"},{"pid":"32","name":"vv","price":"34.00","description":"vv","created_at":"2017-04-22 20:17:36","updated_at":"0000-00-00 00:00:00"},{"pid":"33","name":"mm","price":"55.00","description":"mm","created_at":"2017-04-22 20:20:28","updated_at":"2017-04-22 20:20:28"},{"pid":"34","name":"nn","price":"43.00","description":"nn","created_at":"2017-04-22 20:21:29","updated_at":"2017-04-22 20:21:29"},{"pid":"35","name":"asd","price":"32.00","description":"asd","created_at":"2017-04-22 20:22:41","updated_at":"0000-00-00 00:00:00"},{"pid":"36","name":"fgh","price":"34.00","description":"fgh","created_at":"2017-04-22 20:23:34","updated_at":"0000-00-00 00:00:00"},{"pid":"37","name":"iop","price":"6564.00","description":"iop","created_at":"2017-04-22 20:24:32","updated_at":"0000-00-00 00:00:00"}]
     * success : 1
     */

    public int success;
    public List<ProductsBean> products;

    /*public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public List<ProductsBean> getProducts() {
        return products;
    }

    public void setProducts(List<ProductsBean> products) {
        this.products = products;
    }*/

    public static class ProductsBean {
        /**
         * pid : 9
         * name : vivo
         * price : 4000.00
         * description : This is vivo
         * created_at : 2017-04-21 00:23:27
         * updated_at : 0000-00-00 00:00:00
         */

        public String pid;
        public String name;
        public String price;
        public String description;
        public String image;
        public String created_at;
        public String updated_at;

       /* public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }
        public String getImage(){
            return image;
        }

        public void setImage(String image){
            this.image = image;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }*/
    }
}
