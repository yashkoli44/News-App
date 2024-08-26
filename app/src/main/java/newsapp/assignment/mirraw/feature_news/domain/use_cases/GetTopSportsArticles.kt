package newsapp.assignment.mirraw.feature_news.domain.use_cases

import newsapp.assignment.mirraw.feature_news.data.data_source.NewsApi
import newsapp.assignment.mirraw.feature_news.domain.models.Article
import newsapp.assignment.mirraw.feature_news.domain.repository.NewsRepository

class GetTopSportsArticles(
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(page: Int): List<Article>{
        return newsRepository.getTopSportsArticles(page)
    }
}