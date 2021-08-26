/**
 * Created by Vimal on June-2021.
 */
package com.vimal.retrofitpostapi.adapter;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.vimal.retrofitpostapi.R;
import com.vimal.retrofitpostapi.api.model.Category;

import java.util.List;
import java.util.Random;


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyView> {

    Context context;
    private List<Category> list;
    MediaPlayer mediaPlayer;
    public int selectedpos = 0;
    private Random mRandom = new Random();
    OnCategoryClickedListener mCallbackcategory;

    public class MyView extends RecyclerView.ViewHolder {

        TextView textView;
        LinearLayout mainlay;
        ImageView image;
        RelativeLayout selctionlay;

        public MyView(View view) {
            super(view);
            mainlay = view.findViewById(R.id.mainlay);
            textView = view.findViewById(R.id.textview);
            image = view.findViewById(R.id.image);
            selctionlay = view.findViewById(R.id.selctionlay);
        }
    }


    public CategoryAdapter(Context context, List<Category> horizontalList) {
        this.context = context;
        this.list = horizontalList;
        mediaPlayer = new MediaPlayer();
    }

    @Override
    public MyView onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_category, parent, false);
        return new MyView(itemView);
    }

    public void setOnCategoryClickedListener(OnCategoryClickedListener mCallback) {
        this.mCallbackcategory = mCallback;
    }

    public interface OnCategoryClickedListener {
        public void CategoryClicked(int pos);
    }

    @Override
    public void onBindViewHolder(final MyView holder, final int position) {

        if (selectedpos == position) {
//            holder.selctionlay.getLayoutParams().height = 90;
            holder.textView.setTextColor(context.getResources().getColor(R.color.purple_200));
        } else {
//            holder.selctionlay.getLayoutParams().height = 60;
            holder.textView.setTextColor(context.getResources().getColor(R.color.black));
        }

        Glide.with(context).load(list.get(position).getCategoryImagePath()).placeholder(R.mipmap.ic_launcher).into(holder.image);
        holder.textView.setText(list.get(position).getCategoryName());



        holder.mainlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectedpos = position;
                mCallbackcategory.CategoryClicked(position);
                notifyDataSetChanged();

            }
        });


    }

    protected int getRandomIntInRange(int max, int min){
        return mRandom.nextInt((max-min)+min)+min;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
