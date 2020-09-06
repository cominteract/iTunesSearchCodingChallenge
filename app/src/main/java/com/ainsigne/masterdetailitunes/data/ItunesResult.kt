package com.ainsigne.masterdetailitunes.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * ItunesResult class representing the response from retrofit
 */
class ItunesResult {

    /**
     * TODO : unused
     */
    @SerializedName("resultCount")
    @Expose
    var resultCount : Int? = null

    /**
     * results as List<ItunesItem> the list of itunes track
     */
    @SerializedName("results")
    @Expose
    var results : List<ItunesItem>? = null
}