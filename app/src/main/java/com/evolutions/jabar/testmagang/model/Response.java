package com.evolutions.jabar.testmagang.model;

import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

 public class Response {
     @SerializedName("success")
     private   boolean success;
     @SerializedName("msg")
     public String message;
     @SerializedName("area")
     private  List<Area>area;

     public Response(boolean success,List<Area>area,String msg){
     this.area = area;
     this.success=success;
     this.message=msg;
    }


     public String getMessage() {
         return message;
     }

     public void setMessage(String message) {
         this.message = message;
     }

     public boolean isSuccess() {
        return success;
    }


    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<Area> getArea() {
        return area;

    }

    public void setArea(List<Area> area) {
        this.area = area;
    }

    public static class Area {
         @SerializedName("id")
         private Integer id;
         @SerializedName("name")
         private String name;
        private String deskripsi;

        public Area(String name) {
 this.id = id;
this.name= name;
this.deskripsi = deskripsi;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDeskripsi() {
            return deskripsi;
        }
        public void setDeskripsi(String deskripsi) {
            this.deskripsi = deskripsi;
        }
    }
}
