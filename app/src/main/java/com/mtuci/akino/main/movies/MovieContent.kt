package com.mtuci.akino.main.movies

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.mtuci.akino.main.data.Movie
import com.mtuci.akino.ui.theme.BackgroundLightColor
import com.mtuci.akino.ui.theme.PrimaryColor

@Composable
fun MovieContent(
    movie: Movie,
    openDetails: () -> Unit
){
    Column(
        Modifier
            .padding(2.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable(onClick = openDetails)
            .padding(8.dp)
    ) {
        SubcomposeAsyncImage(
            model = movie.poster.previewUrl,
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(240.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
        ){
            val state = painter.state
            if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error){
                Box(
                    modifier = Modifier
                        .height(240.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(BackgroundLightColor)
                )
            } else {
                SubcomposeAsyncImageContent()
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = movie.name.uppercase(),
            color = PrimaryColor,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(start = 8.dp)
        )
        Text(
            text = "(${movie.year})",
            color = PrimaryColor,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}