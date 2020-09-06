package com.ainsigne.masterdetailitunes.fake

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ainsigne.masterdetailitunes.data.ItunesItem
import com.ainsigne.masterdetailitunes.data.ItunesSearch
import com.ainsigne.masterdetailitunes.interfaces.ItemRepository


/**
 * Repository module for handling data operations for the [ItunesItem].
 * Repository pattern is excellent for abstracting the data access used.
 * In this case we only know that we are passing parameters and info
 * We don't know how it is saved
 */
class FakeItunesItemsRepository() : ItemRepository {
    /**
     * [FakeStore] fake store for accessing mock data
     */
    var fakeStore = FakeStore()


    /**
     * Returns fake itune items to be displayed identified through its itunesSearch parameters
     * @param itunesSearch [ItunesSearch] the identifier for whiched searched parameters to filter with
     * @return [LiveData] of [List] of [ItunesItem] identified through its itunesSearch
     */
    override fun getItunesItemFromSearch(itunesSearch: ItunesSearch): LiveData<List<ItunesItem>> {
        var liveData : MutableLiveData<List<ItunesItem>> = MutableLiveData()
        liveData.value = fakeStore.getItunesItems().filter {
            it.itunesSearch.country == itunesSearch.country &&
            it.itunesSearch.term == itunesSearch.term &&
            it.itunesSearch.media == itunesSearch.media
        }
        return liveData
    }
    /**
     * Returns fake itune items to be displayed ordered by its trackName
     * @return [LiveData] of [List] of [ItunesItem] ordered by its trackName
     */
    override fun getItunesItems() : LiveData<List<ItunesItem>> {
        var liveData : MutableLiveData<List<ItunesItem>> = MutableLiveData()
        liveData.value = fakeStore.getItunesItems()
        return liveData
    }

    /**
     * Returns fake itune item identified through its trackId
     * @param itunesId_ [String] the identifier to identify the [ItunesItem] to return
     * @return [LiveData] of [ItunesItem] identified through its trackId
     */
    override fun getItunesItem(itunesId : String) : LiveData<ItunesItem>{
        var liveData : MutableLiveData<ItunesItem> = MutableLiveData()
        liveData.value = fakeStore.getItunesItems().filter { it.trackId.toString() == itunesId }.first()
        return liveData
    }

    /**
     * Returns fake itunes searched to be displayed and its timestamp to when it is searched
     * @param itunesSearch [ItunesSearch] the identifier for whiched searched parameters to filter with
     * @return [LiveData]  of [ItunesSearch] the itunes searched and its timestamp to when it is searched
     */
    override fun getItunesSearches(itunesSearch: ItunesSearch): LiveData<ItunesSearch> {
        var liveData : MutableLiveData<ItunesSearch> = MutableLiveData()
        liveData.value = fakeStore.getItunesSearches().filter { it.country == itunesSearch.country &&
                it.term == itunesSearch.term &&
                it.media == itunesSearch.media }.first()
        return liveData
    }
    /**
     * Adds a single fake itune item to a mocked list
     * @param itunesItem [ItunesItem]] the fake itune item to add
     */
    override fun insertItunesItem(itunesItem: ItunesItem) {
        fakeStore.getItunesItems().add(itunesItem)
    }
    /**
     * Adds the fake ituneitems entity list
     * @param items [List] of [ItunesItem] the items to add
     */
    override fun insertAll(itunesItems: List<ItunesItem>) {
        fakeStore.getItunesItems().addAll(itunesItems)
    }

    /**
     * Adds the fake itune search entity
     * @param item [ItunesSearch] the search item to add
     */
    override fun insertItunesSearch(itunesSearch: ItunesSearch) {
        fakeStore.getItunesSearches().add(itunesSearch)
    }

}
