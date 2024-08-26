package newsapp.assignment.mirraw.feature_news.presentation.bookmark

sealed class BookmarkEvent{

    data class Fetch(val page: Int = 0): BookmarkEvent()

}