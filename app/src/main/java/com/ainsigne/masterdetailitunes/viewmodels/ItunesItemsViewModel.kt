package com.ainsigne.masterdetailitunes.viewmodels

import androidx.lifecycle.*
import com.ainsigne.masterdetailitunes.data.ItunesItem
import com.ainsigne.masterdetailitunes.data.ItunesSearch
import com.ainsigne.masterdetailitunes.interfaces.ItemRepository

/**
 * The ViewModel for [ItunesItems].
 * @param repo [ItemRepository] common interface for the item repository so they can be interswapped with mock or real data
 */
class ItunesItemsViewModel internal constructor(repo: ItemRepository) : ViewModel() {
    /**
     * repo [ItemRepository] common interface for the item repository so they can be interswapped with mock or real data
     */
    private val repo_ = repo
    /**
     * Returns itune items to be displayed ordered by its trackName
     * @return [LiveData] of [List] of [ItunesItem] ordered by its trackName
     */
    var ituneTracks = repo.getItunesItems()

    /**
     * TODO : unused
     */
    fun ituneTracksFromSearch(itunesSearch: ItunesSearch) : LiveData<List<ItunesItem>> {
        return repo_.getItunesItemFromSearch(itunesSearch)
    }

    /**
     * Returns itune item identified through its trackId
     * @param itunesId_ [String] the identifier to identify the [ItunesItem] to return
     * @return [LiveData] of [ItunesItem] identified through its trackId
     */
    fun ituneTrackFromId(trackId : String) : LiveData<ItunesItem>{
        return repo_.getItunesItem(trackId)
    }

    /**
     * Returns itunes searched to be displayed and its timestamp to when it is searched
     * @param itunesSearch [ItunesSearch] the identifier for whiched searched parameters to filter with
     * @return [LiveData]  of [ItunesSearch] the itunes searched and its timestamp to when it is searched
     */
    fun itunesSearches(itunesSearch: ItunesSearch) : LiveData<ItunesSearch> {
        return repo_.getItunesSearches(itunesSearch)
    }
}
