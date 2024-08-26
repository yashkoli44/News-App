package newsapp.assignment.mirraw.feature_news.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import newsapp.assignment.mirraw.feature_news.domain.models.Article
import newsapp.assignment.mirraw.feature_news.domain.models.Bookmark

@Database(entities = [Bookmark::class], version = 1)
abstract class NewsDatabase: RoomDatabase() {

    abstract val bookmarksDao: BookmarksDao

    companion object{
        const val DATABASE_NAME = "news_db"
    }

}