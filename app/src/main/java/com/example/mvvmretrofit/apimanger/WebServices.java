package com.example.mvvmretrofit.apimanger;

import com.example.mvvmretrofit.model.PostsModel;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;

public interface WebServices {
    @GET("posts")
    Single<List<PostsModel>> getPostModel();
}
