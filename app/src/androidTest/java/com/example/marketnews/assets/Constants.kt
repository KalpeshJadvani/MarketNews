package com.example.marketnews.assets


import com.example.marketnews.data.model.ApiModel
import com.example.marketnews.data.model.Article



object Constants {
    // it's for preview use
    val article = Article(
        author="Livemint",
        title= "Long Covid More Severe In Women Than Men, Suggests Study - Mint",
        description= "The study found that 91% of patients, who were followed up for five months on average, continued to experience Covid-19 symptoms. Breathlessness was the most common symptom of long Covid-19, followed by fatigue",
        url= "https://www.livemint.com/news/india/long-covid-more-severe-in-women-than-men-suggests-study-11650538683775.html",
        urlToImage= "https://images.livemint.com/img/2022/04/21/600x338/long_covid_symptoms_1650540839356_1650540839488.jpg",
        publishedAt= "2022-04-21T11:37:19Z",
        content= "Post-coronavirus complications, also called long Covid syndrome, induce more symptoms in women than men, a new study has foun"
    )

    val apiModel = ApiModel(
        totalResults=4,
        listOf(article, article, article, article)
    )
}