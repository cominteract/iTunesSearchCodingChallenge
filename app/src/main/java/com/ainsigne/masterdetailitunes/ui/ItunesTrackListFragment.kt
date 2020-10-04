package com.ainsigne.masterdetailitunes.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.widget.SearchView
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.ainsigne.masterdetailitunes.MainActivity
import com.ainsigne.masterdetailitunes.R
import com.ainsigne.masterdetailitunes.adapters.ItunesTrackAdapter
import com.ainsigne.masterdetailitunes.adapters.MasterDetailTabletInterface
import com.ainsigne.masterdetailitunes.api.ItunesSearchAPI
import com.ainsigne.masterdetailitunes.data.ItunesItem
import com.ainsigne.masterdetailitunes.data.ItunesItemsRepository
import com.ainsigne.masterdetailitunes.data.ItunesSearch
import com.ainsigne.masterdetailitunes.databinding.FragmentItunesTrackDetailsBinding
import com.ainsigne.masterdetailitunes.databinding.FragmentItunesTrackListBinding
import com.ainsigne.masterdetailitunes.utils.*
import com.ainsigne.masterdetailitunes.viewmodels.ItunesItemsViewModel
import kotlinx.android.synthetic.main.fragment_itunes_track_list.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 * Use the [ItunesTrackListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ItunesTrackListFragment : Fragment(), MasterDetailTabletInterface {

    /**
     * itunesViewModel [ItunesItemsViewModel] The ViewModel for [ItunesTrackListFragment].
     */
    @Inject
    lateinit var itunesItemsViewModel: ItunesItemsViewModel

    /**
     * Repository module for handling data operations for the [ItunesItem].
     */
    @Inject
    lateinit var itunesItemRepository: ItunesItemsRepository

    /**
     * [ItunesSearchAPI] its retrofit service builder for making http calls to itunes search api
     */
    @Inject
    lateinit var api: ItunesSearchAPI

    /**
     * [Boolean] whether it is a tablet or not
     */
    var twoPane = false

    /**
     * Adapter for the [RecyclerView] in [ItunesItem].
     * responsible for displaying all the [ItunesItem]
     */
    lateinit var adapter : ItunesTrackAdapter

    /**
     * src to remove the focus
     */
    var srcText : SearchView? = null

    /**
     * When already searched or navigated to its details. Handles navigation from last updated or populating of the
     * parameters last used
     * @param binding [FragmentItunesTrackListBinding] the binding to access the view children
     */
    private fun updateFromLast(binding: FragmentItunesTrackListBinding){

        var itunesSearch = ItunesSearch()
        ItunesConfig.getCountry()?.let {cc ->
            if(cc >= 0){
                itunesSearch.country = countryCodes.get(cc)
                binding.sprCountries.setSelection(cc)
                ItunesConfig.getMedia()?.let {
                    if(it >= 0){
                        itunesSearch.media = mediaCodes.get(it)
                        binding.sprType.setSelection(it)
                        ItunesConfig.getSearch()?.let {search ->
                            itunesSearch.term = search
                            binding.srcTerm.isIconified = false
                            binding.srcTerm.setQuery(search, false)
                            subscribeUI(itunesSearch)
                        }
                    }
                }
            }
        }
        ItunesConfig.getId()?.let {
            binding.srcTerm.clearFocus()
            onSelectTrack(it)
        }
    }

    /**
     * Subscribes to the ui using an observer pattern that when itunes track list searched exist
     * updates the adapter else it will start the get request to the api
     * Observer pattern works pretty well on the android ecosystem making use of the live data and view model
     * capabilities of ktx in this case it notifies whether the itunes track list is already populated
     * and ready to display as a list
     * @param binding [FragmentItunesTrackDetailsBinding] the binding to access the view children
     */
    private fun subscribeUI(itunesSearch: ItunesSearch){
        itunesItemsViewModel.ituneTracks.observe(viewLifecycleOwner){
            var items = it.filter { item ->
               item.itunesSearch.term.contains(itunesSearch.term)
                       && item.itunesSearch.media == itunesSearch.media
                       && item.itunesSearch.country == itunesSearch.country
            }
            if(items.isNullOrEmpty()){
                startAPI(itunesSearch)
            }
            adapter.submitList(items)
        }

        itunesItemsViewModel.itunesSearches(itunesSearch).observe(viewLifecycleOwner){


        }
    }

    /**
     * When opening the page remove search view focus
     */
    override fun onResume() {
        super.onResume()
        srcText?.clearFocus()
    }

    /**
     * Starts the api using the itune search parameters
     * @param itunesSearch [ItunesSearch] the search parameters to be used as parameters for the url used
     * in the [GET] request
     */
    private fun startAPI(itunesSearch: ItunesSearch){
        CoroutineScope(Dispatchers.IO).launch {
            Log.d(" Searching "," Searching for ${itunesSearch.term} " +
                    "${itunesSearch.media} ${itunesSearch.country}")
            val response = api.webservice.searchAPI(itunesSearch.term, itunesSearch.media, itunesSearch.country)
            if(response.isSuccessful){

                response.body()?.results?.let {
                    for(item in it){
                        val r = Random()
                        val num: Int = r.nextInt(20 - 1) + 1
                        item.trackBGColor = num
                        item.artworkUrl150 = item.artworkUrl100?.replace("100x100","150x150")
                        item.artworkUrl400 = item.artworkUrl100?.replace("100x100","600x600")
                        item.itunesSearch = itunesSearch
                    }
                    itunesItemRepository.insertAll(it)
                    itunesItemRepository.insertItunesSearch(itunesSearch)
                    Log.d(" Searching success"," Searching success ${it.size}")
                }
            }else{
                Log.d(" Searching error "," Searching error ${response.raw().request().url()}")
            }
        }
    }


    /**
     * Binds the data and subscribes ui
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentItunesTrackListBinding.inflate(inflater, container, false)
        context ?: return binding.root
        (context as MainActivity).activityComponent.inject(this)
        adapter = ItunesTrackAdapter()
        adapter.masterDetailTabletInterface = this
        if (binding.root.findViewById<NestedScrollView>(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            twoPane = true
        }

        var countriesAdapter = ArrayAdapter<String>(requireContext(), R.layout.dropdown_menu_item, countries)
        var mediasAdapter = ArrayAdapter<String>(requireContext(), R.layout.dropdown_menu_item, medias)
        binding.itemList.adapter = adapter
        binding.sprCountries.adapter = countriesAdapter
        binding.sprType.adapter = mediasAdapter
        updateFromLast(binding)
        srcText = binding.srcTerm
        binding.srcTerm.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
           override fun onQueryTextSubmit(query: String?): Boolean {
               submitQuery(query,binding)
               return true
           }

           override fun onQueryTextChange(newText: String?): Boolean {
             return true
           }
       })

        setHasOptionsMenu(true)
        return binding.root
    }

    /**
     * When search term is submitted, Create the parameters to be used in a [GET] request or
     * retrieve the already saved data from the repo
     * @param query [String] the query searched
     * @param binding [FragmentItunesTrackDetailsBinding] the binding to access the view children
     */
    private fun submitQuery(query : String?, binding: FragmentItunesTrackListBinding){
        var itunesSearch = ItunesSearch()
        val timestamp =  Date().toStringFormatFull()
        itunesSearch.timestamp = timestamp
        ItunesConfig.saveTimestamp(timestamp)
        itunesSearch.country = countryCodes[spr_countries.selectedItemPosition]
        query?.let {
            itunesSearch.term = it
            ItunesConfig.saveSearch(it)
        }
        itunesSearch.media = mediaCodes[spr_type.selectedItemPosition]
        ItunesConfig.saveCountry(spr_countries.selectedItemPosition)
        ItunesConfig.saveMedia(spr_type.selectedItemPosition)
        binding.srcTerm.clearFocus()
        subscribeUI(itunesSearch)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment
         *
         * @return A new instance of fragment ItunesTrackListFragment.
         */
        @JvmStatic
        fun newInstance() =
            ItunesTrackListFragment()
    }

    /**
     * When a track is selected handle navigation or replace of the detail view for tablet
     * @param trackId [String] the identifier to determine which itune track to display the details
     */
    override fun onSelectTrack(trackId : String) {
        val timestamp =  Date().toStringFormatFull()
        ItunesConfig.saveTimestamp(timestamp)
        if(twoPane){
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.item_detail_container, ItunesTrackDetailsFragment.newInstance(trackId))
                ?.commit()
        }else{
            val direction = ItunesTrackListFragmentDirections.actionItemsFragmentToItunesDetailFragment(trackId = trackId)
            this@ItunesTrackListFragment.findNavController().navigate(direction)
        }
    }
}