package newsapp.assignment.mirraw.feature_news.presentation.entertainment

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Newspaper
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import newsapp.assignment.mirraw.R
import newsapp.assignment.mirraw.Screens
import newsapp.assignment.mirraw.feature_news.presentation.components.ArticlesList
import newsapp.assignment.mirraw.feature_news.presentation.sports.SportsEvent
import newsapp.assignment.mirraw.utils.toSpecialString
import newsapp.assignment.mirraw.utils.urlEncode

@Composable
fun EntertainmentScreen(
    navController: NavController,
    viewModel: EntertainmentViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    val listState = rememberLazyListState()

    val context = LocalContext.current

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
                    viewModel.onEvent(EntertainmentEvent.FetchNews())
                }
            }
    }

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when(event) {
                is EntertainmentViewModel.UiEvent.ShowToast -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }


    LaunchedEffect(key1 = true) {
        if(state.articles.isEmpty()) {
            viewModel.onEvent(EntertainmentEvent.FetchNews())
        }
    }

    Column(

    ) {
//        Row(
//            modifier = Modifier.padding(horizontal = 15.dp, vertical = 10.dp),
//            horizontalArrangement = Arrangement.SpaceBetween
//        ) {
//            Text(text = "Entertainment", style = MaterialTheme.typography.titleLarge)
//        }
        ArticlesList(
            loading = state.loading,
            articles = state.articles,
            emptyMessage = "No Star Power Today.",
            listState = listState,
            onClick = {
                navController.navigate(Screens.DetailsScreen.name + "?title=${it.title}&description=${it.description ?: "No description"}&image=${it.urlToImage?.urlEncode() ?: ""}&url=${it.url?.urlEncode() ?: ""}&published_at=${it.publishedAt.time}")
            },
            onRefresh = {
                viewModel.onEvent(EntertainmentEvent.FetchNews())
            }
        )

    }
}