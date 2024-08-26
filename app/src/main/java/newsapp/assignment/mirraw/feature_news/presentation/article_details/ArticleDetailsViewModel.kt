package newsapp.assignment.mirraw.feature_news.presentation.article_details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import newsapp.assignment.mirraw.feature_news.domain.use_cases.NewsUseCases
import javax.inject.Inject

@HiltViewModel
class ArticleDetailsViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
): ViewModel() {

    private var _state = mutableStateOf(ArticleDetailsState())
    val state: State<ArticleDetailsState> = _state

    fun onEvent(articleDetailsEvent: ArticleDetailsEvent){
        when(articleDetailsEvent){
            is ArticleDetailsEvent.CheckBookmark -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val exists = newsUseCases.bookmarkExists(articleDetailsEvent.url)
                    _state.value = _state.value.copy(isBookmarked = exists)
                }
            }
            is ArticleDetailsEvent.ToggleBookmark -> {
                viewModelScope.launch(Dispatchers.IO) {
                    if(!_state.value.bookmarkInProgress) {
                        if (state.value.isBookmarked) {
                            _state.value =
                                _state.value.copy(isBookmarked = false, bookmarkInProgress = true)
                            try {
                                newsUseCases.removeBookmark(articleDetailsEvent.bookmark.url)
                                _state.value = _state.value.copy(bookmarkInProgress = false)
                            } catch (e: Exception) {
                                _state.value = _state.value.copy(
                                    isBookmarked = true,
                                    bookmarkInProgress = false
                                )
                            }
                        } else {
                            _state.value =
                                _state.value.copy(isBookmarked = true, bookmarkInProgress = true)
                            try {
                                newsUseCases.bookmarkArticle(articleDetailsEvent.bookmark)
                                _state.value = _state.value.copy(bookmarkInProgress = false)

                            } catch (e: Exception) {
                                _state.value = _state.value.copy(
                                    isBookmarked = false,
                                    bookmarkInProgress = false
                                )
                            }
                        }
                    }
                }
            }
        }
    }

}