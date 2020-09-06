package com.ainsigne.masterdetailitunes.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

/**
 * Type converters to allow Room to reference complex data types.
 */
class Converters {
    /**
     * TODO : unused
     */
    @TypeConverter fun calendarToDatestamp(calendar: Calendar): Long = calendar.timeInMillis

    /**
     * TODO : unused
     */
    @TypeConverter fun datestampToCalendar(value: Long): Calendar =
            Calendar.getInstance().apply { timeInMillis = value }


    /**
     * TODO : unused
     */
    @TypeConverter fun arrayListHashmapToString(value: ArrayList<HashMap<String,Any>>) = Gson().toJson(value)

    /**
     * TODO : unused
     */
    @TypeConverter fun stringToArrayListHashmap(value: String) : ArrayList<HashMap<String,Any>>{
        val itunesType = object : TypeToken<ArrayList<HashMap<String,Any>>>() {}.type
        return Gson().fromJson(value,itunesType)
    }
    /**
     * TODO : unused
     */
    @TypeConverter fun hashmapToString(value: HashMap<String,Any>) = Gson().toJson(value)

    /**
     * TODO : unused
     */
    @TypeConverter fun stringTohashmap(value: String) : HashMap<String,Any>{
        val itunesType = object : TypeToken<HashMap<String,Any>>() {}.type
        return Gson().fromJson(value, itunesType)
    }

    /**
     * TODO : unused
     */
    @TypeConverter fun anyToString(any : Any) = Gson().toJson(any)
    /**
     * TODO : unused
     */
    @TypeConverter fun stringToAny(value: String) : Any{
        val itunesType = object : TypeToken<Any>() {}.type
        return Gson().fromJson(value, itunesType)
    }

}