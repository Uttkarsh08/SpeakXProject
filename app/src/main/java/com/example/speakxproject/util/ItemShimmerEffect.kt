package com.example.speakxproject.util

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.speakxproject.R

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
fun ItemShimmerEffect(modifier: Modifier = Modifier){
    Column(
        modifier = Modifier.fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ){

        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(vertical = 32.dp)
            ,
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.size(height = 20.dp, width = 40.dp)
                    .shadow(elevation = 2.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .shimmerEffect(),

            )
            Box(
                modifier = Modifier.size(height = 20.dp, width = 100.dp)
                    .shadow(elevation = 2.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .shimmerEffect()
            )
        }

        Box(
            modifier = Modifier.fillMaxWidth()
                .shimmerEffect()
                .size(height = 2.dp, width = 2.dp)
        )


    }
}
