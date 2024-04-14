package com.mtuci.akino.details.reviews

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mtuci.akino.R
import com.mtuci.akino.details.data.Review
import com.mtuci.akino.ui.theme.PrimaryColor
import com.mtuci.akino.ui.theme.TextLightColor

@Composable
fun ReviewContent(review: Review){
    Column(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 15.dp)
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = review.author,
            color = PrimaryColor,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp
        )
        Text(
            text = review.date.substring(0, 10),
            color = TextLightColor,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = review.type ?: stringResource(R.string.empty),
            color = TextLightColor,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(R.string.review),
            color = PrimaryColor,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp
        )
        Text(
            text = review.review ?: stringResource(R.string.empty),
            color = PrimaryColor,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            maxLines = 5,
            overflow = TextOverflow.Ellipsis
        )
    }
}