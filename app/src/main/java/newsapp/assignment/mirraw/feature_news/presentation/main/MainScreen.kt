package newsapp.assignment.mirraw.feature_news.presentation.main

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Memory
import androidx.compose.material.icons.filled.Tv
import androidx.compose.material.icons.outlined.Bookmarks
import androidx.compose.material.icons.outlined.Memory
import androidx.compose.material.icons.outlined.SportsSoccer
import androidx.compose.material.icons.outlined.Tv
import androidx.compose.material.icons.rounded.Bookmark
import androidx.compose.material.icons.rounded.Bookmarks
import androidx.compose.material.icons.rounded.Memory
import androidx.compose.material.icons.rounded.MilitaryTech
import androidx.compose.material.icons.rounded.SportsSoccer
import androidx.compose.material.icons.rounded.Tv
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import newsapp.assignment.mirraw.Screens
import newsapp.assignment.mirraw.feature_news.presentation.bookmark.BookmarkScreen
import newsapp.assignment.mirraw.feature_news.presentation.entertainment.EntertainmentScreen
import newsapp.assignment.mirraw.feature_news.presentation.sports.SportsScreen
import newsapp.assignment.mirraw.feature_news.presentation.technology.TechnologyScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    navController: NavController,
    homeViewModel: MainViewModel = hiltViewModel()
) {

    val state = homeViewModel.state.value

    val pagerState = rememberPagerState { 3 }

    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = true) {
        snapshotFlow { pagerState.currentPage }.collect {
            homeViewModel.onEvent(MainEvents.NavigateTo(it))
        }
    }

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = state.selectedIndex == 0,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(0)
                        }
                        homeViewModel.onEvent(MainEvents.NavigateTo(0))
                    },
                    icon = { Icon(Icons.Outlined.Memory , contentDescription = "technology") },
                    label = { Text(text = "Technology") })
                NavigationBarItem(
                    selected = state.selectedIndex == 1,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(1)
                        }
                        homeViewModel.onEvent(MainEvents.NavigateTo(1))
                    },
                    icon = { Icon(Icons.Outlined.Tv, contentDescription = "entertainmemt") },
                    label = { Text(text = "Entertainment") })
                NavigationBarItem(
                    selected = state.selectedIndex == 2,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(2)
                        }
                        homeViewModel.onEvent(MainEvents.NavigateTo(2))
                    },
                    icon = { Icon(Icons.Outlined.SportsSoccer, contentDescription = "sports") },
                    label = { Text(text = "Sports") })
//                NavigationBarItem(
//                    selected = state.selectedIndex == 3,
//                    onClick = {
//                        coroutineScope.launch {
//                            pagerState.animateScrollToPage(3)
//                        }
//                        homeViewModel.onEvent(MainEvents.NavigateTo(3))
//                    },
//                    icon = { Icon(Icons.Outlined.Bookmarks, contentDescription = "bookmark") },
//                    label = { Text(text = "Bookmarks") })
            }
        },
    ) {
        Column(modifier = Modifier.padding(it)) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 15.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "News", style = MaterialTheme.typography.titleLarge)
                IconButton(onClick = {
                    navController.navigate(Screens.BookmarkScreen.name)
                }) {
                    Icon(Icons.Rounded.Bookmarks, contentDescription = "Bookmarks")
                }
            }
            HorizontalPager(
                state = pagerState, beyondBoundsPageCount = 3
            ) { index ->
                when (index) {
                    0 -> {
                        TechnologyScreen(navController)
                    }

                    1 -> {
                        EntertainmentScreen(navController)
                    }

                    2 -> {
                        SportsScreen(navController)
                    }
//                    3 -> {
//                        BookmarkScreen(navController)
//                    }
                }
            }
        }
    }

}