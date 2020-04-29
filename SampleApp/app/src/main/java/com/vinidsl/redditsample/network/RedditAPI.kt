package com.vinidsl.redditsample.network

import com.google.gson.GsonBuilder
import com.vinidsl.redditsample.model.RedditEntry
import com.vinidsl.redditsample.network.deserializer.RedditEntryDeserializer
import com.vinidsl.redditsample.network.service.RedditService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RedditAPI {
    private val baseUrl = "https://www.reddit.com"

    val REDDIT_SERVICE : RedditService

    init {

        val gsonBuilder = GsonBuilder()
        gsonBuilder.registerTypeAdapter(RedditEntry::class.java, RedditEntryDeserializer())
        val gson = gsonBuilder.create()

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        REDDIT_SERVICE = retrofit.create(RedditService::class.java)
    }
}