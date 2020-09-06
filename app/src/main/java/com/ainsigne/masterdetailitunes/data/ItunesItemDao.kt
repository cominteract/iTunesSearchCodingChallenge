package com.ainsigne.masterdetailitunes.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * The Data Access Object for the [ItunesItem] class.
 */
@Dao
interface ItunesItemDao {
    /**
     * Returns saved itune items to be displayed ordered by its trackName
     * @return [LiveData] of [List] of [ItunesItem] ordered by its trackName
     */
    @Query("SELECT * FROM itunes_items ORDER BY trackName")
    fun getItunesItems(): LiveData<List<ItunesItem>>

    /**
     * Returns saved itune item identified through its trackName
     * @param itunesName_ [String] the identifier to identify the [ItunesItem] to return
     * @return [LiveData] of [ItunesItem] identified through its trackName
     */
    @Query("SELECT * FROM itunes_items WHERE trackName = :itunesName_")
    fun getItunesItem(itunesName_: String): LiveData<ItunesItem>

    /**
     * Returns saved itune item identified through its trackId
     * @param itunesId_ [String] the identifier to identify the [ItunesItem] to return
     * @return [LiveData] of [ItunesItem] identified through its trackId
     */
    @Query("SELECT * FROM itunes_items WHERE trackId = :itunesId_")
    fun getItunesItemFromId(itunesId_: String): LiveData<ItunesItem>

    /**
     * Returns saved itune items to be displayed identified through its itunesSearch parameters
     * @param itunesSearch_country [String] the identifier for whiched searched country to filter with
     * @param itunesSearch_term [String] the identifier for whiched searched term to filter with
     * @param itunesSearch_media [String] the identifier for whiched searched media to filter with
     * @return [LiveData] of [List] of [ItunesItem] identified through its itunesSearch parameters
     */
    @Query("SELECT * FROM itunes_items WHERE itunesSearch_country = :itunesSearch_country AND itunesSearch_term = :itunesSearch_term AND itunesSearch_media = :itunesSearch_media")
    fun getItunesItemFromSearch(itunesSearch_country: String, itunesSearch_term : String, itunesSearch_media: String): LiveData<List<ItunesItem>>

    /**
     * Saves the ituneitems entity list to room db
     * @param items [List] of [ItunesItem] the items to add and save
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<ItunesItem>)

}
