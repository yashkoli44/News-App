package newsapp.assignment.mirraw.feature_news.domain.repository

import newsapp.assignment.mirraw.feature_news.domain.models.Article
import newsapp.assignment.mirraw.feature_news.domain.models.Bookmark

interface NewsRepository {

    suspend fun getTopTechnologyArticles(page: Int): List<Article>

    suspend fun getTopEntertainmentArticles(page: Int): List<Article>

    suspend fun getTopSportsArticles(page: Int): List<Article>

    suspend fun searchNews(query: String): List<Article>

    suspend fun getAllBookmarks(): List<Bookmark>

    suspend fun bookmarkArticle(bookmark: Bookmark)

    suspend fun bookmarkExists(url: String): Boolean

    suspend fun removeBookmark(url: String)


}