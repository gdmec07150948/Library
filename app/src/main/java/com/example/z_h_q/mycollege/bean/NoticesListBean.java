package com.example.z_h_q.mycollege.bean;

import java.util.List;

/**
 * Created by Z-H-Q on 2017/4/24.
 */
public class NoticesListBean {

    /**
     * notices : [{"notice_content":"????????????????2016?4?4????????????????????????","notice_date":"2016-3-30","notice_id":"1","notice_title":"?????????"}]
     * success : 1
     */

    public int success;
    public List<NoticesBean> notices;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public List<NoticesBean> getNotices() {
        return notices;
    }

    public void setNotices(List<NoticesBean> notices) {
        this.notices = notices;
    }

    public static class NoticesBean {
        /**
         * notice_content : ????????????????2016?4?4????????????????????????
         * notice_date : 2016-3-30
         * notice_id : 1
         * notice_title : ?????????
         */

        public String notice_content;
        public String notice_date;
        public String notice_id;
        public String notice_title;

        public String getNotice_content() {
            return notice_content;
        }

        public void setNotice_content(String notice_content) {
            this.notice_content = notice_content;
        }

        public String getNotice_date() {
            return notice_date;
        }

        public void setNotice_date(String notice_date) {
            this.notice_date = notice_date;
        }

        public String getNotice_id() {
            return notice_id;
        }

        public void setNotice_id(String notice_id) {
            this.notice_id = notice_id;
        }

        public String getNotice_title() {
            return notice_title;
        }

        public void setNotice_title(String notice_title) {
            this.notice_title = notice_title;
        }
    }
}
