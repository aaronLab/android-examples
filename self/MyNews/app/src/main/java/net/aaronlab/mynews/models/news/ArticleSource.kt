package net.aaronlab.mynews.models.news

import com.google.gson.annotations.SerializedName

/*
기사 출처 소스
 */
data class ArticleSource(

    @SerializedName("id")
    val id: String?,

    @SerializedName("name")
    val name: String?

)
