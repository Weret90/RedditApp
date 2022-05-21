package com.ermolaev_arkhip.redditapp.data.model

import com.google.gson.annotations.SerializedName

data class RedditResponse(
    @SerializedName("data")
    val data: ResponseData,
)

data class ResponseData(
    @SerializedName("children")
    val popularPosts: List<PopularPostDto>,
)

data class PopularPostDto(
    @SerializedName("data")
    val info: PostInfoDto,
)

data class PostInfoDto(
    @SerializedName("num_comments")
    val numComments: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("ups")
    val numLikes: Int,
)