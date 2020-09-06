package com.ainsigne.masterdetailitunes.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ainsigne.masterdetailitunes.data.ItunesItem
import com.ainsigne.masterdetailitunes.databinding.ItemListContentBinding
import com.ainsigne.masterdetailitunes.utils.ItunesConfig

/**
 * interface used for listening to when the view is clicked for navigation or display of detail view
 *
 */
interface MasterDetailTabletInterface{
    /**
     * When a track is selected handle navigation or replace of the detail view for tablet
     * @param trackId [String] the identifier to determine which itune track to display the details
     */
    fun onSelectTrack(trackId : String)
}

/**
 * Adapter for the [RecyclerView] in [ItunesItem].
 * responsible for displaying all the [ItunesItem]
 */
class ItunesTrackAdapter : ListAdapter<ItunesItem, ItunesTrackAdapter.ViewHolder>(ItunesDiffCallback()) {

    /**
     * interface used for listening to when the view is clicked for navigation or display of detail view
     *
     */
    var masterDetailTabletInterface : MasterDetailTabletInterface? = null

    /**
     * Binds on click listener to the view container
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itunesItem = getItem(position)

        holder.apply {

            itunesItem.trackId?.let{
                bind(createOnClickListener(it), itunesItem)
            }
            itemView.tag = itunesItem
        }
    }


    /**
     * Default view holder from binding
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemListContentBinding.inflate(
                LayoutInflater.from(parent.context), parent, false))
    }

    /**
     * Creates an onclicklistener for the view container
     * @param itunesItemId [Long] the identifier to be used to proceed to its detail view
     * @return [View.OnClickListener] the listener for accepting on click on the view container to proceed to detail view
     */
    private fun createOnClickListener(itunesItemId: Long): View.OnClickListener {

        return View.OnClickListener {
            ItunesConfig.saveId(itunesItemId.toString())
            masterDetailTabletInterface?.onSelectTrack(trackId = itunesItemId.toString())
        }
    }

    /**
     * View holder to bind click listener and itunes item
     */
    class ViewHolder(
        private val binding: ItemListContentBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        /**
         * Binds the onclicklistener and itunes item for displaying ui
         * @param listener [View.OnClickListener] the on click listener for the view
         * @param item [ItunesItem] the itunes item for ui to be displayed
         */
        fun bind(listener: View.OnClickListener, item: ItunesItem) {
            binding.apply {
                clickListener = listener
                itunesItem = item
                executePendingBindings()
            }
        }
    }
}

/**
 * For comparing same items
 */
private class ItunesDiffCallback : DiffUtil.ItemCallback<ItunesItem>() {

    /**
     * Check whether the items are the same through its id
     */
    override fun areItemsTheSame(oldItem: ItunesItem, newItem: ItunesItem): Boolean {
        return oldItem.trackId == newItem.trackId
    }

    /**
     * Check whether the items are the same item
     */
    override fun areContentsTheSame(oldItem: ItunesItem, newItem: ItunesItem): Boolean {
        return oldItem == newItem
    }
}