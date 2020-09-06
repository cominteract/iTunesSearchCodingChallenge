package com.ainsigne.masterdetailitunes.di

import com.ainsigne.masterdetailitunes.MainActivity
import com.ainsigne.masterdetailitunes.api.ItunesSearchAPI
import dagger.Module
import dagger.Provides

/**
 * Provides the api and activity for context
 */
@Module
class ActivityModule(private val act: MainActivity) {
    /**
     * Provides the activity for context
     */
    @Provides
    fun providesActivity() : MainActivity = act

    /**
     * Provides the api access
     */
    @Provides
    fun providesItunesSearch() : ItunesSearchAPI = ItunesSearchAPI()

}