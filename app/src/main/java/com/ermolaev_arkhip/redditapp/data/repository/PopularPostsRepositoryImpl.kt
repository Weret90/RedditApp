package com.ermolaev_arkhip.redditapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava3.flowable
import com.ermolaev_arkhip.redditapp.data.PopularPostsPagingSource
import com.ermolaev_arkhip.redditapp.domain.entity.PopularPost
import com.ermolaev_arkhip.redditapp.domain.repository.PopularPostsRepository
import io.reactivex.rxjava3.core.Flowable

class PopularPostsRepositoryImpl(
    private val pagingSource: PopularPostsPagingSource,
) : PopularPostsRepository {

    companion object {
        private const val POSTS_IN_ONE_LOADING = 20
    }

    override fun getPopularPosts(): Flowable<PagingData<PopularPost>> =
        Pager(
            config = PagingConfig(
                pageSize = POSTS_IN_ONE_LOADING
            ),
            pagingSourceFactory = { pagingSource }
        ).flowable
}