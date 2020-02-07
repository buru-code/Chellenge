package com.demo.delta.deltapost.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.demo.delta.deltapost.model.Post;
import com.demo.delta.deltapost.service.PostRepository;

import java.util.List;

public class PostViewModel extends AndroidViewModel {

    private LiveData<List<Post>> postListObservable;

    public PostViewModel(@NonNull Application application) {
        super(application);
        postListObservable = PostRepository.getInstance().getAllPosts();
    }

    public LiveData<List<Post>> getPostListObservable(){
        return postListObservable;
    }
}
