package com.vinidsl.redditsample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.vinidsl.redditsample.model.RedditEntry
import com.vinidsl.redditsample.repository.RedditRepository

class MainListViewModel : ViewModel() {

    var repository = RedditRepository()

    private var redditEntries : LiveData<PagedList<RedditEntry>> = repository.instanceLivePage()

    fun getRedditEntryLiveData(): LiveData<PagedList<RedditEntry>> {
        return redditEntries
    }

    fun resetLiveData() {
        redditEntries = repository.instanceLivePage()
    }
}
