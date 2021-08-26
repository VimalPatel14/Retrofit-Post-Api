package com.vimal.retrofitpostapi.api.viewmodel;


import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.vimal.retrofitpostapi.BuildConfig;
import com.vimal.retrofitpostapi.api.Api;
import com.vimal.retrofitpostapi.api.model.CategoryMain;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class CategoryViewModel extends ViewModel {

    //this is the data that we will fetch asynchronously 
    private MutableLiveData<CategoryMain> categoryList;

    //we will call this method to get the data
    public LiveData<CategoryMain> getCategory() {
        //if the list is null 
        if (categoryList == null) {
            categoryList = new MutableLiveData<CategoryMain>();
            //we will load it asynchronously from server in this method
            loadCategory();
        }

        //finally we will return the list
        return categoryList;
    }


    //This method is using Retrofit to get the JSON data from URL 
    private void loadCategory() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
//        Call<CategoryMain> call = api.getCategory();
        Call<CategoryMain> call = api.createPost("-1");
        call.enqueue(new Callback<CategoryMain>() {
            @Override
            public void onResponse(Call<CategoryMain> call, Response<CategoryMain> response) {

                if (response.isSuccessful()) {
                    Log.e("vml",  " isSuccessful");
                    categoryList.setValue(response.body());
                } else {
                    Log.e("vml",  " isSuccessful_No");
                }
                //finally we are setting the list to our MutableLiveData
            }

            @Override
            public void onFailure(Call<CategoryMain> call, Throwable t) {
                Log.e("vml", t.getMessage() + " error");
            }
        });
    }
}
 