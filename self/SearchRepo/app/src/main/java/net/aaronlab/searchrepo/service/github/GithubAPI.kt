package net.aaronlab.searchrepo.service.github

import net.aaronlab.searchrepo.models.GithubRepositoriesModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/*
Github API Endpoint
 */
interface GithubAPI {

    @GET("search/repositories")
    suspend fun getRepositories(
        @Query("q") query: String
    ): Response<GithubRepositoriesModel>

}