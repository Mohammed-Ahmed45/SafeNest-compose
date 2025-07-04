package com.mohamed.safenest.ui.screens.onboarding

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun OnboardingPageContent(
    page: OnboardingPage,
    isVisible: Boolean
) {
    var contentVisible by remember { mutableStateOf(false) }

    LaunchedEffect(isVisible) {
        if (isVisible) {
            delay(200)
            contentVisible = true
        } else {
            contentVisible = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Image with animation (removed circular background)
        AnimatedVisibility(
            visible = contentVisible,
            enter = fadeIn(animationSpec = tween(600)) +
                    slideInVertically(
                        initialOffsetY = { it / 2 },
                        animationSpec = tween(600)
                    ),
            exit = fadeOut(animationSpec = tween(300)) +
                    slideOutVertically(
                        targetOffsetY = { -it / 2 },
                        animationSpec = tween(300)
                    )
        ) {
            Image(
                painter = painterResource(id = page.imageRes),
                contentDescription = page.title,
                modifier = Modifier
                    .size(width = 415.dp, height = 310.dp)
                    .scale(
                        animateFloatAsState(
                            targetValue = if (contentVisible) 1f else 0.8f,
                            animationSpec = tween(600)
                        ).value
                    ),
                contentScale = ContentScale.Fit
            )
        }

        Spacer(modifier = Modifier.height(48.dp))

        // Title with animation
        AnimatedVisibility(
            visible = contentVisible,
            enter = fadeIn(animationSpec = tween(800, delayMillis = 200)) +
                    slideInVertically(
                        initialOffsetY = { it / 3 },
                        animationSpec = tween(800, delayMillis = 200)
                    ),
            exit = fadeOut(animationSpec = tween(300))
        ) {
            Text(
                text = page.title,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Description with animation
        AnimatedVisibility(
            visible = contentVisible,
            enter = fadeIn(animationSpec = tween(1000, delayMillis = 400)) +
                    slideInVertically(
                        initialOffsetY = { it / 4 },
                        animationSpec = tween(1000, delayMillis = 400)
                    ),
            exit = fadeOut(animationSpec = tween(300))
        ) {
            Text(
                text = page.description,
                fontSize = 16.sp,
                color = Color.Black.copy(alpha = 0.9f),
                textAlign = TextAlign.Center,
                lineHeight = 24.sp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}
