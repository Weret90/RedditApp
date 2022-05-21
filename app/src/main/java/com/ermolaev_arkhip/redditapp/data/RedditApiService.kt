package com.ermolaev_arkhip.redditapp.data

import com.ermolaev_arkhip.redditapp.data.model.RedditResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RedditApiService {

    @GET(value = "hot.json")
    fun getPopularPosts(
        @Query("count") page: Int,
        @Query("limit") pageSize: Int,
    ): Single<RedditResponse>
}