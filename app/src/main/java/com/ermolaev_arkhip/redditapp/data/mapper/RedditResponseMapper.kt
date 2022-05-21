package com.ermolaev_arkhip.redditapp.data.mapper

import com.ermolaev_arkhip.redditapp.data.model.PopularPostDto
import com.ermolaev_arkhip.redditapp.data.model.RedditResponse
import com.ermolaev_arkhip.redditapp.domain.entity.PopularPost

object RedditResponseMapper {

    fun mapResponseToDomainModelList(response: RedditResponse): List<PopularPost> =
        response.data.popularPosts.map {
            mapDtoToDomainModel(it)
        }

    private fun mapDtoToDomainModel(popularPostDto: PopularPostDto): PopularPost =
        PopularPost(
            numComments = popularPostDto.info.numComments,
            title = popularPostDto.info.title,
            numLikes = popularPostDto.info.numLikes
        )
}