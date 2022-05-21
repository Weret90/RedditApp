package com.ermolaev_arkhip.redditapp.data

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import com.ermolaev_arkhip.redditapp.data.mapper.RedditResponseMapper
import com.ermolaev_arkhip.redditapp.domain.entity.PopularPost
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class PopularPostsPagingSource(
    private val apiService: RedditApiService,
    private val mapper: RedditResponseMapper,
) : RxPagingSource<Int, PopularPost>() {

    override fun getRefreshKey(state: PagingState<Int, PopularPost>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, PopularPost>> {
        val page = params.key ?: 0
        val pageSize = params.loadSize

        return apiService.getPopularPosts(page, pageSize)
            .subscribeOn(Schedulers.io())
            .map {
                mapper.mapResponseToDomainModelList(it)
            }
            .map {
                toLoadResult(it, page, pageSize)
            }
            .onErrorReturn {
                LoadResult.Error(it)
            }
    }

    private fun toLoadResult(posts: List<PopularPost>, page: Int, pageSize: Int): LoadResult<Int, PopularPost> {
        return LoadResult.Page(
            data = posts,
            nextKey = if (posts.size < pageSize) null else page + 1,
            prevKey = if (page == 0) null else page - 1
        )
    }
}