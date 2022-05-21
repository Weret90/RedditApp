package com.ermolaev_arkhip.redditapp.domain.repository

import androidx.paging.PagingData
import com.ermolaev_arkhip.redditapp.domain.entity.PopularPost
import io.reactivex.rxjava3.core.Flowable

interface PopularPostsRepository {

    fun getPopularPosts(): Flowable<PagingData<PopularPost>>
}