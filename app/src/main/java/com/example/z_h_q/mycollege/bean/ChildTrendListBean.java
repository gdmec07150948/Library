package com.example.z_h_q.mycollege.bean;

import java.util.List;

/**
 * Created by Z-H-Q on 2017/5/4.
 */
public class ChildTrendListBean {

    /**
     * childtrends : [{"childtrend_content":"07150948评论","childtrend_date":"2017-05-04 22:08:20","childtrend_id":"1","trend_id":"15","user_id":"07150948"},{"childtrend_content":"07150947评论的","childtrend_date":"2017-05-04 22:08:53","childtrend_id":"2","trend_id":"15","user_id":"07150947"}]
     * success : 1
     */

    private int success;
    private List<ChildtrendsBean> childtrends;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public List<ChildtrendsBean> getChildtrends() {
        return childtrends;
    }

    public void setChildtrends(List<ChildtrendsBean> childtrends) {
        this.childtrends = childtrends;
    }

    public static class ChildtrendsBean {
        /**
         * childtrend_content : 07150948评论
         * childtrend_date : 2017-05-04 22:08:20
         * childtrend_id : 1
         * trend_id : 15
         * user_id : 07150948
         */

        private String childtrend_content;
        private String childtrend_date;
        private String childtrend_id;
        private String trend_id;
        private String user_id;

        public String getChildtrend_content() {
            return childtrend_content;
        }

        public void setChildtrend_content(String childtrend_content) {
            this.childtrend_content = childtrend_content;
        }

        public String getChildtrend_date() {
            return childtrend_date;
        }

        public void setChildtrend_date(String childtrend_date) {
            this.childtrend_date = childtrend_date;
        }

        public String getChildtrend_id() {
            return childtrend_id;
        }

        public void setChildtrend_id(String childtrend_id) {
            this.childtrend_id = childtrend_id;
        }

        public String getTrend_id() {
            return trend_id;
        }

        public void setTrend_id(String trend_id) {
            this.trend_id = trend_id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }
    }
}
