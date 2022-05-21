package com.ermolaev_arkhip.redditapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ermolaev_arkhip.redditapp.databinding.ActivityMainBinding
import com.ermolaev_arkhip.redditapp.presentation.adapters.PostsAdapter
import com.ermolaev_arkhip.redditapp.presentation.adapters.PostsLoaderStateAdapter
import com.ermolaev_arkhip.redditapp.presentation.adapters.TryAgainAction
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModel()
    private val adapter: PostsAdapter by lazy {
        PostsAdapter()
    }
    private val loaderStateAdapter by lazy {
        val tryAgainAction: TryAgainAction = { adapter.retry() }
        PostsLoaderStateAdapter(tryAgainAction)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapterWithLoadState = adapter.withLoadStateFooter(loaderStateAdapter)
        binding.postsRecyclerView.adapter = adapterWithLoadState

        viewModel.pagingData.observe(this) {
            adapter.submitData(lifecycle, it)
        }
    }
}