package newsapp.assignment.mirraw

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import newsapp.assignment.mirraw.feature_news.presentation.article_details.ArticleDetailsScreen
import newsapp.assignment.mirraw.feature_news.presentation.bookmark.BookmarkScreen
import newsapp.assignment.mirraw.feature_news.presentation.main.HomeScreen
import newsapp.assignment.mirraw.ui.theme.NewsAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            NewsAppTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    NavHost(
                        navController = navController,
                        startDestination = Screens.HomeScreen.name
                    ) {
                        composable(
                            Screens.HomeScreen.name,
                            popExitTransition = { ExitTransition.None },
                            popEnterTransition = { EnterTransition.None }) {
                            HomeScreen(navController)
                        }
                        composable(
                            Screens.BookmarkScreen.name,
                            enterTransition = {
                                slideIntoContainer(
                                    towards = AnimatedContentTransitionScope.SlideDirection.Start,
                                    tween(durationMillis = 500)
                                )
                            },
                            exitTransition = {
                                ExitTransition.KeepUntilTransitionsFinished
                            },
                            popExitTransition = { slideOutOfContainer(
                                towards = AnimatedContentTransitionScope.SlideDirection.End,
                                tween(durationMillis = 500)
                            ) },
                            popEnterTransition = { EnterTransition.None }) {
                            BookmarkScreen(navController)
                        }
                        composable(Screens.DetailsScreen.name + "?title={title}&description={description}&image={image}&url={url}&published_at={published_at}",
                            arguments = listOf(
                                navArgument(
                                    name = "title",
                                ) {
                                    type = NavType.StringType
                                },
                                navArgument(
                                    name = "description",
                                ) {
                                    type = NavType.StringType
                                },
                                navArgument(
                                    name = "image",
                                ) {
                                    type = NavType.StringType
                                },
                                navArgument(
                                    name = "url",
                                ) {
                                    type = NavType.StringType
                                },
                                navArgument(
                                    name = "published_at",
                                ) {
                                    type = NavType.LongType
                                }
                            ), enterTransition = {
                                slideIntoContainer(
                                    towards = AnimatedContentTransitionScope.SlideDirection.Start,
                                    tween(durationMillis = 500)
                                )
                            }, exitTransition = {
                                slideOutOfContainer(
                                    towards = AnimatedContentTransitionScope.SlideDirection.End,
                                    tween(durationMillis = 500)
                                )
                            }) {
                            val title = it.arguments?.getString("title") ?: "No title"
                            val description =
                                it.arguments?.getString("description") ?: "No description"
                            val image = it.arguments?.getString("image")
                            val url = it.arguments?.getString("url") ?: ""
                            val publishedAt = it.arguments?.getLong("published_at") ?: 0L
                            ArticleDetailsScreen(
                                navController,
                                title,
                                description,
                                image,
                                url,
                                publishedAt
                            )
                        }
                    }
                }
            }
        }
    }
}
