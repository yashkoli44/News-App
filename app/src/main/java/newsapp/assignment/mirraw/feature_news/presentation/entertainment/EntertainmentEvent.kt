package newsapp.assignment.mirraw.feature_news.presentation.entertainment

sealed class EntertainmentEvent {

    data class FetchNews(val page: Int = 0): EntertainmentEvent()

}