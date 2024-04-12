package com.mtuci.akino.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.request.ImageRequest
import com.google.accompanist.coil.rememberCoilPainter
import com.mtuci.akino.R
import com.mtuci.akino.ui.theme.BackgroundLightColor
import com.mtuci.akino.ui.theme.PrimaryColor
import com.mtuci.akino.ui.theme.SecondaryColor
import com.mtuci.akino.ui.theme.TextLightColor

@Composable
fun DetailsContent(
    movieDetails: MovieDetails,
    onBackClick: () -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .safeDrawingPadding()
            .padding(32.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_back),
                contentDescription = "",
                tint = PrimaryColor,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .clip(CircleShape)
                    .clickable(onClick = onBackClick)
                    .padding(vertical = 6.dp, horizontal = 10.dp)
            )
            Icon(
                painter = painterResource(R.drawable.ic_share),
                contentDescription = "",
                tint = PrimaryColor,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .clip(CircleShape)
                    .clickable { }
                    .padding(6.dp)
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        Image(
            painter = rememberCoilPainter(
                request = ImageRequest.Builder(LocalContext.current).crossfade(true)
                    .data(movieDetails.backdrop.url).build()
            ),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(400.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = movieDetails.name,
            fontWeight = FontWeight.Medium,
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Box(modifier = Modifier.fillMaxWidth()) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Text(
                    text = movieDetails.countries[0].name,
                    color = TextLightColor,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    painter = painterResource(R.drawable.ic_lang),
                    contentDescription = "",
                    tint = SecondaryColor
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.align(Alignment.Center)
            ) {
                Text(
                    text = "${movieDetails.movieLength} min",
                    color = TextLightColor,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    painter = painterResource(R.drawable.ic_time),
                    contentDescription = "",
                    tint = SecondaryColor
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                Text(
                    text = movieDetails.rating.kp.toString(),
                    color = TextLightColor,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    painter = painterResource(R.drawable.ic_star),
                    contentDescription = "",
                    tint = SecondaryColor
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.description),
                color = PrimaryColor,
                fontWeight = FontWeight.Medium, 
                fontSize = 18.sp
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = movieDetails.description,
            color = PrimaryColor,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
            )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(R.string.actors),
            color = PrimaryColor,
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(12.dp))
        LazyRow(modifier = Modifier.fillMaxWidth()){
            items(movieDetails.persons){ person ->
                PersonContent(person = person)
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(R.string.reviews),
            color = PrimaryColor,
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp
        )
    }
}