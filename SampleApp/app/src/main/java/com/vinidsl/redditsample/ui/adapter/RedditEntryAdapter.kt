package com.vinidsl.redditsample.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.vinidsl.redditsample.R
import com.vinidsl.redditsample.databinding.RedditEntryItemBinding
import com.vinidsl.redditsample.model.RedditEntry

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
    }

    class EntryViewHolder(val redditEntryItemBinding: RedditEntryItemBinding) : RecyclerView.ViewHolder(redditEntryItemBinding.root)


}