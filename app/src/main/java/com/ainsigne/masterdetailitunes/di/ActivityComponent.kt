package com.ainsigne.masterdetailitunes.di

import com.ainsigne.masterdetailitunes.MainActivity
import com.ainsigne.masterdetailitunes.ui.ItunesTrackDetailsFragment
import com.ainsigne.masterdetailitunes.ui.ItunesTrackListFragment
import dagger.Component
import javax.inject.Singleton

/**
 * handles injection to fragments and handling of modules needed
 */
@Singleton
@Component(modules = [ActivityModule::class,DatabaseModule::class])
interface ActivityComponent{
    /**
     * Accesses the modules
     */
    fun mainActivity(): MainActivity

    /**
     * Injects the module to [ItunesTrackListFragment]
     * @param itunesTrackListFragment [ItunesTrackListFragment] the fragment to inject the moduies
     */
    fun inject(itunesTrackListFragment: ItunesTrackListFragment)

    /**
     * Injects the module to [ItunesTrackDetailsFragment]
     * @param itunesTrackDetailsFragment [ItunesTrackDetailsFragment] the fragment to inject the moduies
     */
    fun inject(itunesTrackDetailsFragment: ItunesTrackDetailsFragment)

}