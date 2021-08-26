package com.vimal.retrofitpostapi.api;



import com.vimal.retrofitpostapi.api.model.CategoryMain;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface Api {

//    @GET("Category.php")
//    Call<CategoryMain> getCategory();

    @POST("Category.php")
    @FormUrlEncoded
    Call<CategoryMain> createPost(@Field("Id") String Id);

    @GET
    Call<CategoryMain> getSearch(@Url String url,
                                      @Header("Authorization") String credentials,
                                      @Query("query") String queryText);
}