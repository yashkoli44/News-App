package newsapp.assignment.mirraw.feature_news.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import newsapp.assignment.mirraw.feature_news.domain.models.Article
import newsapp.assignment.mirraw.feature_news.domain.models.Bookmark

@Dao
abstract class BookmarksDao {

    @Query("SELECT * FROM bookmarks where url = :url")
    abstract fun getBookmarkByUrl(url: String): Bookmark?

    @Query("SELECT * FROM bookmarks")
    abstract fun getAllBookmarks(): List<Bookmark>

    @Insert
    abstract fun insertBookmark(bookmark: Bookmark)

    @Query("DELETE FROM bookmarks WHERE url = :url")
    abstract fun removeBookmark(url: String)

}