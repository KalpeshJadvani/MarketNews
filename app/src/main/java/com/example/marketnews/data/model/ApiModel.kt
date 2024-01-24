package com.example.marketnews.data.model

import com.google.gson.annotations.SerializedName

data class ApiModel(
    @SerializedName("totalResults") val totalResults: Int,
    @SerializedName("articles") val articles: List<Article>
)
