package newsapp.assignment.mirraw.feature_news.presentation.sports

sealed class SportsEvent {

    data class FetchNews(val page: Int = 0): SportsEvent()

}