package newsapp.assignment.mirraw.feature_news.presentation.entertainment

import newsapp.assignment.mirraw.feature_news.domain.models.Article

data class EntertainmentState(
    val articles: List<Article> = emptyList(),
    val loading: Boolean = true,
    val nextPage: Int = 1,
)