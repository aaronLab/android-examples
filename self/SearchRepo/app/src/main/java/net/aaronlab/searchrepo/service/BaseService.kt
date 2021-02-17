package net.aaronlab.searchrepo.service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BaseService {

    fun getClient(baseURL: String): Retrofit? = Retrofit.Builder()
        .baseUrl(baseURL)
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

}