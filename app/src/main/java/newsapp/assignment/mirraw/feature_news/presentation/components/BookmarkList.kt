package newsapp.assignment.mirraw.feature_news.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Bookmarks
import androidx.compose.material.icons.rounded.Newspaper
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import newsapp.assignment.mirraw.R
import newsapp.assignment.mirraw.feature_news.domain.models.Article
import newsapp.assignment.mirraw.feature_news.domain.models.Bookmark
import newsapp.assignment.mirraw.utils.toSpecialString

@Composable
fun BookmarkList(
    loading: Boolean,
    bookmarks: List<Bookmark>,
    emptyMessage: String? = null,
    onClick: (Bookmark) -> Unit = {}
) {


    if (loading && bookmarks.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                strokeCap = StrokeCap.Round
            )
        }
    } else if (bookmarks.isNotEmpty()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            item(contentType = -1) {
                Spacer(modifier = Modifier.height(10.dp))
            }
            items(
                bookmarks.size
            ) {

                Card(
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .clickable { onClick(bookmarks[it]) }
                ) {
                    AsyncImage(
                        model = bookmarks[it].urlToImage,
                        placeholder = painterResource(id = R.drawable.default_news),
                        error = painterResource(id = R.drawable.default_news),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {

                        Text(
                            bookmarks[it].title,
                            style = MaterialTheme.typography.titleLarge,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 2
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(
                            bookmarks[it].description,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray),
                            maxLines = 3
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                        Text(
                            bookmarks[it].publishedAt.toSpecialString(),
                            modifier = Modifier.align(Alignment.End),
                            overflow = TextOverflow.Ellipsis,
                            style = TextStyle(color = Color.Gray, fontSize = 13.sp),
                            maxLines = 2
                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
            if (loading) {
                item(contentType = -1) {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Row(modifier = Modifier.align(Alignment.Center)) {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .padding(5.dp)
                                    .size(15.dp), strokeWidth = 2.dp,
                                strokeCap = StrokeCap.Round
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(
                                text = "Hang on...",
                                style = LocalTextStyle.current.copy(color = Color.Gray)
                            )
                        }
                    }
                }
            }
            item(contentType = -1) {
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    } else {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    Icons.Outlined.Bookmarks,
                    contentDescription = "bookmarks",
                    modifier = Modifier.size(100.dp),
                    tint = Color.Gray
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text("No bookmarks", style = TextStyle(color = Color.Gray))
            }
        }
    }
}