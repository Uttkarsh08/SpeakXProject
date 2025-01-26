package com.example.speakxproject.util

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.speakxproject.R
import androidx.compose.ui.graphics.Color

fun Modifier.shimmerEffect() = composed {
    val transition = rememberInfiniteTransition(label = "")
    val alpha = transition.animateFloat(
        initialValue = 0.2f, targetValue = 0.9f, animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    ).value
    background(color = colorResource(id = R.color.shimmer).copy(alpha = alpha))
}


@Composable
fun ItemShimmerEffect(
    modifier: Modifier = Modifier,
    color: Color
){
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(horizontal = 4.dp)
            .wrapContentSize(Alignment.Center)
            .background(color = MaterialTheme.colorScheme.background)
            .border(2.dp, color = colorResource(R.color.shimmer), shape = RoundedCornerShape(26.dp))
    ){

        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(vertical = 32.dp)
            ,
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.size(height = 40.dp, width = 60.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .shimmerEffect(),

            )
            Box(
                modifier = Modifier.size(height = 60.dp, width = 110.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .shimmerEffect()
            )
        }


    }
}
