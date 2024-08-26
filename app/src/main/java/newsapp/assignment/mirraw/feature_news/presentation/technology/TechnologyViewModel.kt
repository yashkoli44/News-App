package newsapp.assignment.mirraw.feature_news.presentation.technology

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import newsapp.assignment.mirraw.feature_news.domain.use_cases.NewsUseCases
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class TechnologyViewModel @Inject constructor(
    private val newsUseCase: NewsUseCases
): ViewModel() {

    private var _state = mutableStateOf(TechnologyState())
    val state: State<TechnologyState> = _state

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: TechnologyEvent){
        when(event){
            is TechnologyEvent.FetchNews -> {
                if(_state.value.articles.isEmpty() || (!_state.value.loading && _state.value.articles.size == 20 * (_state.value.nextPage - 1))) {
                    getArticles()
                }
            }
        }
    }

    private fun getArticles() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _state.value = _state.value.copy(loading = true)
                val articles = newsUseCase.getTopTechnologyArticles(_state.value.nextPage)
                _state.value = _state.value.copy(loading = false, articles = _state.value.articles + articles, nextPage = _state.value.nextPage + 1)
            } catch (e: RuntimeException){
//                Log.d("RuntimeException", e.message.toString())
                _eventFlow.emit(UiEvent.ShowToast(e.message.toString()))
                _state.value = _state.value.copy(loading = false)
            } catch (e: HttpException){
//                Log.d("Exception", e.message.toString())
                _eventFlow.emit(UiEvent.ShowToast(e.message.toString()))
                _state.value = _state.value.copy(loading = false)
            } catch (e: Exception){
//                Log.d("Exception", e.message.toString())
                _eventFlow.emit(UiEvent.ShowToast(e.message.toString()))
                _state.value = _state.value.copy(loading = false)
            }
        }
    }

    sealed class UiEvent{
        data class ShowToast(val message: String): UiEvent()
    }

}