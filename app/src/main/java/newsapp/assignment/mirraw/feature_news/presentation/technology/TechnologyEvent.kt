package newsapp.assignment.mirraw.feature_news.presentation.technology


sealed class TechnologyEvent {

    data class FetchNews(val page: Int = 0): TechnologyEvent()

}