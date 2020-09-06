package com.ainsigne.masterdetailitunes.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * ItunesSearch class represents the query made with query, media and country parameter
 */
@Entity(tableName = "itunes_searches")
data class ItunesSearch(

    /**
     * timestamp [String] when it is searched
     */
    @PrimaryKey() @ColumnInfo(name = "timestamp")
    var timestamp : String = "21345",

    /**
     * term [String] the term to be searched
     */
    var term : String = "avengers",



    /**
     * media [String] the media type to be searched
     */
    var media : String = "movie",

    /**
     * TODO : unused
     */
    var entity : String? = null,

    /**
     * country [String] the country criteria to b searched
     */
    var country : String = "us")  {

}