package newsapp.assignment.mirraw.feature_news.presentation.technology

import newsapp.assignment.mirraw.feature_news.domain.models.Article

data class TechnologyState(
    val articles: List<Article> = emptyList(),
    val loading: Boolean = true,
    val nextPage: Int = 1
)