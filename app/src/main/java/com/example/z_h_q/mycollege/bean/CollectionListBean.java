package com.example.z_h_q.mycollege.bean;

import java.util.List;

/**
 * Created by Z-H-Q on 2017/5/7.
 */
public class CollectionListBean {

    /**
     * collections : [{"book_id":"1","collection_id":"1","user_id":"07150948"}]
     * success : 1
     */

    private int success;
    private List<CollectionsBean> collections;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public List<CollectionsBean> getCollections() {
        return collections;
    }

    public void setCollections(List<CollectionsBean> collections) {
        this.collections = collections;
    }

    public static class CollectionsBean {
        /**
         * book_id : 1
         * collection_id : 1
         * user_id : 07150948
         */

        private String book_id;
        private String collection_id;
        private String user_id;

        public String getBook_id() {
            return book_id;
        }

        public void setBook_id(String book_id) {
            this.book_id = book_id;
        }

        public String getCollection_id() {
            return collection_id;
        }

        public void setCollection_id(String collection_id) {
            this.collection_id = collection_id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }
    }
}
