package newsapp.assignment.mirraw.feature_news.domain.models

data class NewsResponse(
    val status: String, val totalResults: Int, val articles: List<Article>
)
