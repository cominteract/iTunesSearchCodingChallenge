package com.ainsigne.masterdetailitunes.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ainsigne.masterdetailitunes.data.*
import com.ainsigne.masterdetailitunes.utils.DATABASE_NAME
import com.ainsigne.masterdetailitunes.viewmodels.ItunesItemsViewModel
import com.ainsigne.masterdetailitunes.viewmodels.ItunesItemsViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Database module used for providing dependency regarding repository viewmodel and dao
 * @param appContext [Context] the context provided for initializing app db
 */
@Module
class DatabaseModule(appContext: Context) {

    /**
     * [AppDatabase] the database builder for room db
     */
    var appDatabase : AppDatabase

    /**
     * Initializes the room db using the DATABASE_NAME from constants
     */
    init {
        appDatabase = Room.databaseBuilder(appContext, AppDatabase::class.java, DATABASE_NAME)
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                }
            })
            .build()
    }

    /**
     * Provides [AppDatabase]
     */
    @Provides
    @Singleton
    fun providesRoom() : AppDatabase {
        return appDatabase
    }

    /**
     * Provides [ItunesItemDao]
     */
    @Provides
    @Singleton
    fun providesItunesItemDao() : ItunesItemDao {
        return appDatabase.itunesItemDao()
    }

    /**
     * Provides [ItunesSearchDao]
     */
    @Provides
    @Singleton
    fun providesItunesSearchDao() : ItunesSearchDao {
        return appDatabase.itunesSearchDao()
    }

    /**
     * Provides [ItunesItemsRepository]
     * @param dao [ItunesItemDao]
     * @param sdao [ItunesSearchDao]
     */
    @Provides
    @Singleton
    fun providesItunesItemRepository(dao: ItunesItemDao, sdao: ItunesSearchDao) : ItunesItemsRepository{
        val repo = ItunesItemsRepository(dao, sdao)
        return repo
    }

    /**
     * Provides [ItunesItemsViewModelFactory]
     * @param itunesItemsRepository [ItunesItemsRepository]
     */
    @Provides
    @Singleton
    fun provideItunesItemFactory(itunesItemsRepository: ItunesItemsRepository) : ItunesItemsViewModelFactory {
        return ItunesItemsViewModelFactory(itunesItemsRepository)
    }

    /**
     * Provides [ItunesItemsViewModel]
     * @param factory [ItunesItemsViewModelFactory]
     */
    @Provides
    @Singleton
    fun providesItunesItemViewModel(factory: ItunesItemsViewModelFactory) : ItunesItemsViewModel {
        val viewModel = factory.create(ItunesItemsViewModel::class.java)
        return  viewModel
    }

}