package net.aaronlab.searchrepo.models

import com.google.gson.annotations.SerializedName

/*
Repo 목록 모델
 */
data class GithubRepositoriesModel(

    @SerializedName("total_count")
    val totalCount: Int?,

    @SerializedName("items")
    val items: List<GithubRepositoryModel>?

)

/*
Repo 상세 모델
 */
data class GithubRepositoryModel(

    @SerializedName("id")
    val id: Long?,

    @SerializedName("full_name")
    val fullName: String?,

    @SerializedName("html_url")
    val htmlURL: String?,

    @SerializedName("description")
    val description: String?,

    @SerializedName("stargazers_count")
    val starGazersCount: Int?,

    @SerializedName("owner")
    val owner: GithubRepositoryOwnerModel?

)

/*
Repo 소유자
 */
data class GithubRepositoryOwnerModel(

    @SerializedName("avatar_url")
    val avatarURL: String?

)
