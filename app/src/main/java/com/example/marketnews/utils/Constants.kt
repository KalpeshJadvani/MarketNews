package com.example.marketnews.utils


import com.example.marketnews.data.model.Article
import com.example.marketnews.domain.model.NewsModel

object Constants {
    val tagTile = "tagTitle"
    val tagTitleAuthor = "tagTitleAuthor"
    val tagArticlesList = "tagArticlesList"
    val tagCustomRow = "tagCustomRow"

    // it's for preview use
    val article = NewsModel(
        author = "Livemint",
        title = "Long Covid More Severe In Women Than Men, Suggests Study - Mint",
        description = "The study found that 91% of patients, who were followed up for five months on average, continued to experience Covid-19 symptoms. Breathlessness was the most common symptom of long Covid-19, followed by fatigue",
        urlToImage = "https://images.livemint.com/img/2022/04/21/600x338/long_covid_symptoms_1650540839356_1650540839488.jpg",
    )
}