package com.ainsigne.masterdetailitunes.api

import com.ainsigne.masterdetailitunes.data.ItunesResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * [ItunesSearchService] webservice access to issue a [GET] request to itunes search api
 */
interface ItunesSearchService {
    /**
     * searchAPI the retrofit service created using the endpoint search with term,media and country parameters
     * @param term as String the term parameter used in GET request
     * @param media as String the media parameter used in GET request
     * @param country as String the country parameter used in GET request
     * @return Call<ItunesResult> response from the retrofit call
     **/
    @GET("search")
    suspend fun searchAPI(@Query("term") term: String,
                  @Query("media") media: String,
                  @Query("country") country: String): Response<ItunesResult>
}

