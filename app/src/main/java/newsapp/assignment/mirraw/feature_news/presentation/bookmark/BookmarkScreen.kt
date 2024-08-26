package newsapp.assignment.mirraw.feature_news.presentation.bookmark

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import newsapp.assignment.mirraw.Screens
import newsapp.assignment.mirraw.feature_news.presentation.components.BookmarkList
import newsapp.assignment.mirraw.utils.urlEncode

@Composable
fun BookmarkScreen(
    navController: NavController,
    viewModel: BookmarkViewModel = hiltViewModel()
) {

    val state = viewModel.state.value

    LaunchedEffect(key1 = true) {
        viewModel.onEvent(BookmarkEvent.Fetch())
    }

    Scaffold {
        Column(modifier = Modifier.padding(it)) {
            Row(
                modifier = Modifier.padding(horizontal = 15.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Bookmarks", style = MaterialTheme.typography.titleLarge)
            }
            BookmarkList(
                loading = state.loading,
                bookmarks = state.bookmarks,
                onClick = { bookmark ->
                    navController.navigate(Screens.DetailsScreen.name + "?title=${bookmark.title}&description=${bookmark.description}&image=${bookmark.urlToImage.urlEncode()}&url=${bookmark.url.urlEncode()}&published_at=${bookmark.publishedAt.time}")
                }
            )
        }
    }

}