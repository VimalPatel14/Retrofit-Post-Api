package com.vimal.retrofitpostapi.api.model;

import java.util.List;

public class CategoryMain  {

//    @SerializedName("data")
//    @Expose
    private List<Category> data = null;
//    @SerializedName("sucess")
//    @Expose
    private Boolean sucess;
//    @SerializedName("message")
//    @Expose
    private String message;

    public List<Category> getData() {
        return data;
    }

    public void setData(List<Category> data) {
        this.data = data;
    }

    public Boolean getSucess() {
        return sucess;
    }

    public void setSucess(Boolean sucess) {
        this.sucess = sucess;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }



}

