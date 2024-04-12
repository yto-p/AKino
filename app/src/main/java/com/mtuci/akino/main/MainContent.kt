package com.mtuci.akino.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.mtuci.akino.R
import com.mtuci.akino.ui.theme.PrimaryColor
import com.mtuci.akino.ui.theme.SecondaryColor

@Composable
fun MainContent(
    pagingData: LazyPagingItems<Movie>,
    openDetails: (id: Int) -> Unit,
    onFilterClick: () -> Unit,
    onSearchClick: () -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .padding(top = 32.dp)
            .padding(horizontal = 16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_filter),
                contentDescription = "",
                tint = PrimaryColor,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .clip(CircleShape)
                    .clickable(onClick = onFilterClick)
                    .padding(horizontal = 6.dp, vertical = 7.dp)
            )
            Icon(
                painter = painterResource(R.drawable.ic_search),
                contentDescription = "",
                tint = PrimaryColor,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .clip(CircleShape)
                    .clickable(onClick = onSearchClick)
                    .padding(6.dp)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(R.string.movies_and_series),
            color = PrimaryColor,
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp,
            modifier = Modifier.padding(start = 8.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            columns = GridCells.Fixed(2)){
            if (pagingData.loadState.refresh is LoadState.Loading){
                item {
                    Box(modifier = Modifier.fillMaxSize().padding(30.dp)){
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
                        MovieContent(
                            movie = item,
                            openDetails = { openDetails(item.id) }
                        )
                    }
                }
            }
            if (pagingData.loadState.refresh is LoadState.Error){
                item {
                    Box(modifier = Modifier
                        .fillMaxSize()
                        .padding(30.dp)) {
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
                            .padding(30.dp)
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
                    ErrorFooter { pagingData.retry() }
                }
            }
        }
    }
}

@Composable
fun ErrorFooter(retry: () -> Unit){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
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