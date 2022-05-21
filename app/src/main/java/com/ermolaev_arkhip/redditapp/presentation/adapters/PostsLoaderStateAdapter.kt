package com.ermolaev_arkhip.redditapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ermolaev_arkhip.redditapp.databinding.ActivityMainBinding

typealias TryAgainAction = () -> Unit

class PostsLoaderStateAdapter(
    private val tryAgainAction: TryAgainAction
) : LoadStateAdapter<PostsLoaderStateAdapter.Holder>() {

    override fun onBindViewHolder(holder: Holder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): Holder {
        val binding = ActivityMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding, tryAgainAction)
    }

    class Holder(
        private val binding: ActivityMainBinding,
        private val tryAgainAction: TryAgainAction
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.errorButton.setOnClickListener {
                tryAgainAction()
            }
        }

        fun bind(loadState: LoadState) {
            binding.errorButton.isVisible = loadState is LoadState.Error
            binding.progressBar.isVisible = loadState is LoadState.Loading
        }
    }
}