package newsapp.assignment.mirraw.feature_news.di

import android.app.Application
import androidx.room.Room
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import newsapp.assignment.mirraw.feature_news.data.data_source.BookmarksDao
import newsapp.assignment.mirraw.feature_news.data.data_source.NewsApi
import newsapp.assignment.mirraw.feature_news.data.data_source.NewsDatabase
import newsapp.assignment.mirraw.feature_news.data.repository.NewsRepositoryImpl
import newsapp.assignment.mirraw.feature_news.domain.repository.NewsRepository
import newsapp.assignment.mirraw.feature_news.domain.use_cases.BookmarkArticle
import newsapp.assignment.mirraw.feature_news.domain.use_cases.BookmarkExists
import newsapp.assignment.mirraw.feature_news.domain.use_cases.GetAllBookmarks
import newsapp.assignment.mirraw.feature_news.domain.use_cases.GetTopEntertainmentArticles
import newsapp.assignment.mirraw.feature_news.domain.use_cases.GetTopSportsArticles
import newsapp.assignment.mirraw.feature_news.domain.use_cases.GetTopTechnologyArticles
import newsapp.assignment.mirraw.feature_news.domain.use_cases.NewsUseCases
import newsapp.assignment.mirraw.feature_news.domain.use_cases.RemoveBookmark
import newsapp.assignment.mirraw.feature_news.domain.use_cases.SearchNewsArticles
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NewsModule {

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit{
        val gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create()
        return Retrofit.Builder()
            .baseUrl("https://newsapi.org")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun providesNewsApi(retrofit: Retrofit): NewsApi{
        return retrofit.create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun providesNewsRepository(newsApi: NewsApi, bookmarksDao: BookmarksDao): NewsRepository{
        return NewsRepositoryImpl(newsApi, bookmarksDao)
    }

    @Provides
    @Singleton
    fun providesNewsUseCases(newsRepository: NewsRepository): NewsUseCases{
        return NewsUseCases(
            getTopTechnologyArticles = GetTopTechnologyArticles(newsRepository),
            getTopEntertainmentArticles = GetTopEntertainmentArticles(newsRepository),
            getTopSportsArticles = GetTopSportsArticles(newsRepository),
            searchNewsArticles = SearchNewsArticles(newsRepository),
            bookmarkArticle = BookmarkArticle(newsRepository),
            bookmarkExists = BookmarkExists(newsRepository),
            getAllBookmarks = GetAllBookmarks(newsRepository),
            removeBookmark = RemoveBookmark(newsRepository),
        )
    }

    @Provides
    @Singleton
    fun providesNewsDatabase(application: Application): NewsDatabase {
        return Room.databaseBuilder(application, NewsDatabase::class.java, NewsDatabase.DATABASE_NAME).build()
    }

    @Provides
    @Singleton
    fun providesNewsDao(database: NewsDatabase): BookmarksDao {
        return database.bookmarksDao
    }


}