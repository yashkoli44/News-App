package newsapp.assignment.mirraw.feature_news.presentation.sports

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import newsapp.assignment.mirraw.Screens
import newsapp.assignment.mirraw.feature_news.presentation.components.ArticlesList
import newsapp.assignment.mirraw.feature_news.presentation.entertainment.EntertainmentEvent
import newsapp.assignment.mirraw.feature_news.presentation.entertainment.EntertainmentViewModel
import newsapp.assignment.mirraw.feature_news.presentation.technology.TechnologyViewModel
import newsapp.assignment.mirraw.utils.urlEncode

@Composable
fun SportsScreen(
    navController: NavController,
    viewModel: SportsViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    val listState = rememberLazyListState()

    LaunchedEffect(listState) {
        snapshotFlow {
            listState.layoutInfo.visibleItemsInfo.filter { item -> item.contentType != -1 }
                .lastOrNull()?.index
        }
            .map {
                it == viewModel.state.value.articles.lastIndex
            }
            .distinctUntilChanged()
            .collectLatest { isAtEnd ->
                if (isAtEnd) {
                    viewModel.onEvent(SportsEvent.FetchNews())
                }
            }
    }

    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when(event) {
                is SportsViewModel.UiEvent.ShowToast -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }


    LaunchedEffect(Unit) {
        if(state.articles.isEmpty()) {
            viewModel.onEvent(SportsEvent.FetchNews())
        }
    }

    Column(
    ) {
//        Row(
//            modifier = Modifier.padding(horizontal = 15.dp, vertical = 10.dp),
//            horizontalArrangement = Arrangement.SpaceBetween
//        ) {
//            Text(text = "Sports", style = MaterialTheme.typography.titleLarge)
//        }
        ArticlesList(
            loading = state.loading,
            articles = state.articles,
            emptyMessage = "All Quiet on the Sports Front.",
            listState = listState,
            onClick = {
                navController.navigate(Screens.DetailsScreen.name + "?title=${it.title}&description=${it.description ?: "No description"}&image=${it.urlToImage?.urlEncode() ?: ""}&url=${it.url?.urlEncode() ?: ""}&published_at=${it.publishedAt.time}")
            },
            onRefresh = {
                viewModel.onEvent(SportsEvent.FetchNews())
            }
        )

    }
}