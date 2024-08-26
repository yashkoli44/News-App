package newsapp.assignment.mirraw.feature_news.presentation.article_details

import newsapp.assignment.mirraw.feature_news.domain.models.Bookmark

sealed class ArticleDetailsEvent {

    data class CheckBookmark(val url: String): ArticleDetailsEvent()

    data class ToggleBookmark(val bookmark: Bookmark): ArticleDetailsEvent()

}