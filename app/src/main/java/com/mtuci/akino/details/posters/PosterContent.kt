package com.mtuci.akino.details.posters

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.mtuci.akino.details.data.Poster
import com.mtuci.akino.ui.theme.BackgroundLightColor

@Composable
fun PosterContent(poster: Poster){
    Column {
        SubcomposeAsyncImage(
            model = poster.previewUrl,
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(150.dp)
                .width(230.dp)
                .padding(horizontal = 4.dp)
                .clip(RoundedCornerShape(8.dp))
        ){
            val state = painter.state
            if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error){
                Box(
                    modifier = Modifier
                        .height(150.dp)
                        .width(230.dp)
                        .padding(horizontal = 4.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(BackgroundLightColor)
                )
            } else {
                SubcomposeAsyncImageContent()
            }
        }
    }
}