package com.ermolaev_arkhip.redditapp.di

import com.ermolaev_arkhip.redditapp.data.PopularPostsPagingSource
import com.ermolaev_arkhip.redditapp.data.repository.PopularPostsRepositoryImpl
import com.ermolaev_arkhip.redditapp.data.RedditApiService
import com.ermolaev_arkhip.redditapp.data.mapper.RedditResponseMapper
import com.ermolaev_arkhip.redditapp.domain.repository.PopularPostsRepository
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object DataModule {
    private const val REDDIT_URL = "https://www.reddit.com/r/aww/"

    val value = module {
        single<RedditApiService> {
            Retrofit.Builder()
                .baseUrl(REDDIT_URL)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RedditApiService::class.java)
        }

        single {
            RedditResponseMapper
        }

        single<PopularPostsRepository> {
            PopularPostsRepositoryImpl(
                pagingSource = get()
            )
        }
        single {
            PopularPostsPagingSource(
                apiService = get(),
                mapper = get()
            )
        }
    }
}