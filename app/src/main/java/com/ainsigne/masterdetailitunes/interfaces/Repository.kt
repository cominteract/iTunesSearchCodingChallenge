package com.ainsigne.masterdetailitunes.interfaces

import androidx.lifecycle.LiveData
import com.ainsigne.masterdetailitunes.data.ItunesItem
import com.ainsigne.masterdetailitunes.data.ItunesSearch

/**
 * Common interface for the item repository so they can be interswapped with mock or real data
 */
interface ItemRepository {
    /**
     * Returns itune items to be displayed ordered by its trackName
     * @return [LiveData] of [List] of [ItunesItem] ordered by its trackName
     */
    fun getItunesItems() : LiveData<List<ItunesItem>>
    /**
     * Returns itune item identified through its trackId
     * @param itunesId_ [String] the identifier to identify the [ItunesItem] to return
     * @return [LiveData] of [ItunesItem] identified through its trackId
     */
    fun getItunesItem(itunesId : String) : LiveData<ItunesItem>
    /**
     * Returns itune items to be displayed identified through its itunesSearch parameters
     * @param itunesSearch [ItunesSearch] the identifier for whiched searched parameters to filter with
     * @return [LiveData] of [List] of [ItunesItem] identified through its itunesSearch
     */
    fun getItunesItemFromSearch(itunesSearch : ItunesSearch) : LiveData<List<ItunesItem>>
    /**
     * Returns itunes searched to be displayed and its timestamp to when it is searched
     * @param itunesSearch [ItunesSearch] the identifier for whiched searched parameters to filter with
     * @return [LiveData]  of [ItunesSearch] the itunes searched and its timestamp to when it is searched
     */
    fun getItunesSearches(itunesSearch : ItunesSearch) : LiveData<ItunesSearch>
    /**
     * Adds a single itune item to a mocked list
     * @param itunesItem [ItunesItem]] the fake itune item to add
     */
    fun insertItunesItem(itunesItem: ItunesItem)
    /**
     * Adds the itune search entity
     * @param item [ItunesSearch] the search item to add
     */
    fun insertItunesSearch(itunesSearch: ItunesSearch)
    /**
     * Adds the ituneitems entity list
     * @param items [List] of [ItunesItem] the items to add
     */
    fun insertAll(itunesItems : List<ItunesItem>)

}

