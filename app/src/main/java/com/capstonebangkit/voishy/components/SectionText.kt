package com.capstonebangkit.voishy.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.capstonebangkit.voishy.ui.theme.VoishyTheme

@Composable
fun SectionText(title: String, description: String, color: Color, type: String) {
    if (type == "medium"){
        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = color
                )
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.Normal,
                    color = color
                )
            )
        }
    } else if (type == "large"){
        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = color
                )
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Normal,
                    color = color
                )
            )
        }
    }
}

@Preview(showBackground = false)
@Composable
fun BannerPreview() {
    VoishyTheme {
        SectionText(
            title = "Let's Learn!",
            description = "Read our helpful material regarding speaking and more!",
            color = Color.White,
            type = "large"
        )
    }
}
