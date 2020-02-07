package com.demo.delta.deltapost.viewmodel;

import android.app.Application;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.demo.delta.deltapost.model.Post;
import com.demo.delta.deltapost.service.PostRepository;

import org.jetbrains.annotations.NotNull;
import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.TestRule;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

class PostViewModelTest {
    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    @Mock
    PostRepository repository;

    @Mock
    Application application;

    private PostViewModel viewModelPost;
    private List<Post> posts = computePosts();

    @NotNull
    private List<Post> computePosts() {
        List<Post> posts = new ArrayList<>();
        posts.add(new Post());
        posts.add(new Post());
        posts.add(new Post());
        return posts;
    }


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        when(repository.getAllPosts()).thenReturn(computeLiveData());
        viewModelPost = new PostViewModel(application, repository);
    }

    @NotNull
    private LiveData<List<Post>> computeLiveData() {
        MutableLiveData<List<Post>> liveData = new MutableLiveData<>();
        liveData.setValue(posts);
        return liveData;
    }



    @Test
    public void testGetPostListObservable() {
        LiveData<List<Post>> actual = viewModelPost.getPostListObservable();
        assertEquals(posts, actual.getValue());
    }

    @org.junit.Test
    public void testGetPostInfo() {
        Post expectedPost = new Post();
        MutableLiveData<Post> liveData = new MutableLiveData<Post>();
        liveData.postValue(expectedPost);
        when(repository.getPostInfo(1)).thenReturn(liveData);

        Post actual = viewModelPost.getPostInfo(1).getValue();

        assertEquals(expectedPost, actual);


    }

}