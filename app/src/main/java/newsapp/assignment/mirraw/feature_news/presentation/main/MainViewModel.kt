package newsapp.assignment.mirraw.feature_news.presentation.main

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import newsapp.assignment.mirraw.feature_news.domain.use_cases.NewsUseCases
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val newsUseCases: NewsUseCases
): ViewModel() {

    private var _state = mutableStateOf(MainState())
    val state: State<MainState> = _state

    fun onEvent(homeEvents: MainEvents){
        when(homeEvents){
            is MainEvents.NavigateTo -> {
                _state.value = _state.value.copy(selectedIndex = homeEvents.index)
            }
        }
    }

}