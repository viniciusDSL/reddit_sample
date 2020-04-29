package com.vinidsl.redditsample.datasource

import androidx.paging.PageKeyedDataSource
import com.vinidsl.redditsample.model.RedditEntry
import com.vinidsl.redditsample.network.RedditAPI
import com.vinidsl.redditsample.network.response.TopEntriesResponse
import retrofit2.Call
import retrofit2.Response

class RedditEntryDataSource : PageKeyedDataSource<String, RedditEntry?>(){
    override fun loadInitial(
        params: LoadInitialParams<String>,
        callback: LoadInitialCallback<String, RedditEntry?>
    ) {
        RedditAPI.REDDIT_SERVICE.getEntries().enqueue(object :
            retrofit2.Callback<TopEntriesResponse> {
            override fun onFailure(call: Call<TopEntriesResponse>, t: Throwable) {
                //TODO implement error callbacks
            }

            override fun onResponse(
                call: Call<TopEntriesResponse>,
                response: Response<TopEntriesResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.data?.children?.let {
                          callback.onResult(it, null, response.body()!!.data!!.after)
                    } // ?: TODO handle unexpected json response
                } else {
                    //TODO implement error callbacks
                }
            }
        })
    }

    override fun loadAfter(
        params: LoadParams<String>,
        callback: LoadCallback<String, RedditEntry?>
    ) {
        RedditAPI.REDDIT_SERVICE.getEntriesAfter(params.key).enqueue(object :
            retrofit2.Callback<TopEntriesResponse> {
            override fun onFailure(call: Call<TopEntriesResponse>, t: Throwable) {
                //TODO implement error callbacks
            }

            override fun onResponse(
                call: Call<TopEntriesResponse>,
                response: Response<TopEntriesResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.data?.children?.let {
                        callback.onResult(it,  response.body()!!.data!!.after)
                    } // ?: TODO handle unexpected json response
                } else {
                    //TODO implement error callbacks
                }
            }

        })
    }

    override fun loadBefore(
        params: LoadParams<String>,
        callback: LoadCallback<String, RedditEntry?>
    ) {
        // TODO I'm not sure if we need this implementation at least for now
    }
}