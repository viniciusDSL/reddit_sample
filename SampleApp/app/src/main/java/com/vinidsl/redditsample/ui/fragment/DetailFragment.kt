package com.vinidsl.redditsample.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vinidsl.redditsample.R
import com.vinidsl.redditsample.databinding.RedditEntryItemBinding
import com.vinidsl.redditsample.model.RedditEntry
import com.vinidsl.redditsample.storage.SharedPreferencesManager

class DetailFragment : Fragment() {

    companion object {
        const val REDDIT_ENTRY_PARAM = "reddit_entry"
        fun newInstance() = DetailFragment()
    }

    private lateinit var binding: RedditEntryItemBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RedditEntryItemBinding.inflate(inflater, container, false)
        binding.root.findViewById<View>(R.id.bt_dismiss).visibility = View.INVISIBLE
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arguments?.getParcelable<RedditEntry>(REDDIT_ENTRY_PARAM)?.let {entry->
            binding.entry = entry
            context?.let {verifiedContext->
                SharedPreferencesManager.updateRedditEntryStatus(verifiedContext, entry.name)
            }
        }
    }

}
