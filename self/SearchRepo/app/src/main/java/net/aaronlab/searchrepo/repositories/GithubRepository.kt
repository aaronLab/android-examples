package net.aaronlab.searchrepo.repositories

import net.aaronlab.searchrepo.service.github.GithubService

/*
Github Repository
 */
class GithubRepository {

    private val githubClient = GithubService.client

    suspend fun getRepositories(query: String) = githubClient?.getRepositories(query)

}
