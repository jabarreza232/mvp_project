package com.evolutions.jabar.testmagang.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModelProduct {
    @SerializedName("success")
    private boolean success;
    @SerializedName("product_knowledge")
    List<Product>productList;


    public ModelProduct(boolean success, List<Product> productList) {
        this.success = success;
        this.productList = productList;

    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public class Product {
        @SerializedName("id")
        public Integer id;
        @SerializedName("admin")
        private String admin;
        @SerializedName("sender")
        private String sender;
        @SerializedName("subject")
        private String subject;
        @SerializedName("file_pdf_url")
        private String file_pdf;

        public Product(Integer id, String admin, String sender, String subject,String file_pdf) {
            this.id = id;
            this.admin = admin;
            this.sender = sender;
            this.subject = subject;
            this.file_pdf = file_pdf;
        }


        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getAdmin() {
            return admin;
        }

        public void setAdmin(String admin) {
            this.admin = admin;
        }

        public String getSender() {
            return sender;
        }

        public void setSender(String sender) {
            this.sender = sender;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getFile_pdf() {
            return file_pdf;
        }

        public void setFile_pdf(String file_pdf) {
            this.file_pdf = file_pdf;
        }
    }


}
