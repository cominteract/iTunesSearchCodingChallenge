package com.ainsigne.masterdetailitunes.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

/**
 * The Room database for this app
 */
@Database(entities = [ItunesItem::class, ItunesSearch::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    /**
     * the dao for room access to perform crud on [ItunesItem]
     * @return [ItunesItemDao]
     */
    abstract fun itunesItemDao() : ItunesItemDao

    /**
     * the dao for room access to perform crud on [ItunesSearch]
     * @return [ItunesSearchDao]
     */
    abstract fun itunesSearchDao() : ItunesSearchDao
}
