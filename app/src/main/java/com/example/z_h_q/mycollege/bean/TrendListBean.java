package com.example.z_h_q.mycollege.bean;

import java.util.List;

/**
 * Created by Z-H-Q on 2017/4/30.
 */
public class TrendListBean {

    /**
     * success : 1
     * trends : [{"trend_content":"今儿个真呀真高兴！","trend_date":"2017-04-30 11:57:29","trend_id":"1","user_id":"07150948"},{"trend_content":"哈哈哈哈哈哈哈哈哈哈哈哈","trend_date":"2017-04-30 11:57:52","trend_id":"2","user_id":"admin"},{"trend_content":"滋滋滋滋滋滋滋滋滋滋滋滋。","trend_date":"2017-04-30 11:58:16","trend_id":"3","user_id":"07150947"}]
     */

    private int success;
    public List<TrendsBean> trends;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public List<TrendsBean> getTrends() {
        return trends;
    }

    public void setTrends(List<TrendsBean> trends) {
        this.trends = trends;
    }

    public static class TrendsBean {
        /**
         * trend_content : 今儿个真呀真高兴！
         * trend_date : 2017-04-30 11:57:29
         * trend_id : 1
         * user_id : 07150948
         */

        public String trend_content;
        public String trend_date;
        public String trend_id;
        public String user_id;

        public String getTrend_content() {
            return trend_content;
        }

        public void setTrend_content(String trend_content) {
            this.trend_content = trend_content;
        }

        public String getTrend_date() {
            return trend_date;
        }

        public void setTrend_date(String trend_date) {
            this.trend_date = trend_date;
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
