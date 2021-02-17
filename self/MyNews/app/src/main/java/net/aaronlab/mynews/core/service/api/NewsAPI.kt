package net.aaronlab.mynews.core.service.api

import net.aaronlab.mynews.models.news.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

    /*
    탑 헤드라인 뉴스 목록
     */
    @GET("v2/top-headlines")
    suspend fun getTopHeadLines(
        @Query("country") country: String = "us",
        @Query("pageSize") pageSize: Int,
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String = APIKey.KEY
    ): Response<NewsResponse>

    /*
    전체 뉴스에서 검색
     */
    @GET("v2/everything")
    suspend fun getSearchedNews(
        @Query("q") query: String,
        @Query("pageSize") pageSize: Int,
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String = APIKey.KEY
    ): Response<NewsResponse>

}