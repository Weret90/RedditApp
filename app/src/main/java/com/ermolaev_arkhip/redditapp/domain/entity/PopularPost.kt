package com.ermolaev_arkhip.redditapp.domain.entity

data class PopularPost(
    val numComments: Int,
    val title: String,
    val numLikes: Int,
)