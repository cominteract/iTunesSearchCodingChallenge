package com.ainsigne.masterdetailitunes.fake

import com.ainsigne.masterdetailitunes.data.ItunesItem
import com.ainsigne.masterdetailitunes.data.ItunesSearch
import com.ainsigne.masterdetailitunes.utils.*
import java.util.*
import kotlin.collections.ArrayList

/**
 * Fake store for accessing mock data
 */
class FakeStore {

    /**
     * [ArrayList] of [ItunesItem] mocked itune items
     */
    private var itunesItems = ArrayList<ItunesItem>()
    /**
     * [ArrayList] of [ItunesSearch] mocked itune searches
     */
    private var itunesSearches = ArrayList<ItunesSearch>()

    init {
        mockTracks()
    }
    /**
     * generates mock itunes tracks to be used in unit tests or fake api
     */
    private fun mockTracks(){

        for(i in randomIds.indices)
        {
            var itunesSearch = ItunesSearch()
            itunesSearch.timestamp = Date().toStringFormatFull()
            itunesSearch.media = medias[i]
            var max = countryCodes.size - 1
            var min = 0
            var rand : Int = Random().nextInt(max - min + 1) + min
            itunesSearch.country = countryCodes[rand]
            itunesSearch.term = randomTracks[i]
            itunesSearches.add(itunesSearch)
        }
        for(i in randomIds.indices) {
            val itunesItem = ItunesItem(itunesSearch = itunesSearches[i])
            itunesItem.trackId = randomIds[i]
            itunesItem.primaryGenreName = randomGenre[i]
            itunesItem.trackName = randomTracks[i]
            itunesItem.artworkUrl100 = artworkUrls[i]
            itunesItem.currency = "USD"
            itunesItem.artworkUrl60 = itunesItem.artworkUrl100?.replace("100x100","60x60")
            itunesItem.artworkUrl150 = itunesItem.artworkUrl100?.replace("100x100","150x150")
            itunesItem.artworkUrl400 = itunesItem.artworkUrl100?.replace("100x100","600x600")
            itunesItem.longDescription = loren
            val start = 1.0
            val end = 150.0
            val random = Random().nextDouble()
            val price = start + random * (end - start)
            val trackPrice = Math.round(price * 100.0) / 100.0
            itunesItem.trackPrice = trackPrice
            itunesItems.add(itunesItem)
        }
    }

    /**
     * [ArrayList] of [ItunesItem] mocked itune items
     */
    fun getItunesItems() : ArrayList<ItunesItem>{
        return itunesItems
    }
    /**
     * [ArrayList] of [ItunesSearch] mocked itune searches
     */
    fun getItunesSearches() : ArrayList<ItunesSearch>{
        return itunesSearches
    }
}