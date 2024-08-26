package newsapp.assignment.mirraw.feature_news.data.repository

import newsapp.assignment.mirraw.feature_news.data.data_source.BookmarksDao
import newsapp.assignment.mirraw.feature_news.data.data_source.NewsApi
import newsapp.assignment.mirraw.feature_news.domain.models.Article
import newsapp.assignment.mirraw.feature_news.domain.models.Bookmark
import newsapp.assignment.mirraw.feature_news.domain.models.Source
import newsapp.assignment.mirraw.feature_news.domain.repository.NewsRepository
import java.util.Calendar
import java.util.Date

class NewsRepositoryImpl(
    private val newsApi: NewsApi,
    private val bookmarksDao: BookmarksDao,
) : NewsRepository {
    override suspend fun getTopTechnologyArticles(page: Int): List<Article> {
        val response = newsApi.getTopTechnologyArticles(page)
        return response.articles

        // Test
//        val c = Calendar.getInstance()
//        c.set(Calendar.MONTH, Calendar.AUGUST)
//        c.set(Calendar.YEAR, 2024)
//        c.set(Calendar.DAY_OF_MONTH, 25)
//
//        return listOf(
//            Article(
//                id = null,
//                source = Source("929", ""),
//                author = "test",
//                title = "World's First Cat Elected as Mayor, Promises More Naps and Tuna for All!",
//                description = null,
//                url = null,
//                content = null,
//                publishedAt = c.time,
////                urlToImage = "https://img.freepik.com/free-photo/wide-angle-shot-single-tree-growing-clouded-sky-during-sunset-surrounded-by-grass_181624-22807.jpg?w=740&t=st=1724582568~exp=1724583168~hmac=6d64c0b9988f663489c99f8846d6667d6bd5956901541ff1e4a8fa2f9c12933a",
//                urlToImage = null
//            ),
//            Article(
//                id = null,
//                source = Source("929", ""),
//                author = "test",
//                title = "World's First Cat Elected as Mayor, Promises More Naps and Tuna for All!",
//                description = "In an unprecedented move, the small town of Whiskerville has elected a cat named Mr. Whiskers as their new mayor. The feline politician ran on a platform of 'Purrs for Peace' and has already enacted policies encouraging daily naps and unlimited tuna treats. While some residents are skeptical about his ability to lead, others are fully embracing the change, with local businesses stocking up on catnip and scratching posts. Mr. Whiskers, who spent most of his first day in office lounging on the mayoral desk, has yet to comment on his plans for infrastructure or public safety, but his popularity continues to soar as the town rallies behind its new furry leader. This is a test line so I can test if its scrolling or not. This is a test line so I can test if its scrolling or not. This is a test line so I can test if its scrolling or not. This is a test line so I can test if its scrolling or not.",
//                url = "test2",
//                content = "test",
//                publishedAt = c.time,
//                urlToImage = "https://img.freepik.com/free-photo/wide-angle-shot-single-tree-growing-clouded-sky-during-sunset-surrounded-by-grass_181624-22807.jpg?w=740&t=st=1724582568~exp=1724583168~hmac=6d64c0b9988f663489c99f8846d6667d6bd5956901541ff1e4a8fa2f9c12933a",
//            ),
//            Article(
//                id = null,
//                source = Source("929", ""),
//                author = "test",
//                title = "World's First Cat Elected as Mayor, Promises More Naps and Tuna for All!",
//                description = "In an unprecedented move, the small town of Whiskerville has elected a cat named Mr. Whiskers as their new mayor. The feline politician ran on a platform of 'Purrs for Peace' and has already enacted policies encouraging daily naps and unlimited tuna treats. While some residents are skeptical about his ability to lead, others are fully embracing the change, with local businesses stocking up on catnip and scratching posts. Mr. Whiskers, who spent most of his first day in office lounging on the mayoral desk, has yet to comment on his plans for infrastructure or public safety, but his popularity continues to soar as the town rallies behind its new furry leader. This is a test line so I can test if its scrolling or not. This is a test line so I can test if its scrolling or not. This is a test line so I can test if its scrolling or not. This is a test line so I can test if its scrolling or not.",
//                url = "test3",
//                content = "test",
//                publishedAt = c.time,
//                urlToImage = "https://img.freepik.com/free-photo/wide-angle-shot-single-tree-growing-clouded-sky-during-sunset-surrounded-by-grass_181624-22807.jpg?w=740&t=st=1724582568~exp=1724583168~hmac=6d64c0b9988f663489c99f8846d6667d6bd5956901541ff1e4a8fa2f9c12933a",
//            ),
//            Article(
//                id = null,
//                source = Source("929", ""),
//                author = "test",
//                title = "World's First Cat Elected as Mayor, Promises More Naps and Tuna for All!",
//                description = "In an unprecedented move, the small town of Whiskerville has elected a cat named Mr. Whiskers as their new mayor. The feline politician ran on a platform of 'Purrs for Peace' and has already enacted policies encouraging daily naps and unlimited tuna treats. While some residents are skeptical about his ability to lead, others are fully embracing the change, with local businesses stocking up on catnip and scratching posts. Mr. Whiskers, who spent most of his first day in office lounging on the mayoral desk, has yet to comment on his plans for infrastructure or public safety, but his popularity continues to soar as the town rallies behind its new furry leader. This is a test line so I can test if its scrolling or not. This is a test line so I can test if its scrolling or not. This is a test line so I can test if its scrolling or not. This is a test line so I can test if its scrolling or not.",
//                url = "test4",
//                content = "test",
//                publishedAt = c.time,
//                urlToImage = "https://img.freepik.com/free-photo/wide-angle-shot-single-tree-growing-clouded-sky-during-sunset-surrounded-by-grass_181624-22807.jpg?w=740&t=st=1724582568~exp=1724583168~hmac=6d64c0b9988f663489c99f8846d6667d6bd5956901541ff1e4a8fa2f9c12933a",
//            ),
//
//        )
    }

    override suspend fun getTopEntertainmentArticles(page: Int): List<Article> {
        val response = newsApi.getTopEntertainmentArticles(page)
        return response.articles
    }

    override suspend fun getTopSportsArticles(page: Int): List<Article> {
        val response = newsApi.getTopSportsArticles(page)
        return response.articles
    }

    // Unused
    override suspend fun searchNews(query: String): List<Article> {
        val response = newsApi.searchArticles(query)
        return response.articles
    }

    override suspend fun getAllBookmarks(): List<Bookmark> {
        return bookmarksDao.getAllBookmarks()
    }

    override suspend fun bookmarkArticle(bookmark: Bookmark) {
        return bookmarksDao.insertBookmark(bookmark)
    }

    override suspend fun bookmarkExists(url: String): Boolean {
        return bookmarksDao.getBookmarkByUrl(url) != null
    }

    override suspend fun removeBookmark(url: String) {
        return bookmarksDao.removeBookmark(url)
    }
}