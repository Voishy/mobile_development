sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Voice : Screen("voice")
    object Materials : Screen("materials")
    object ArticleDetail : Screen("articleDetail/{articleTitle}")
    object MovieDetail : Screen("movieDetail/{movieId}")
    object ResultScreen : Screen("resultScreen")
}
