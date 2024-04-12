package com.mtuci.akino.main.movies

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.request.ImageRequest
import com.google.accompanist.coil.rememberCoilPainter
import com.mtuci.akino.main.data.Movie
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
        Image(
            painter = rememberCoilPainter(
                request = ImageRequest.Builder(LocalContext.current).crossfade(true)
                    .data(movie.poster.previewUrl).build()
            ),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(240.dp)
                .width(167.dp)
                .clip(RoundedCornerShape(16.dp))
        )
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