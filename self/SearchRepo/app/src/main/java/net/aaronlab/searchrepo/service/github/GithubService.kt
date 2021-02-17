package net.aaronlab.searchrepo.service.github

import net.aaronlab.searchrepo.service.BaseService

/*
Github Service
 */
object GithubService {

    private const val GITHUB_URL = "https://api.github.com"

    val client = BaseService().getClient(GITHUB_URL)?.create(GithubAPI::class.java)
}