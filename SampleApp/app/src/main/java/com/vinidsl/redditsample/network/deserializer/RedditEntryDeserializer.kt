package com.vinidsl.redditsample.network.deserializer

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.vinidsl.redditsample.model.RedditEntry
import java.lang.reflect.Type

class RedditEntryDeserializer : JsonDeserializer<RedditEntry> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): RedditEntry {

        val result = RedditEntry()

        json?.asJsonObject?.getAsJsonObject("data")?.let{
            result.apply {
                title = it.get("title").asString
                author = it.get("author_fullname").asString
                thumbnail = it.get("thumbnail").asString
                commentsCount = it.get("num_comments").asInt
                created = it.get("created_utc").asDouble
                url = it.get("url").asString
                name = it.get("name").asString
            }
        }
        return result
    }
}