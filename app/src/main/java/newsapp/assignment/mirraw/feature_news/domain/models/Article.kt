package newsapp.assignment.mirraw.feature_news.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

data class Article(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val source: Source?,
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: Date,
    val content: String?,
)
