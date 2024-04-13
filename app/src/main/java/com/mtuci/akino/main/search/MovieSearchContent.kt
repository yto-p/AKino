package com.mtuci.akino.main.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
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
import com.mtuci.akino.ui.theme.BackgroundLightColor
import com.mtuci.akino.ui.theme.PrimaryColor
import com.mtuci.akino.ui.theme.TextLightColor

@Composable
fun MovieSearchContent(
    movieSearch: Movie,
    openDetails: () -> Unit
){
    Column(
        modifier = Modifier
            .padding(2.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable(onClick = openDetails)
            .padding(8.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = rememberCoilPainter(
                    request = ImageRequest.Builder(LocalContext.current).crossfade(true)
                        .data(movieSearch.poster.previewUrl).build()
                ),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(120.dp)
                    .width(84.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 4.dp)
            ) {
                Text(
                    text = movieSearch.name.uppercase(),
                    color = PrimaryColor,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = movieSearch.year.toString(),
                    color = TextLightColor,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Divider(color = BackgroundLightColor, thickness = 1.dp)
    }
}