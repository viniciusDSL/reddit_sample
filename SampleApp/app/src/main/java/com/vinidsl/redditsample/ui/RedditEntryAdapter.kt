package com.vinidsl.redditsample.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.vinidsl.redditsample.R
import com.vinidsl.redditsample.databinding.RedditEntryItemBinding
import com.vinidsl.redditsample.model.RedditEntry

class RedditEntryAdapter : RecyclerView.Adapter<RedditEntryAdapter.EntryViewHolder>(){

    private val redditEntries = arrayListOf<RedditEntry>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntryViewHolder {
        val view = DataBindingUtil.inflate<RedditEntryItemBinding>(LayoutInflater.from(parent.context),
            R.layout.reddit_entry_item, parent, false)
        return EntryViewHolder(view)
    }

    override fun getItemCount(): Int = redditEntries.size

    override fun onBindViewHolder(holder: EntryViewHolder, position: Int) {
        val redditEntryItem = redditEntries[position]
        holder.redditEntryItemBinding.entry = redditEntryItem
    }

    class EntryViewHolder(val redditEntryItemBinding: RedditEntryItemBinding) : RecyclerView.ViewHolder(redditEntryItemBinding.root)

    fun addRedditEntries(items: List<RedditEntry>) {
        redditEntries.addAll(items)
        notifyDataSetChanged()
    }
}