/**
 * Created by Vimal on June-2021.
 */
package com.vimal.retrofitpostapi.api.model;

import java.io.Serializable;
//test+

public class Jsonpost implements Serializable {

    private String postId;
    private Integer image;

    public Jsonpost(String postId, Integer image) {
        this.postId = postId;
        this.image = image;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

}



