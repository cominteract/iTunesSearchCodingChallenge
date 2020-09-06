package com.ainsigne.masterdetailitunes

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.ainsigne.masterdetailitunes.data.ItunesItem
import com.ainsigne.masterdetailitunes.data.ItunesSearch
import com.ainsigne.masterdetailitunes.fake.FakeItunesItemsRepository
import com.ainsigne.masterdetailitunes.utils.*
import com.ainsigne.masterdetailitunes.viewmodels.ItunesItemsViewModel
import org.junit.Test
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ItunesTest {


    inline fun <reified T> lambdaMock(): T = mock(T::class.java)

    lateinit var repository: FakeItunesItemsRepository

    lateinit var viewmodel: ItunesItemsViewModel
    lateinit var detailId : String
    var tobeAdded : Int = 0

    fun observeItuneTracksChanges(lifecycle: Lifecycle, observer: (List<ItunesItem>) -> Unit) {
        viewmodel.ituneTracks.observe( { lifecycle } ) { items ->
            items.let(observer)
        }
    }

    fun observeItuneDetailChanges(lifecycle: Lifecycle, observer: (ItunesItem) -> Unit){
        viewmodel.ituneTrackFromId(detailId).observe( { lifecycle } ){
            it.let(observer)
        }
    }

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup(){
        detailId = "4"
        tobeAdded = 4
        repository = FakeItunesItemsRepository()
        viewmodel = ItunesItemsViewModel(repository)

    }

    /**
     * Test whether the itunes track is retrieved from an id
     */
    @Test
    fun testItunesDetails(){
        val observer = lambdaMock<(ItunesItem) -> Unit>()
        val lifecycle = LifecycleRegistry(mock(LifecycleOwner::class.java))
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
        observeItuneDetailChanges(lifecycle,observer)
        viewmodel.ituneTrackFromId(detailId).let {
            assert(detailId == it.value?.trackId.toString())
            verify(observer)
        }
    }
    /**
     * Test whether the itunes track list are updated after adding an item
     */
    @Test
    fun testItunesItems() {
        val observer = lambdaMock<(List<ItunesItem>) -> Unit>()
        val lifecycle = LifecycleRegistry(mock(LifecycleOwner::class.java))
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
        observeItuneTracksChanges(lifecycle, observer)
        var itunesSearch = ItunesSearch()
        itunesSearch.timestamp = Date().toStringFormatFull()
        itunesSearch.media = medias[tobeAdded]
        var max = countryCodes.size - 1
        var min = 0
        var rand : Int = Random().nextInt(max - min + 1) + min
        itunesSearch.country = countryCodes[rand]
        itunesSearch.term = randomTracks[tobeAdded]

        val itunesItem = ItunesItem(itunesSearch = itunesSearch)
        itunesItem.trackId = 5
        itunesItem.primaryGenreName = randomGenre[tobeAdded]
        itunesItem.trackName = randomTracks[tobeAdded]
        itunesItem.artworkUrl100 = artworkUrls[tobeAdded]
        itunesItem.currency = "USD"
        itunesItem.artworkUrl60 = itunesItem.artworkUrl100?.replace("100x100","60x60")
        itunesItem.artworkUrl150 = itunesItem.artworkUrl100?.replace("100x100","150x150")
        itunesItem.artworkUrl400 = itunesItem.artworkUrl100?.replace("100x100","600x600")
        itunesItem.longDescription = loren
        val start = 1.0
        val end = 150.0
        val random = Random().nextDouble()
        val price = start + random * (end - start)
        val trackPrice = Math.round(price * 100.0) / 100.0
        itunesItem.trackPrice = trackPrice

        repository.insertItunesItem(itunesItem)
        // verify that it is added and it is the same list that is being updated meaning the repository works!
        viewmodel.ituneTracks.value?.let {
            assert(it.size == 5)
            verify(observer).invoke(it)
        }
    }
}
