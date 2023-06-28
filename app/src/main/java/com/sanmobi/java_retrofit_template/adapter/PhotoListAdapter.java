package com.sanmobi.java_retrofit_template.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.sanmobi.java_retrofit_template.R;
import com.sanmobi.java_retrofit_template.model.Photo;
import java.util.List;

public class PhotoListAdapter extends RecyclerView.Adapter<PhotoListAdapter.MyviewHolder> {

    Context context;
    List<Photo> photoList;

    public PhotoListAdapter(Context context, List<Photo> movieList) {
        this.context = context;
        this.photoList = movieList;
    }

    public void setPhotoList(List<Photo> photoList) {
        this.photoList = photoList;
        notifyDataSetChanged();
    }

    @Override
    public PhotoListAdapter.MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.listitem_photo,parent,false);
        return new MyviewHolder(view);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(PhotoListAdapter.MyviewHolder holder,  int position) {

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Photo photo = photoList.get(position);
                Toast.makeText(context, photo.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });

        holder.tvName.setText(photoList.get(position).getTitle().toString());

        Glide.with(context).load(photoList.get(position).getThumbnailUrl()).apply(RequestOptions.centerCropTransform()).into(holder.image);

    }

    @Override
    public int getItemCount() {
        if(photoList != null){
            return photoList.size();
        }
        return 0;

    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        ImageView image;

        public MyviewHolder(View itemView) {
            super(itemView);
            tvName = (TextView)itemView.findViewById(R.id.title);
            image = (ImageView)itemView.findViewById(R.id.image);
        }
    }
}