package com.example.z_h_q.mycollege.bean;

import java.util.List;

/**
 * Created by Z-H-Q on 2017/4/28.
 */
public class UserListBean {
    /**
     * success : 1
     * user : [{"user_email":"987735082@qq.com","user_icon":"icon3.jpg","user_id":"admin","user_name":"张三的歌","user_password":"123456","user_sex":"男","user_tel":"15815265134","user_trueName":"张三"}]
     */

    private int success;
    private List<UserBean> user;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public List<UserBean> getUser() {
        return user;
    }

    public void setUser(List<UserBean> user) {
        this.user = user;
    }

    public static class UserBean {
        /**
         * user_email : 987735082@qq.com
         * user_icon : icon3.jpg
         * user_id : admin
         * user_name : 张三的歌
         * user_password : 123456
         * user_sex : 男
         * user_tel : 15815265134
         * user_trueName : 张三
         */

        private String user_email;
        private String user_icon;
        private String user_id;
        private String user_name;
        private String user_password;
        private String user_sex;
        private String user_tel;
        private String user_trueName;

        public String getUser_email() {
            return user_email;
        }

        public void setUser_email(String user_email) {
            this.user_email = user_email;
        }

        public String getUser_icon() {
            return user_icon;
        }

        public void setUser_icon(String user_icon) {
            this.user_icon = user_icon;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getUser_password() {
            return user_password;
        }

        public void setUser_password(String user_password) {
            this.user_password = user_password;
        }

        public String getUser_sex() {
            return user_sex;
        }

        public void setUser_sex(String user_sex) {
            this.user_sex = user_sex;
        }

        public String getUser_tel() {
            return user_tel;
        }

        public void setUser_tel(String user_tel) {
            this.user_tel = user_tel;
        }

        public String getUser_trueName() {
            return user_trueName;
        }

        public void setUser_trueName(String user_trueName) {
            this.user_trueName = user_trueName;
        }
    }


}
