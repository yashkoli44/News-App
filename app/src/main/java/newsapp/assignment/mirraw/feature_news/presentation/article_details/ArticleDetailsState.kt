package newsapp.assignment.mirraw.feature_news.presentation.article_details

data class ArticleDetailsState(
    val isBookmarked: Boolean = false,
    val bookmarkInProgress: Boolean = false,
)