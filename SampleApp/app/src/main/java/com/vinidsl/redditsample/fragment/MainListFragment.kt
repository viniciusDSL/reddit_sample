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
import com.vinidsl.redditsample.network.RedditAPI
import com.vinidsl.redditsample.network.response.TopEntriesResponse
import com.vinidsl.redditsample.ui.RedditEntryAdapter
import com.vinidsl.redditsample.viewmode.MainListViewModel
import kotlinx.android.synthetic.main.main_list_fragment.*
import retrofit2.Call
import retrofit2.Response

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

        if (redditEntryAdapter.itemCount==0) {
            RedditAPI.REDDIT_SERVICE.getEntries().enqueue(object :
                retrofit2.Callback<TopEntriesResponse> {

                override fun onFailure(call: Call<TopEntriesResponse>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<TopEntriesResponse>,
                    response: Response<TopEntriesResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.data?.children?.let {
                            redditEntryAdapter.addRedditEntries(it)
                        }
                    }
                }

            })
        }
    }

}
