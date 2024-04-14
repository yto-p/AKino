package com.mtuci.akino.main

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.mtuci.akino.R
import com.mtuci.akino.main.data.Movie
import com.mtuci.akino.main.movies.MovieContent
import com.mtuci.akino.main.search.MovieSearchContent
import com.mtuci.akino.ui.theme.BackgroundLightColor
import com.mtuci.akino.ui.theme.PrimaryColor
import com.mtuci.akino.ui.theme.SecondaryColor
import com.mtuci.akino.ui.theme.TextLightColor
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent(
    pagingData: LazyPagingItems<Movie>,
    openDetails: (id: Int) -> Unit,
    searchText: String,
    movieSearchList: List<Movie>,
    isSearching: Boolean,
    yearText: String,
    countryText: String,
    ageText: String,
    onYearTextChange: (String) -> Unit,
    onCountryTextChange: (String) -> Unit,
    onAgeTextChange: (String) -> Unit,
    onSearchTextChange: (String) -> Unit,
    onApplyClick: () -> Unit,
    onSearchClick: () -> Unit,
    onBackClick: () -> Unit,
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .padding(top = 32.dp)
            .padding(horizontal = 16.dp)
    ) {
        val sheetState = rememberModalBottomSheetState()
        val scope = rememberCoroutineScope()
        var showBottomSheet by remember { mutableStateOf(false) }
        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = {
                    showBottomSheet = false
                },
                sheetState = sheetState,
                containerColor = Color.White
                ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp)
                        .padding(bottom = 15.dp)
                ) {
                    Text(
                        text = stringResource(R.string.filters),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        color = PrimaryColor,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    BasicTextField(
                        value = yearText,
                        onValueChange = onYearTextChange,
                        singleLine = true,
                        textStyle = TextStyle(color = PrimaryColor, fontSize = 16.sp, fontWeight = FontWeight.Normal),
                        decorationBox = { textField ->
                            Box(
                                contentAlignment = Alignment.CenterStart,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(18.dp))
                                    .background(BackgroundLightColor)
                                    .padding(
                                        horizontal = 14.dp,
                                        vertical = 16.dp
                                    )
                            ) {
                                Spacer(Modifier.width(6.dp))
                                if (yearText.isEmpty()) {
                                    Text(
                                        text = stringResource(R.string.year),
                                        fontSize = 16.sp,
                                        color = TextLightColor
                                    )
                                }
                                textField()
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(18.dp))
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    BasicTextField(
                        value = countryText,
                        onValueChange = onCountryTextChange,
                        singleLine = true,
                        textStyle = TextStyle(color = PrimaryColor, fontSize = 16.sp, fontWeight = FontWeight.Normal),
                        decorationBox = { textField ->
                            Box(
                                contentAlignment = Alignment.CenterStart,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(18.dp))
                                    .background(BackgroundLightColor)
                                    .padding(
                                        horizontal = 14.dp,
                                        vertical = 16.dp
                                    )
                            ) {
                                Spacer(Modifier.width(6.dp))
                                if (countryText.isEmpty()) {
                                    Text(
                                        text = stringResource(R.string.country_filter),
                                        fontSize = 16.sp,
                                        color = TextLightColor
                                    )
                                }
                                textField()
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(18.dp))
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    BasicTextField(
                        value = ageText,
                        onValueChange = onAgeTextChange,
                        singleLine = true,
                        textStyle = TextStyle(color = PrimaryColor, fontSize = 16.sp, fontWeight = FontWeight.Normal),
                        decorationBox = { textField ->
                            Box(
                                contentAlignment = Alignment.CenterStart,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(18.dp))
                                    .background(BackgroundLightColor)
                                    .padding(
                                        horizontal = 14.dp,
                                        vertical = 16.dp
                                    )
                            ) {
                                Spacer(Modifier.width(6.dp))
                                if (ageText.isEmpty()) {
                                    Text(
                                        text = stringResource(R.string.age_rating),
                                        fontSize = 16.sp,
                                        color = TextLightColor
                                    )
                                }
                                textField()
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(18.dp))
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Button(
                        colors = ButtonDefaults.buttonColors(containerColor = SecondaryColor),
                        onClick = {
                            onApplyClick()
                            scope.launch { sheetState.hide() }.invokeOnCompletion {
                                if (!sheetState.isVisible) {
                                    showBottomSheet = false
                                }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = stringResource(R.string.apply),
                            fontSize = 16.sp,
                            color = Color.White,
                            modifier = Modifier.padding(vertical = 4.dp)
                        )
                    }
                }
            }
        }
        if (isSearching){
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_back),
                    contentDescription = "",
                    tint = PrimaryColor,
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable(onClick = onBackClick)
                        .padding(vertical = 6.dp, horizontal = 10.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                val focusManager = LocalFocusManager.current
                BasicTextField(
                    value = searchText,
                    onValueChange = onSearchTextChange,
                    singleLine = true,
                    textStyle = TextStyle(color = PrimaryColor, fontSize = 16.sp, fontWeight = FontWeight.Normal),
                    keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done, keyboardType = KeyboardType.Text),
                    decorationBox = { textField ->
                        Box(
                            contentAlignment = Alignment.CenterStart,
                            modifier = Modifier
                                .clip(RoundedCornerShape(18.dp))
                                .background(BackgroundLightColor)
                                .padding(
                                    horizontal = 14.dp,
                                    vertical = 16.dp
                                )
                        ) {
                            Spacer(Modifier.width(6.dp))
                            if (searchText.isEmpty()) {
                                Text(
                                    text = stringResource(R.string.search),
                                    fontSize = 16.sp,
                                    color = TextLightColor
                                )
                            }
                            textField()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(18.dp))
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            LazyColumn(Modifier.fillMaxSize()){
                items(movieSearchList.size){ index ->
                    val item = movieSearchList[index]
                    MovieSearchContent(
                        movieSearch = item,
                        openDetails = { openDetails(item.id) }
                    )
                }
            }
        } else {
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
                        .clickable(onClick = { showBottomSheet = true })
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
            if (pagingData.itemCount != 0){
                LazyVerticalGrid(
                    modifier = Modifier.fillMaxSize(),
                    columns = GridCells.Fixed(2)){
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
            } else {
                LazyVerticalGrid(
                    modifier = Modifier.fillMaxSize(),
                    columns = GridCells.Fixed(2)){
                    if (pagingData.loadState.refresh is LoadState.Loading){
                        item {
                            Box(modifier = Modifier
                                .fillMaxSize()
                                .padding(30.dp)){
                                CircularProgressIndicator(
                                    color = SecondaryColor,
                                    modifier = Modifier
                                        .align(Alignment.Center))
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