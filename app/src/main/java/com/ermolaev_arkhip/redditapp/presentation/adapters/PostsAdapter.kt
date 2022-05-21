package com.ermolaev_arkhip.redditapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ermolaev_arkhip.redditapp.databinding.ItemPostBinding
import com.ermolaev_arkhip.redditapp.domain.entity.PopularPost

class PostsAdapter : PagingDataAdapter<PopularPost, PostsViewHolder>(PostDiffItemCallback) {
    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val binding = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostsViewHolder(binding)
    }
}

class PostsViewHolder(private val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(post: PopularPost?) {
        with(binding) {
            title.text = post?.title
            likes.text = post?.numLikes.toString()
            comments.text = post?.numComments.toString()
        }
    }
}

private object PostDiffItemCallback : DiffUtil.ItemCallback<PopularPost>() {
    override fun areItemsTheSame(oldItem: PopularPost, newItem: PopularPost): Boolean =
        oldItem.title == newItem.title

    override fun areContentsTheSame(oldItem: PopularPost, newItem: PopularPost): Boolean =
        oldItem == newItem
}