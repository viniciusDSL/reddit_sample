package com.vinidsl.redditsample.network.service

import com.vinidsl.redditsample.network.response.TopEntriesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RedditService {
    @GET("r/all/top/.json?t=all&limit=10")
    fun getEntries() : Call<TopEntriesResponse>

    @GET("r/all/top/.json?t=all&limit=10")
    fun getEntriesAfter(@Query("after") key: String) : Call<TopEntriesResponse>

    @GET("r/all/top/.json?t=all&limit=10")
    fun getEntriesBefore(@Query("before") key: String) : Call<TopEntriesResponse>
}