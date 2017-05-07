package com.example.z_h_q.mycollege.bean;

import java.util.List;

/**
 * Created by Z-H-Q on 2017/5/1.
 */
public class NameListBean {

    /**
     * success : 1
     * user : [{"user_icon":"icon2.jpg","user_name":"低调"}]
     */

    public int success;
    public List<UserBean> user;

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
         * user_icon : icon2.jpg
         * user_name : 低调
         */

        public String user_icon;
        public String user_name;

        public String getUser_icon() {
            return user_icon;
        }

        public void setUser_icon(String user_icon) {
            this.user_icon = user_icon;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }
    }
}
