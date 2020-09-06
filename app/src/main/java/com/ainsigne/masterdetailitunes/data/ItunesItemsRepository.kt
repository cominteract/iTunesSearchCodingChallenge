package com.ainsigne.masterdetailitunes.data

import androidx.lifecycle.LiveData
import com.ainsigne.masterdetailitunes.interfaces.ItemRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Repository module for handling data operations for the [ItunesItem].
 * Repository pattern is excellent for abstracting the data access used.
 * In this case we only know that we are passing parameters and info
 * We don't know how it is saved
 * @param itunesItemDao [ItunesItemDao] Data Access Object for the [ItunesItem] class.
 * @param itunesSearchDao [ItunesSearchDao] The Data Access Object for the [ItunesSearch] class.
 */
class ItunesItemsRepository(var itunesItemDao : ItunesItemDao, var itunesSearchDao: ItunesSearchDao) : ItemRepository {

    /**
     * Returns saved itune items to be displayed identified through its itunesSearch parameters
     * @param itunesSearch [ItunesSearch] the identifier for whiched searched parameters to filter with
     * @return [LiveData] of [List] of [ItunesItem] identified through its itunesSearch
     */
    override fun getItunesItemFromSearch(itunesSearch: ItunesSearch): LiveData<List<ItunesItem>> {
        return itunesItemDao.getItunesItemFromSearch(itunesSearch.country, itunesSearch.term, itunesSearch.media)
    }

    /**
     * Returns saved itune items to be displayed ordered by its trackName
     * @return [LiveData] of [List] of [ItunesItem] ordered by its trackName
     */
    override fun getItunesItems() =  itunesItemDao.getItunesItems()

    /**
     * Returns saved itune item identified through its trackId
     * @param itunesId_ [String] the identifier to identify the [ItunesItem] to return
     * @return [LiveData] of [ItunesItem] identified through its trackId
     */
    override fun getItunesItem(itunesId : String) = itunesItemDao.getItunesItemFromId(itunesId)

    /**
     * Returns saved itunes searched to be displayed and its timestamp to when it is searched
     * @param itunesSearch [ItunesSearch] the identifier for whiched searched parameters to filter with
     * @return [LiveData]  of [ItunesSearch] the itunes searched and its timestamp to when it is searched
     */
    override fun getItunesSearches(itunesSearch: ItunesSearch): LiveData<ItunesSearch> {
        return itunesSearchDao.getItuneSearchesFrom(itunesSearch.country,itunesSearch.term,itunesSearch.media)
    }

    /**
     * TODO : unused
     */
    override fun insertItunesItem(itunesItem: ItunesItem) {

    }
    /**
     * Saves the ituneitems entity list to room db
     * @param items [List] of [ItunesItem] the items to add and save
     */
    override fun insertAll(itunesItems: List<ItunesItem>) {
        CoroutineScope(Dispatchers.IO).launch {
            itunesItemDao.insertAll(itunesItems)
        }
    }
    /**
     * Saves the itune search entity to room db
     * @param item [ItunesSearch] the search item to add and save
     */
    override fun insertItunesSearch(itunesSearch: ItunesSearch) {
        CoroutineScope(Dispatchers.IO).launch {
            itunesSearchDao.insertSearch(itunesSearch)
        }
    }
}
