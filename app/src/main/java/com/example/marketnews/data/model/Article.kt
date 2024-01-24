package com.example.marketnews.data.model

import com.google.gson.annotations.SerializedName

data class Article(
    @SerializedName("title") val title: String,
    @SerializedName("author") val author: String,
    @SerializedName("url") val url: String,
    @SerializedName("urlToImage") val urlToImage: String,
    @SerializedName("publishedAt") val publishedAt: String,
    @SerializedName("description") val description: String,
    @SerializedName("content") val content: String,
)
