package newsapp.assignment.mirraw.feature_news.domain.use_cases

data class NewsUseCases(
    val getTopEntertainmentArticles: GetTopEntertainmentArticles,
    val getTopTechnologyArticles: GetTopTechnologyArticles,
    val getTopSportsArticles: GetTopSportsArticles,
    val searchNewsArticles: SearchNewsArticles,
    val getAllBookmarks: GetAllBookmarks,
    val bookmarkExists: BookmarkExists,
    val bookmarkArticle: BookmarkArticle,
    val removeBookmark: RemoveBookmark
) {
}