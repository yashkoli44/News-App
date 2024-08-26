package newsapp.assignment.mirraw.feature_news.data.data_source

import newsapp.assignment.mirraw.feature_news.domain.models.NewsResponse
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Query


interface NewsApi {

    @GET("/v2/top-headlines?category=technology&apiKey=API_KEY&country=in")
    suspend fun getTopTechnologyArticles(@Query("page") page: Int): NewsResponse

    @GET("/v2/top-headlines?category=entertainment&apiKey=API_KEY&country=in")
    suspend fun getTopEntertainmentArticles(@Query("page") page: Int): NewsResponse

    @GET("/v2/top-headlines?category=sports&apiKey=API_KEY&country=in")
    suspend fun getTopSportsArticles(@Query("page") page: Int): NewsResponse

    @GET("/v2/everything?apiKey=API_KEY&language=en")
    suspend fun searchArticles(@Query("q") query: String): NewsResponse

}