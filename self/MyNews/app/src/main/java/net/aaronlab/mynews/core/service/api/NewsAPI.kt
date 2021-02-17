package net.aaronlab.mynews.core.service.api

import net.aaronlab.mynews.models.news.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

    @GET("v2/top-headlines")
    suspend fun getTopHeadLines(
        @Query("pageSize") pageSize: Int,
        @Query("page") page: Int
    ): Response<NewsResponse>

}