package newsapp.assignment.mirraw.feature_news.presentation.bookmark

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import newsapp.assignment.mirraw.feature_news.domain.use_cases.NewsUseCases
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val newsUseCase: NewsUseCases
): ViewModel() {

    private var _state = mutableStateOf(BookmarkState())
    val state: State<BookmarkState> = _state

    fun onEvent(event: BookmarkEvent){
        when(event){
            is BookmarkEvent.Fetch -> {
                getBookmarks()
            }
        }
    }

    private fun getBookmarks() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _state.value = _state.value.copy(loading = true)
                val articles = newsUseCase.getAllBookmarks()
                _state.value = _state.value.copy(loading = false, bookmarks = articles)
            } catch (e: RuntimeException){
//                Log.d("RuntimeException", e.message.toString())
                _state.value = _state.value.copy(loading = false)
            } catch (e: Exception){
//                Log.d("Exception", e.message.toString())
                _state.value = _state.value.copy(loading = false)
            }
        }
    }

}