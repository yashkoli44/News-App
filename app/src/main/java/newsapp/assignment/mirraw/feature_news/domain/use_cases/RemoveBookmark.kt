package newsapp.assignment.mirraw.feature_news.domain.use_cases

import newsapp.assignment.mirraw.feature_news.domain.models.Article
import newsapp.assignment.mirraw.feature_news.domain.repository.NewsRepository

class RemoveBookmark(
    val newsRepository: NewsRepository
) {

    suspend operator fun invoke(url: String) {
        return newsRepository.removeBookmark(url)
    }

}