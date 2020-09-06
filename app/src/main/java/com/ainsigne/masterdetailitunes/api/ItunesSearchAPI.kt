package com.ainsigne.masterdetailitunes.api

import com.ainsigne.masterdetailitunes.utils.BASE_API
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * [ItunesSearchAPI] its retrofit service builder for making http calls to itunes search api
 */
class ItunesSearchAPI{
    /**
     * [ItunesSearchService] webservice access to issue a [GET] request to itunes search api
     */
    val webservice: ItunesSearchService by lazy {
        val client = OkHttpClient.Builder().connectTimeout(15, TimeUnit.SECONDS).readTimeout(15, TimeUnit.SECONDS).build()
        Retrofit.Builder()
            .baseUrl(BASE_API).client(client)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(ItunesSearchService::class.java)
    }
}

