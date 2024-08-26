package newsapp.assignment.mirraw.feature_news.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import newsapp.assignment.mirraw.feature_news.data.data_source.DateConverter
import java.util.Date

@Entity(tableName = "bookmarks")
@TypeConverters(DateConverter::class)
data class Bookmark(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: Date,
)