package com.vinidsl.redditsample.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.vinidsl.redditsample.databinding.MainListFragmentBinding
import com.vinidsl.redditsample.ui.adapter.RedditEntryAdapter
import com.vinidsl.redditsample.viewmodel.MainListViewModel
import kotlinx.android.synthetic.main.main_list_fragment.*

class MainListFragment : Fragment() {

    companion object {
        fun newInstance() = MainListFragment()
    }

    private lateinit var viewModel: MainListViewModel
    private lateinit var binding: MainListFragmentBinding

    private var redditEntryAdapter =
        RedditEntryAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MainListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //TODO check deprecation
        viewModel = ViewModelProviders.of(this).get(MainListViewModel::class.java)
        binding.viewModel = viewModel

        rv_entries.layoutManager = LinearLayoutManager(context)
        val dividerItemDecoration = DividerItemDecoration(
            context, DividerItemDecoration.VERTICAL
        )
        rv_entries.addItemDecoration(dividerItemDecoration)
        rv_entries.adapter = redditEntryAdapter

        viewModel.getRedditEntryLiveData().observe(viewLifecycleOwner, Observer {
            redditEntryAdapter.submitList(it)
            viewModel.enableRefresh()
        })
    }

}
