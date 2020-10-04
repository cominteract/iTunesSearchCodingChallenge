package com.ainsigne.masterdetailitunes.data

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * ItunesItem class for representing the itunes track properties
 */

@Entity(tableName = "itunes_items")
data class ItunesItem (
    /**
     *  trackId [Long] the identifier used for the itunes track
     **/
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "trackId")
    var trackId : Long? = null,

    /**
     * country [String] to identify which country the itunes track originated
     */
    var country: String? = null,

    /**
    * trackName [String] the itunes track name
    */

    var trackName: String? = null,

    /**
     * previewUrl [String] the url for preview audio or video
     */
    var previewUrl: String? = null,

    /**
     * currency [String] the currency used for the track
     */
    var currency: String? = null,

    /**
     * artworkUrl60 [String] the icon url for the itunes track
     */
    var artworkUrl60: String? = null,

    /**
     * artworkUrl100 [String] the icon url for the itunes track
     */
    var artworkUrl100: String? = null,

    /**
     * artworkUrl150 [String] the icon url for the itunes track
     */
    var artworkUrl150: String? = null,
    /**
     * artworkUrl400 [String] the icon url for the itunes track
     */
    var artworkUrl400: String? = null,

    /**
     * trackPrice [Double] the price of the itunes track
     */
    var trackPrice: Double = 0.0,
    /**
     * trackRentalPrice [Double] the price of the itunes rental track
    */
    var trackRentalPrice: Double = 0.0,
    /**
     * trackHdPrice [Double] the price of the itunes hd track
     */
    var trackHdPrice: Double = 0.0,
    /**
     * trackBGColor [Int] the bg color of the track randomly assigned
     */
    var trackBGColor: Int = 0,
    /**
     * trackHdRentalPrice [Double] the price of the itunes hd rental track
    */
    var trackHdRentalPrice: Double = 0.0,

    /**
     * longDescription [String] the long description for the itunes track
     */
    var longDescription: String? = null,

    /**
     * longDescription [String] the content advisory rating for the itunes track
     */
    var contentAdvisoryRating: String? = null,

    /**
     * releaseDate [String] the release date for the itunes track
    */
    var releaseDate : String? = null,
    /**
     * artistName [String] the artist name for the itunes track
    */
    var artistName : String? = null,

    /**
     * primaryGenreName [String] the genre name for the itunes track
     */
    var primaryGenreName: String? = null,
    /**
     * itunesSearch [ItunesSearch] the itune search parameters to identify what searched access the itunes track
     */
    @field:Embedded(prefix = "itunesSearch_")
    var itunesSearch: ItunesSearch)