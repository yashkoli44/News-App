package newsapp.assignment.mirraw.feature_news.presentation.article_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Bookmark
import androidx.compose.material.icons.rounded.BookmarkBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import newsapp.assignment.mirraw.R
import newsapp.assignment.mirraw.feature_news.domain.models.Bookmark
import java.util.Calendar
import java.util.Date

@Composable
fun ArticleDetailsScreen(
    navController: NavController,
    title: String,
    description: String,
    image: String?,
    url: String,
    publishedAt: Long,
    articleDetailsViewModel: ArticleDetailsViewModel = hiltViewModel()
) {

    val state = articleDetailsViewModel.state.value

    LaunchedEffect(key1 = true) {
        articleDetailsViewModel.onEvent(ArticleDetailsEvent.CheckBookmark(url))
    }

    Scaffold {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            Box {
                AsyncImage(
                    model = image,
                    placeholder = painterResource(id = R.drawable.default_news),
                    error = painterResource(id = R.drawable.default_news),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    IconButton(
                        onClick = {
                            navController.navigateUp()
                        },
                        modifier = Modifier
                            .padding(10.dp)
                            .background(color = Color.White, shape = CircleShape)
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.Black
                        )
                    }
                    IconButton(
                        onClick = {
                            articleDetailsViewModel.onEvent(
                                ArticleDetailsEvent.ToggleBookmark(
                                    Bookmark(
                                        title = title,
                                        description = description,
                                        urlToImage = image ?: "",
                                        url = url,
                                        publishedAt = Date(publishedAt)
                                    )
                                )
                            )
                        },
                        enabled = url.isNotBlank(),
                        modifier = Modifier
                            .padding(10.dp)
                            .background(color = Color.White, shape = CircleShape)
                    ) {
                        Icon(
                            imageVector = if (state.isBookmarked) Icons.Rounded.Bookmark else Icons.Rounded.BookmarkBorder,
                            contentDescription = "Bookmark",
                            tint = Color.Black
                        )
                    }
                }
            }
            Column(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = title, style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Description", style = LocalTextStyle.current.copy(color = Color.Gray))
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = description)
                Spacer(modifier = Modifier.height(10.dp))

            }

        }
    }

}