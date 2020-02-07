package com.demo.delta.deltapost.service;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.demo.delta.deltapost.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostRepository {

    private static PostRepository instance;
    private PostService service ;



    public synchronized static PostRepository getInstance() {
        if (instance == null) {
            instance = new  PostRepository();
        }
        return instance;
    }


    public PostRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PostService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(PostService.class);

    }


    public LiveData<List<Post>> getAllPosts() {
        final MutableLiveData<List<Post>> data = new MutableLiveData<>();
        service.getAllPost().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                //TODO  Try error  later ...
                data.setValue(null);
            }
        });
        return data;
    }


    public LiveData<Post> getPostInfo(int id) {
        final MutableLiveData<Post> data = new MutableLiveData<>();
        service.getPostInfo().enqueue(new Callback<Post>() {
            @Override
            public void onResponse(@NonNull Call<Post> call, @NonNull Response<Post> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<Post> call, @NonNull Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }
}
