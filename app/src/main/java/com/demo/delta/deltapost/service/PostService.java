package com.demo.delta.deltapost.service;

import com.demo.delta.deltapost.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PostService {
     String BASE_URL= "";

    @GET("allPost")
    Call<List<Post>> getAllPost();

    @GET("postInfo/{postId}")
    Call<Post> getPostInfo();


}
