package com.ainsigne.masterdetailitunes.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ainsigne.masterdetailitunes.interfaces.ItemRepository

/**
 * Factory for creating a [ItunesItemsViewModel] with a constructor that takes a [ItunesItemsRepository].
 */
class ItunesItemsViewModelFactory(var repo: ItemRepository
) : ViewModelProvider.NewInstanceFactory() {

    /**
     * Factory builder for view model
     */
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = ItunesItemsViewModel(repo) as T
}
