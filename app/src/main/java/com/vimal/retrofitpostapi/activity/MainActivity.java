package com.vimal.retrofitpostapi.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.AnimationUtils;

import com.vimal.retrofitpostapi.MyApp;
import com.vimal.retrofitpostapi.R;
import com.vimal.retrofitpostapi.adapter.CategoryAdapter;
import com.vimal.retrofitpostapi.adapter.PostAdapter;
import com.vimal.retrofitpostapi.api.model.Category;
import com.vimal.retrofitpostapi.api.model.CategoryMain;
import com.vimal.retrofitpostapi.api.model.Jsonpost;
import com.vimal.retrofitpostapi.api.viewmodel.CategoryViewModel;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity implements PostAdapter.OnShareClickedListener, CategoryAdapter.OnCategoryClickedListener{

    RecyclerView recyclerviewcategory,recyclerviewpost;
    CategoryAdapter adapter;
    List<Category> categorylist;
    ArrayList<Jsonpost> categorypost;
    PostAdapter postAdapter;
    int selectedpos = 0;
    int selectedcategory = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        categorypost = new ArrayList<Jsonpost>();

        recyclerviewcategory = findViewById(R.id.recyclerviewcategory);
        recyclerviewpost = findViewById(R.id.recyclerviewpost);
        loadjson();
        setPostadapter();
    }

    public void loadjson() {


        Log.e("vml"," loadjson");

        LinearLayoutManager HorizontalLayoutcat = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerviewcategory.setLayoutManager(HorizontalLayoutcat);
        CategoryViewModel categoryViewModel = ViewModelProviders.of(this).get(CategoryViewModel.class);
        if (isConnected()) {
            categoryViewModel.getCategory().observe(this, new Observer<CategoryMain>() {
                @Override
                public void onChanged(@Nullable CategoryMain heroList) {
                    if (heroList.getData() != null && heroList.getData().size() > 0) {

                        categorylist = heroList.getData();
                        Realm realm = Realm.getDefaultInstance();
                        realm.beginTransaction();
                        realm.delete(Category.class);
                        realm.copyToRealmOrUpdate(categorylist);
                        realm.commitTransaction();

                        Log.e("vml", heroList.getData().size() + " CategoryViewModel_size");

                        adapter = new CategoryAdapter(MainActivity.this, categorylist);
                        recyclerviewcategory.setAdapter(adapter);
                        recyclerviewcategory.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(MainActivity.this, R.anim.layout_animation_right_to_left));
                        adapter.setOnCategoryClickedListener(MainActivity.this::CategoryClicked);
                    }else {
                        Log.e("vml","0 size");
                    }

                }
            });
        } else {
            RealmResults<Category> heroList = MyApp.realm.where(Category.class).findAll();
            adapter = new CategoryAdapter(MainActivity.this, heroList);
            recyclerviewcategory.setAdapter(adapter);
            recyclerviewcategory.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(MainActivity.this, R.anim.layout_animation_right_to_left));
        }


    }

    public boolean isConnected() {
        boolean connected = false;
        try {
            ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo nInfo = cm.getActiveNetworkInfo();
            connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
            return connected;
        } catch (Exception e) {
            Log.e("Connectivity Exception", e.getMessage());
        }
        return connected;
    }

    public void setPostadapter() {

        categorypost.clear();
        categorypost.add(new Jsonpost("Aaj se Teri", R.drawable.couple));
        categorypost.add(new Jsonpost("Tere mere", R.drawable.couple));
        categorypost.add(new Jsonpost("Hindi", R.drawable.couple));
        categorypost.add(new Jsonpost("Love", R.drawable.couple));
        categorypost.add(new Jsonpost("Dialogue", R.drawable.couple));
        categorypost.add(new Jsonpost("Bengali", R.drawable.couple));
        categorypost.add(new Jsonpost("Bhojpuri", R.drawable.couple));
        categorypost.add(new Jsonpost("Birthday", R.drawable.couple));
        categorypost.add(new Jsonpost("Calender", R.drawable.couple));
        categorypost.add(new Jsonpost("English", R.drawable.couple));

        postAdapter = new PostAdapter(MainActivity.this, categorypost);
        LinearLayoutManager HorizontalLayoutpost = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerviewpost.setLayoutManager(HorizontalLayoutpost);
        postAdapter.setOnShareClickedListener(this);
        recyclerviewpost.setAdapter(postAdapter);
        recyclerviewpost.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(this, R.anim.layout_animation_left_to_right));
    }

    @Override
    public void CategoryClicked(int pos) {
        selectedcategory = pos;
        setPostadapter();

    }

    @Override
    public void ShareClicked(int pos) {
        Log.e("vml", pos + "pos");
        selectedpos = pos;
//        Intent mIntent = new Intent(MainActivity.this, PickImageActivity.class);
//        mIntent.putExtra(PickImageActivity.KEY_LIMIT_MAX_IMAGE, 60);
//        mIntent.putExtra(PickImageActivity.KEY_LIMIT_MIN_IMAGE, 3);
//        startActivityForResult(mIntent, PickImageActivity.PICKER_REQUEST_CODE);
    }

}