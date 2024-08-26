package newsapp.assignment.mirraw.feature_news.presentation.main

import newsapp.assignment.mirraw.feature_news.domain.models.Article

data class MainState(
    val selectedIndex: Int = 0,
    val technologyArticles: List<Article> = emptyList(),
    val technologyPage: Int = 0,
    val loadingTechnologyArticles: Boolean = false,
    val entertainmentArticles: List<Article> = emptyList(),
    val entertainmentPage: Int = 0,
    val loadingEntertainmentArticles: Boolean = false,
    val sportsArticles: List<Article> = emptyList(),
    val sportsPage: Int = 0,
    val loadingSportsArticles: Boolean = false
)