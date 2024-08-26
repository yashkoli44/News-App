package newsapp.assignment.mirraw.feature_news.presentation.main

sealed class MainEvents {
    data class NavigateTo(val index: Int): MainEvents()
}