package com.ainsigne.masterdetailitunes.ui

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import com.ainsigne.masterdetailitunes.MainActivity
import com.ainsigne.masterdetailitunes.data.ItunesItem
import com.ainsigne.masterdetailitunes.data.ItunesItemsRepository
import com.ainsigne.masterdetailitunes.databinding.FragmentItunesTrackDetailsBinding
import com.ainsigne.masterdetailitunes.viewmodels.ItunesItemsViewModel
import javax.inject.Inject

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val TRACK_ID = "TRACK_ID"
/**
 * A simple [Fragment] subclass.
 * Use the [ItunesTrackDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ItunesTrackDetailsFragment : Fragment() {
    private val args: ItunesTrackDetailsFragmentArgs by navArgs()

    /**
     * itunesViewModel [ItunesItemsViewModel] The ViewModel for [ItunesTrackDetailsFragment].
     */
    @Inject
    lateinit var itunesItemsViewModel: ItunesItemsViewModel

    /**
     * Repository module for handling data operations for the [ItunesItem].
     */
    @Inject
    lateinit var itunesItemRepository: ItunesItemsRepository


    /**
     * Binds the data and subscribes ui
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentItunesTrackDetailsBinding.inflate(inflater, container, false)
        context ?: return binding.root
        (context as MainActivity).activityComponent.inject(this)
        setHasOptionsMenu(true)
        subscribeUi(binding)
        return binding.root
    }

    /**
     * Subscribes to the ui using an observer pattern that when an itunes track exist with the id it will start
     * the video/audio from its preview url and bind the data attached to the layout
     * Observer pattern works pretty well on the android ecosystem making use of the live data and view model
     * capabilities of ktx in this case it notifies whether the itunes track is already populated
     * and ready to display on the video
     * @param binding [FragmentItunesTrackDetailsBinding] the binding to access the view children
     */
    private fun subscribeUi(binding: FragmentItunesTrackDetailsBinding){
        var trackId = args.trackId
        arguments?.getString(TRACK_ID)?.let {
            trackId = it
        }
        trackId?.let {id ->
            itunesItemsViewModel.ituneTrackFromId(id).observe(viewLifecycleOwner){
                binding.itunesItem = it
                binding.detailToolbar.title = it.trackName
                binding.previewView.setVideoURI(Uri.parse(it.previewUrl))
                binding.previewView.start()
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment .
         * @return A new instance of fragment ItunesTrackDetailsFragment.
         */

        @JvmStatic
        fun newInstance(trackId : String) =
            ItunesTrackDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(TRACK_ID, trackId)
                }
            }
    }
}