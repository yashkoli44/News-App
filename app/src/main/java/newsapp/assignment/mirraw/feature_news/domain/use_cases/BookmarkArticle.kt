package newsapp.assignment.mirraw.feature_news.domain.use_cases

import newsapp.assignment.mirraw.feature_news.domain.models.Article
import newsapp.assignment.mirraw.feature_news.domain.models.Bookmark
import newsapp.assignment.mirraw.feature_news.domain.repository.NewsRepository

class BookmarkArticle(
    val newsRepository: NewsRepository
) {

    suspend operator fun invoke(bookmark: Bookmark) {
        return newsRepository.bookmarkArticle(bookmark)
    }

}