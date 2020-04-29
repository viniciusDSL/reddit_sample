package com.vinidsl.redditsample.network

import com.google.gson.GsonBuilder
import com.vinidsl.redditsample.model.RedditEntry
import com.vinidsl.redditsample.network.deserializer.RedditEntryDeserializer
import com.vinidsl.redditsample.network.response.TopEntriesResponse
import com.vinidsl.redditsample.network.service.RedditService
import junit.framework.Assert
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class DeserializerTest {
    lateinit var callResult: Response<TopEntriesResponse>
    lateinit var mockWebServer : MockWebServer


    @Before
    @Throws(IOException::class)
    fun setup() {
        mockWebServer = MockWebServer()

        val gsonBuilder = GsonBuilder()
        gsonBuilder.registerTypeAdapter(RedditEntry::class.java, RedditEntryDeserializer())
        val gson = gsonBuilder.create()

        val retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url("").toString())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        val inputStream = this.javaClass.classLoader?.getResourceAsStream("response.txt");
        val responseValue = inputStream?.bufferedReader()?.use {
            it.readText()
        }

        mockWebServer.enqueue(MockResponse().setBody(responseValue!!))
        val service = retrofit.create(RedditService::class.java)
        callResult  = service.getEntries().execute()
    }

    @Test
    fun testResponse() {
        Assert.assertNotNull(callResult.body()!!.data)
        Assert.assertEquals(10, callResult.body()!!.data!!.children!!.size)
    }

    @Test
    fun testDeserialization() {
        val entries = callResult.body()!!.data!!.children
        Assert.assertEquals("Guardians of the Front Page", entries!![0].title)
        Assert.assertEquals("t2_tu7hd", entries[0].author)
        Assert.assertEquals(4988, entries[0].commentsCount)
        Assert.assertEquals("t3_5gn8ru", entries[0].name)
        Assert.assertEquals("https://b.thumbs.redditmedia.com/ZF37c_fUuPPTootrtYGvCy5vpbcIPT3Feo3uGNNchfE.jpg", entries[0].thumbnail)
    }

    @After
    fun finish() {
        mockWebServer.shutdown()
    }
}