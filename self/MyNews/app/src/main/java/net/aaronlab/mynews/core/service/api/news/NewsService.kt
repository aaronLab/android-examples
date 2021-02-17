package net.aaronlab.mynews.core.service.api.news

import net.aaronlab.mynews.core.service.BaseService

/*
뉴스 서비스
 */
object NewsService {

    private const val BASE_URL = "https://newsapi.org/"

    val client = BaseService().getClient(BASE_URL)?.create(NewsAPI::class.java)

}