package com.ainsigne.masterdetailitunes.utils

import androidx.preference.PreferenceManager
import com.ainsigne.masterdetailitunes.MainActivity

/**
 * Config class for saving settings and updates
 */
class ItunesConfig {
    companion object{
        /**
         * context : [MainActivity] context used in saving preferences
         */
        lateinit var context: MainActivity

        /**
         * [String]  search key parameter for saving searched term
         */
        val searchKey = "searchKey"
        /**
         * [String]  id key parameter for saving id of itune track selected
         */
        val idKey = "idKey"
        /**
         * [String]  country key key parameter for saving searched country
         */
        val countryKey = "countryKey"
        /**
         * [String] media key parameter for saving searched media
         */
        val mediaKey = "mediaKey"
        /**
         * [String] timestamp key parameter for saving timestamp when searched
         */
        val timeStampKey = "timeStampKey"

        /**
         * saves the searched query
         * @param searched : [String] searched query
         */
        fun saveSearch(searched : String){
            var pref = PreferenceManager.getDefaultSharedPreferences(context)
            val editor = pref?.edit()
            editor?.putString(searchKey, searched)
            editor?.apply()
        }

        /**
         * returns the searched query
         * @return [String] searched query
         */
        fun getSearch() : String?{
            var pref = PreferenceManager.getDefaultSharedPreferences(context)
            return pref.getString(searchKey, "")
        }

        /**
         * saves the searched media
         * @param media [Int] searched media
         */
        fun saveMedia(media : Int){
            var pref = PreferenceManager.getDefaultSharedPreferences(context)
            val editor = pref?.edit()
            editor?.putInt(mediaKey, media)
            editor?.apply()
        }

        /**
         * returns the searched media
         * @return [Int] searched media
         */
        fun getMedia() : Int?{
            var pref = PreferenceManager.getDefaultSharedPreferences(context)
            return pref.getInt(mediaKey, -1)
        }

        /**
         * saves the searched country
         * @param country [Int] searched country
         */
        fun saveCountry(country : Int){
            var pref = PreferenceManager.getDefaultSharedPreferences(context)
            val editor = pref?.edit()
            editor?.putInt(countryKey, country)
            editor?.apply()
        }
        /**
         * returns the searched country
         * @return [Int] searched country
         */
        fun getCountry() : Int?{
            var pref = PreferenceManager.getDefaultSharedPreferences(context)
            return pref.getInt(countryKey, -1)
        }

        /**
         * saves the id of itunes track
         * @param id : [String] saved id of itunes track
         */
        fun saveId(id : String?) : Boolean?{
            var pref = PreferenceManager.getDefaultSharedPreferences(context)
            val editor = pref?.edit()
            editor?.putString(idKey, id)
            return editor?.commit()
        }

        /**
         * returns the id of itunes track
         * @return [String] searched id of itunes track
         */
        fun getId() : String?{
            var pref = PreferenceManager.getDefaultSharedPreferences(context)
            return pref.getString(idKey, null)
        }

        /**
         * saves the timestamp when searched
         * @param timeStamp : [String] the timestamp when searched
         */
        fun saveTimestamp(timeStamp : String?) : Boolean?{
            var pref = PreferenceManager.getDefaultSharedPreferences(context)
            val editor = pref?.edit()
            editor?.putString(timeStampKey, timeStamp)
            return editor?.commit()
        }


        /**
         * returns the timestamp when searched
         * @return [String] the timestamp when searched
         */
        fun getTimestamp() : String?{
            var pref = PreferenceManager.getDefaultSharedPreferences(context)
            return pref.getString(timeStampKey, null)
        }

    }

}