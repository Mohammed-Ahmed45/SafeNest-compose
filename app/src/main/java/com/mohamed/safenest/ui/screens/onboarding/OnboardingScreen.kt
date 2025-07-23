@file:OptIn(ExperimentalFoundationApi::class)

package com.mohamed.safenest.ui.screens.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mohamed.safenest.R
import com.mohamed.safenest.ui.navigation.Route
import kotlinx.coroutines.launch

data class OnboardingPage(
    val title: String,
    val description: String,
    val imageRes: Int,
)

@Composable
fun OnboardingScreen(navController: NavController? = null) {
    val pages = listOf(
        OnboardingPage(
            title = "Welcome to SafeNest",
            description = "Your safety is our priority. Stay connected and secure with our comprehensive safety features",
            imageRes = R.drawable.img_welcome,

            ),
        OnboardingPage(
            title = "Emergency Contacts",
            description = "Call emergency services fast with just one tap and quick support when every second count",
            imageRes = R.drawable.img_emergancy

        ),
        OnboardingPage(
            title = "Smart Alerts",
            description = "Instant notifications for fire, gas or water problems that help you take action before things get worse",
            imageRes = R.drawable.img_security,
        )
    )

    val pagerState = rememberPagerState(pageCount = { pages.size })
    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Color.White
            )
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(60.dp))

            PageIndicator(
                pageCount = pages.size,
                currentPage = pagerState.currentPage,
                modifier = Modifier.padding(horizontal = 32.dp)
            )

            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) { page ->
                OnboardingPageContent(
                    page = pages[page],
                    isVisible = page == pagerState.currentPage
                )
            }

            BottomNavigation(
                currentPage = pagerState.currentPage,
                totalPages = pages.size,
                onNextClick = {
                    scope.launch {
                        if (pagerState.currentPage < pages.size - 1) {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    }
                },
                onSkipClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(pages.size - 1)
                    }
                },
                onGetStartedClick = {
                    navController?.navigate(Route.MAIN_SCREEN) {
                        popUpTo(Route.ONBOARDING_SCREEN) { inclusive = true }
                    }
                }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun OnboardingPreview() {
    MaterialTheme {
        OnboardingScreen()
    }
}