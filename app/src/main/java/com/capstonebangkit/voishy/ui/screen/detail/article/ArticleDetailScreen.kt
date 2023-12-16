package com.capstonebangkit.voishy.ui.screen.detail.article

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.capstonebangkit.voishy.R
import com.capstonebangkit.voishy.components.SectionText
import com.capstonebangkit.voishy.ui.screen.ViewModelFactory
import com.capstonebangkit.voishy.ui.theme.VoishyTheme

@Composable
fun ArticleDetailScreen(articleTitle: String) {
    // Get the ViewModelFactory instance
    val viewModelFactory = ViewModelFactory.getInstance()

    // Instantiate the ArticleViewModel
    val articleViewModel: ArticleViewModel = viewModel(factory = viewModelFactory)

    // Fetch the article details
    LaunchedEffect(articleTitle) {
        articleViewModel.fetchArticleDetail(articleTitle)
    }

    // Observe the article details from the ViewModel
    val article = articleViewModel.articleDetail.collectAsState().value

    // UI code to display the article details
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        article?.let {
            Banner(titleString = it.title.toString())
            Text(
                text = "Time to Read: ${it.times.toString()}",
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.padding(vertical = 4.dp, horizontal = 16.dp)
            )
            Text(
                text = it.article.toString(), style = MaterialTheme.typography.bodyMedium.plus(
                    ParagraphStyle(
                        TextAlign.Justify
                    )
                ),
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
            )
        }
    }
}

@Composable
fun Banner(modifier: Modifier = Modifier, titleString: String) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(R.drawable.profile),
            contentDescription = "Banner Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.requiredHeight(300.dp)
        )
        Column(
            modifier = Modifier
                .matchParentSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                text = titleString, style = MaterialTheme.typography.displaySmall.plus(
                    ParagraphStyle(textAlign = TextAlign.Start)
                ), color = Color.White
            )
        }
    }
}
