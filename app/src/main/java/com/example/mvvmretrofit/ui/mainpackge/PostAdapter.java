package com.example.mvvmretrofit.ui.mainpackge;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmretrofit.R;
import com.example.mvvmretrofit.model.PostsModel;

import java.util.List;
import java.util.ArrayList;


public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private List<PostsModel> postsModels = new ArrayList<>();


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PostsModel post=postsModels.get(position);
        holder.titleTV.setText(post.getTitle());
        holder.userTV.setText(post.getUserId()+"");
        holder.bodyTV.setText(post.getBody());

    }

    @Override
    public int getItemCount() {

        return postsModels == null ? 0 : postsModels.size();
    }

    public void setList(List<PostsModel> postsModels) {
        this.postsModels = postsModels;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTV, userTV, bodyTV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTV = itemView.findViewById(R.id.titleTV);
            userTV = itemView.findViewById(R.id.userIDTV);
            bodyTV = itemView.findViewById(R.id.bodyTV);
        }
    }
}
