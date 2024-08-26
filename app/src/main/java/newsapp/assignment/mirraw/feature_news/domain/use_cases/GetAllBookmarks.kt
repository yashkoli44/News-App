package newsapp.assignment.mirraw.feature_news.domain.use_cases

import newsapp.assignment.mirraw.feature_news.domain.models.Bookmark
import newsapp.assignment.mirraw.feature_news.domain.repository.NewsRepository

class GetAllBookmarks(
    val newsRepository: NewsRepository
) {

    suspend operator fun invoke(): List<Bookmark>{
        return newsRepository.getAllBookmarks()
    }
}