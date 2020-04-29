package com.vinidsl.redditsample.viewmodel

import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.vinidsl.redditsample.model.RedditEntry
import com.vinidsl.redditsample.repository.RedditRepository

class MainListViewModel : ViewModel(), Observable {

    var isRefreshing = false

    var canRefresh = false

    var repository = RedditRepository()

    private var redditEntries : LiveData<PagedList<RedditEntry>> = repository.instanceLivePage()

    fun getRedditEntryLiveData(): LiveData<PagedList<RedditEntry>> {
        return redditEntries
    }

    fun onRefresh() {
        isRefreshing = true
        redditEntries.value?.dataSource?.invalidate()
    }

    fun enableRefresh() {
        isRefreshing = false
        canRefresh = true
        notifyChange()
    }

    @delegate:Transient
    private val mCallBacks: PropertyChangeRegistry by lazy { PropertyChangeRegistry() }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        mCallBacks.remove(callback)
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        mCallBacks.add(callback)
    }

    private fun notifyChange() {
        mCallBacks.notifyChange(this, 0)
    }
}
