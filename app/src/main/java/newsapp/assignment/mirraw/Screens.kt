package newsapp.assignment.mirraw

sealed class Screens(val name: String) {
    data object HomeScreen: Screens("home_screen")
    data object BookmarkScreen: Screens("bookmark_screen")
    data object TechnologyScreen: Screens("technology_screen")
    data object EntertainmentScreen: Screens("entertainment_screen")
    data object SportsScreen: Screens("sports_screen")
    data object DetailsScreen: Screens("details_screen")
}