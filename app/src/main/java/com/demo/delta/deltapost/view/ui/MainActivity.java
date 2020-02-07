package com.demo.delta.deltapost.view.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.demo.delta.deltapost.R;
import com.demo.delta.deltapost.databinding.ActivityMainBinding;
import com.demo.delta.deltapost.model.Post;
import com.demo.delta.deltapost.view.adapter.PostAdapter;
import com.demo.delta.deltapost.viewmodel.PostViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private PostAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        adapter = new PostAdapter();
        binding.postList.setAdapter(adapter);

         PostViewModel viewModel = ViewModelProviders.of(this).get(PostViewModel.class);
         observer(viewModel);

    }




    private void observer(PostViewModel viewModel) {
        viewModel.getPostListObservable().observe(this, new Observer<List<Post>>() {
            @Override
            public void onChanged(@Nullable List<Post> data) {
                if (data != null) {
                    adapter.setPostList(data);
                }
            }
        });
    }
}
