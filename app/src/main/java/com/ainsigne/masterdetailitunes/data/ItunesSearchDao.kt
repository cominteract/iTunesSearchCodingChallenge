package com.ainsigne.masterdetailitunes.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * The Data Access Object for the [ItunesSearch] class.
 */
@Dao
interface ItunesSearchDao {
    /**
     * TODO : unused
     */
    @Query("SELECT * FROM itunes_searches ORDER BY timestamp")
    fun getItunesSearches(): LiveData<List<ItunesSearch>>

    /**
     * Returns saved itunes searched to be displayed and its timestamp to when it is searched
     * @param itunesSearch_country [String] the identifier for whiched searched country to filter with
     * @param itunesSearch_term [String] the identifier for whiched searched term to filter with
     * @param itunesSearch_media [String] the identifier for whiched searched media to filter with
     * @return [LiveData]  of [ItunesSearch] the itunes searched and its timestamp to when it is searched
     */
    @Query("SELECT * FROM itunes_searches WHERE country = :itunesSearch_country AND term = :itunesSearch_term AND media = :itunesSearch_media")
    fun getItuneSearchesFrom(itunesSearch_country: String, itunesSearch_media: String, itunesSearch_term : String): LiveData<ItunesSearch>

    /**
     * Saves the itune search entity to room db
     * @param item [ItunesSearch] the search item to add and save
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSearch(item: ItunesSearch)

}
