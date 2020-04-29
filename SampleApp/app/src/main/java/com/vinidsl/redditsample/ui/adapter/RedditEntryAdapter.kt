package com.vinidsl.redditsample.ui.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.vinidsl.redditsample.R
import com.vinidsl.redditsample.databinding.RedditEntryItemBinding
import com.vinidsl.redditsample.model.RedditEntry
import com.vinidsl.redditsample.ui.fragment.DetailFragment

class RedditEntryAdapter : PagedListAdapter<RedditEntry, RedditEntryAdapter.EntryViewHolder>(DIFF_CALLBACK){

    companion object{
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<RedditEntry>() {
            override fun areItemsTheSame(oldItem: RedditEntry, newItem: RedditEntry): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: RedditEntry, newItem: RedditEntry): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntryViewHolder {
        val view = DataBindingUtil.inflate<RedditEntryItemBinding>(LayoutInflater.from(parent.context),
            R.layout.reddit_entry_item, parent, false)
        return EntryViewHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: EntryViewHolder, position: Int) {
        val redditEntryItem = getItem(position)
        holder.redditEntryItemBinding.entry = redditEntryItem
        holder.itemView.setOnClickListener {
            val bundle = bundleOf(DetailFragment.REDDIT_ENTRY_PARAM to redditEntryItem)
            it.findNavController().navigate(R.id.action_mainListFragment_to_detailFragment, bundle)
        }
        holder.redditEntryItemBinding.btDismiss.setOnClickListener {
            //TODO this is just showing a remove animation, when you scroll you can see the item again
            // I think I should be research a little bit more to how to delete an item from PagedListAdapter
            // and PageKeyedDataSource
            notifyItemRemoved(position)
        }
        holder.redditEntryItemBinding.ivThumbnail.setOnClickListener {
            // validate if the item has a thumbnail and a url
            if (!redditEntryItem!!.thumbnail.isNullOrEmpty() && redditEntryItem.thumbnail!!.startsWith("https")
                && !redditEntryItem.url.isNullOrEmpty()) {
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(redditEntryItem.url)
                holder.itemView.context.startActivity(i)
            }
        }
    }

    class EntryViewHolder(val redditEntryItemBinding: RedditEntryItemBinding) : RecyclerView.ViewHolder(redditEntryItemBinding.root)


}