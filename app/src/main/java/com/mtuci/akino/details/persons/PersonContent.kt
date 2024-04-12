package com.mtuci.akino.details.persons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.request.ImageRequest
import com.google.accompanist.coil.rememberCoilPainter
import com.mtuci.akino.details.data.Person
import com.mtuci.akino.ui.theme.PrimaryColor

@Composable
fun PersonContent(person: Person){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .width(90.dp)
    ) {
        Image(
            painter = rememberCoilPainter(
                request = ImageRequest.Builder(LocalContext.current).crossfade(true)
                    .data(person.photo).build()
            ),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = person.name ?: "No name",
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            maxLines = 2,
            color = PrimaryColor,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        )
    }
}