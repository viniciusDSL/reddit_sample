package com.vinidsl.redditsample.fragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager

import com.vinidsl.redditsample.R
import com.vinidsl.redditsample.model.RedditEntry
import com.vinidsl.redditsample.ui.RedditEntryAdapter
import com.vinidsl.redditsample.viewmode.MainListViewModel
import kotlinx.android.synthetic.main.main_list_fragment.*

class MainListFragment : Fragment() {

    companion object {
        fun newInstance() = MainListFragment()
    }

    private lateinit var viewModel: MainListViewModel
    private var redditEntryAdapter = RedditEntryAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //TODO check deprecation
        viewModel = ViewModelProviders.of(this).get(MainListViewModel::class.java)
        // TODO: Use the ViewModel
        rv_entries.layoutManager = LinearLayoutManager(context)
        val dividerItemDecoration = DividerItemDecoration(
            context, DividerItemDecoration.VERTICAL
        )
        rv_entries.addItemDecoration(dividerItemDecoration)
        rv_entries.adapter = redditEntryAdapter
        // TODO remove
        mockRedditEntries()
    }

    fun mockRedditEntries() {
        val mockedEntries = arrayListOf<RedditEntry>()

        for (c in 0..10) {
            mockedEntries.add(
                RedditEntry().apply {
                    title = "title $c"
                    commentsCount = c*100
                    author = "Vini"
                    url = "https://external-preview.redd.it/cjRxFqjsk-jWzToptU9yGfXgfTOSjuieSXqr87PWBuA.jpg?auto=webp&amp;s=8d30cc191ba001b063f7045a5d8a24b66dc6c386"
                    created = 1478680045.0
                    thumbnail = "https://b.thumbs.redditmedia.com/0Bz7ivQoXUIjSSTm-5WOtDkXrmGht62Z9Mbq-WQkmMA.jpg"
                }
            )
        }
        redditEntryAdapter.addRedditEntries(mockedEntries)
    }

}
