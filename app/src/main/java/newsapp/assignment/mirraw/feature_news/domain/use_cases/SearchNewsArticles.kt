package newsapp.assignment.mirraw.feature_news.domain.use_cases

import newsapp.assignment.mirraw.feature_news.domain.models.Article
import newsapp.assignment.mirraw.feature_news.domain.repository.NewsRepository

class SearchNewsArticles(private val newsRepository: NewsRepository) {

    suspend operator fun invoke(query: String): List<Article>{
        return newsRepository.searchNews(query)
    }

}