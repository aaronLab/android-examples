package net.aaronlab.mynews.models.news

import com.google.gson.annotations.SerializedName

data class NewsResponse(

    @SerializedName("status")
    val status: String?,

    @SerializedName("totalResults")
    val totalResults: Long?,

    @SerializedName("articles")
    val articles: List<Article>?

)