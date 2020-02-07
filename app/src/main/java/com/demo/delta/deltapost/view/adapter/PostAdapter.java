package com.demo.delta.deltapost.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.demo.delta.deltapost.R;
import com.demo.delta.deltapost.databinding.PostRowBinding;
import com.demo.delta.deltapost.model.Post;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder>{
    private List<? extends Post> postList;

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PostRowBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.post_row, parent,false);

        return new  PostViewHolder(binding);
    }



    public  void  setPostList (final List<? extends Post> postList){
        if(this.postList == null){
            this.postList = postList;
            notifyItemRangeInserted(0,postList.size());
        }
        else
        {
            DiffUtil.DiffResult  result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return PostAdapter.this.postList.size();
                }

                @Override
                public int getNewListSize() {
                    return  postList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return PostAdapter.this.postList.get(oldItemPosition).getTitle() ==
                            postList.get(newItemPosition).getTitle();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Post post = postList.get(newItemPosition);
                    Post oldPost = postList.get(oldItemPosition);

                    return post.getTitle().equals(oldPost.getTitle());
                }
            });
            this.postList = postList;
            result.dispatchUpdatesTo(this);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        holder.binding.setPost(postList.get(position));
        holder.binding.executePendingBindings();
    }


    @Override
    public int getItemCount() {
        return postList == null ? 0 : postList.size();
    }


    static class PostViewHolder extends RecyclerView.ViewHolder{
        final PostRowBinding binding;

        public PostViewHolder(@NonNull PostRowBinding binding)  {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
