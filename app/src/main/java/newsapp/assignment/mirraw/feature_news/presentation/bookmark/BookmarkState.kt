package newsapp.assignment.mirraw.feature_news.presentation.bookmark

import newsapp.assignment.mirraw.feature_news.domain.models.Bookmark

data class BookmarkState(
    val bookmarks: List<Bookmark> = emptyList(),
    val loading: Boolean = true,
)