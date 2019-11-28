package com.example.mvvmretrofit.roomdatabase.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.mvvmretrofit.model.PostsModel;

import java.util.List;
@Dao
public interface PostDao {

    @Query("select * from PostsModel")
    List<PostsModel> getAllPosts();
    @Insert
    void cahePosts(List<PostsModel> postsModels);

}
