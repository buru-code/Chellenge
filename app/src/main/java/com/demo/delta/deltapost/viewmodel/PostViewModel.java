package com.demo.delta.deltapost.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.demo.delta.deltapost.model.Post;
import com.demo.delta.deltapost.service.PostRepository;

import java.util.List;

public class PostViewModel extends AndroidViewModel {

    private final LiveData<List<Post>> postListObservable;
    private final PostRepository postRepository;


    public PostViewModel(@NonNull Application application, @NonNull PostRepository postRepsository) {
        super(application);
        postListObservable = postRepsository.getInstance().getAllPosts();
        this.postRepository = postRepsository;
    }

    public LiveData<List<Post>> getPostListObservable(){
        return postListObservable;
    }

    public LiveData<Post> getPostInfo(int id) {
        return postRepository.getPostInfo(id);
    }
}
