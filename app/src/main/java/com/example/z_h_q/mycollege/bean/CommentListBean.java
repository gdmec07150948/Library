package com.example.z_h_q.mycollege.bean;

import java.util.List;

/**
 * Created by Z-H-Q on 2017/5/7.
 */
public class CommentListBean {

    /**
     * comments : [{"book_id":"1","comment_content":"好书","comment_date":"2017-05-06 16:28:37","comment_id":"1","user_id":"07150948"}]
     * success : 1
     */

    private int success;
    private List<CommentsBean> comments;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public List<CommentsBean> getComments() {
        return comments;
    }

    public void setComments(List<CommentsBean> comments) {
        this.comments = comments;
    }

    public static class CommentsBean {
        /**
         * book_id : 1
         * comment_content : 好书
         * comment_date : 2017-05-06 16:28:37
         * comment_id : 1
         * user_id : 07150948
         */

        private String book_id;
        private String comment_content;
        private String comment_date;
        private String comment_id;
        private String user_id;

        public String getBook_id() {
            return book_id;
        }

        public void setBook_id(String book_id) {
            this.book_id = book_id;
        }

        public String getComment_content() {
            return comment_content;
        }

        public void setComment_content(String comment_content) {
            this.comment_content = comment_content;
        }

        public String getComment_date() {
            return comment_date;
        }

        public void setComment_date(String comment_date) {
            this.comment_date = comment_date;
        }

        public String getComment_id() {
            return comment_id;
        }

        public void setComment_id(String comment_id) {
            this.comment_id = comment_id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }
    }
}
