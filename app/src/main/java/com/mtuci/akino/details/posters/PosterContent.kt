package com.mtuci.akino.details.posters

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.request.ImageRequest
import com.google.accompanist.coil.rememberCoilPainter
import com.mtuci.akino.details.data.Poster

@Composable
fun PosterContent(poster: Poster){
    Column {
        Image(
            painter = rememberCoilPainter(
                request = ImageRequest.Builder(LocalContext.current).crossfade(true)
                    .data(poster.previewUrl).build()
            ),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(150.dp)
                .width(230.dp)
                .padding(horizontal = 3.dp)
                .clip(RoundedCornerShape(8.dp))
        )
    }
}