package com.mtuci.akino.details

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.mtuci.akino.R
import com.mtuci.akino.details.data.MovieDetails
import com.mtuci.akino.details.data.Poster
import com.mtuci.akino.details.data.Review
import com.mtuci.akino.details.persons.PersonContent
import com.mtuci.akino.details.posters.PosterContent
import com.mtuci.akino.details.reviews.ReviewContent
import com.mtuci.akino.ui.theme.BackgroundLightColor
import com.mtuci.akino.ui.theme.PrimaryColor
import com.mtuci.akino.ui.theme.SecondaryColor
import com.mtuci.akino.ui.theme.TextLightColor

@Composable
fun DetailsContent(
    isLoading: Boolean,
    movieDetails: MovieDetails?,
    posters: List<Poster>?,
    pagingData: LazyPagingItems<Review>,
    onBackClick: () -> Unit
){
    if (isLoading) {
        Box(
            Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator(
                color = SecondaryColor,
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .safeDrawingPadding()
                .padding(top = 32.dp)
        ) {
            LazyColumn(modifier = Modifier.fillMaxSize()){
                item {
                    movieDetails ?: return@item
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
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
                        SubcomposeAsyncImage(
                            model = movieDetails.backdrop?.url,
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .height(400.dp)
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(16.dp))
                        ){
                            val state = painter.state
                            if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error){
                                Box(
                                    modifier = Modifier
                                        .height(400.dp)
                                        .fillMaxWidth()
                                        .clip(RoundedCornerShape(16.dp))
                                        .background(BackgroundLightColor)
                                )
                            } else {
                                SubcomposeAsyncImageContent()
                            }
                        }
                        Spacer(modifier = Modifier.height(24.dp))
                        Text(
                            text = movieDetails.name ?: stringResource(id = R.string.empty),
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
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = movieDetails.description ?: stringResource(R.string.empty),
                            color = PrimaryColor,
                            fontWeight = FontWeight.Normal,
                            fontSize = 16.sp
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        Text(
                            text = stringResource(R.string.posters),
                            color = PrimaryColor,
                            fontWeight = FontWeight.Medium,
                            fontSize = 18.sp
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                    if (posters?.size == 0){
                        Text(
                            text = stringResource(R.string.empty),
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 12.dp),
                        modifier = Modifier.fillMaxWidth()
                    ){
                        posters ?: return@LazyRow
                        items(posters){ poster ->
                            PosterContent(poster = poster)
                        }
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = stringResource(R.string.persons),
                        color = PrimaryColor,
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    if (movieDetails.persons.isEmpty()){
                        Text(
                            text = stringResource(R.string.empty),
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
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
                        fontSize = 18.sp,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                    if (pagingData.itemCount == 0){
                        Text(
                            text = stringResource(R.string.empty),
                            modifier = Modifier
                                .padding(vertical = 12.dp)
                                .padding(start = 16.dp)
                        )
                    }
                }
                if (pagingData.loadState.refresh is LoadState.Loading){
                    item {
                        Box(modifier = Modifier
                            .fillMaxSize()
                            .padding(15.dp)){
                            CircularProgressIndicator(
                                color = SecondaryColor,
                                modifier = Modifier
                                    .align(Alignment.Center))
                        }
                    }
                }
                if (pagingData.loadState.refresh is LoadState.NotLoading){
                    items(pagingData.itemCount) { index ->
                        val item = pagingData[index]
                        item?.let {
                            ReviewContent(
                                review = item
                            )
                        }
                    }
                }
                if (pagingData.loadState.refresh is LoadState.Error){
                    item {
                        Box(modifier = Modifier
                            .fillMaxSize()
                            .padding(15.dp)) {
                            Text(
                                text = stringResource(R.string.retry),
                                fontStyle = FontStyle.Italic,
                                color = PrimaryColor,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Normal,
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .clickable {
                                        pagingData.refresh()
                                    })
                        }
                    }
                }
                if (pagingData.loadState.append is LoadState.Loading){
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(15.dp)
                        ) {
                            CircularProgressIndicator(
                                color = SecondaryColor,
                                modifier = Modifier
                                    .align(Alignment.Center)
                            )
                        }
                    }
                }
                if (pagingData.loadState.append is LoadState.Error){
                    item {
                        Error { pagingData.retry() }
                    }
                }
            }
        }
    }
}
@Composable
fun Error(retry: () -> Unit){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .align(Alignment.Center)
        ) {
            Text(
                text = stringResource(R.string.retry),
                fontStyle = FontStyle.Italic,
                color = PrimaryColor,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier
                    .clickable { retry.invoke() }
            )
        }
    }
}