package newsapp.assignment.mirraw.feature_news.presentation.sports

import newsapp.assignment.mirraw.feature_news.domain.models.Article

data class SportsState(
    val articles: List<Article> = emptyList(),
    val loading: Boolean = true,
    val nextPage: Int = 1
)