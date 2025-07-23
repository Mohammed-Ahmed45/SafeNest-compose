package com.mohamed.safenest.ui.screens.splash

    import androidx.compose.foundation.Image
    import androidx.compose.foundation.layout.Arrangement
    import androidx.compose.foundation.layout.Column
    import androidx.compose.foundation.layout.fillMaxSize
    import androidx.compose.foundation.layout.size
    import androidx.compose.runtime.Composable
    import androidx.compose.runtime.LaunchedEffect
    import androidx.compose.ui.Alignment
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.res.painterResource
    import androidx.compose.ui.tooling.preview.Preview
    import androidx.compose.ui.unit.dp
    import androidx.navigation.NavController
    import com.mohamed.safenest.R
    import com.mohamed.safenest.ui.navigation.Route
    import kotlinx.coroutines.delay

    @Composable
    fun SplashScreen(navController: NavController,modifier: Modifier = Modifier) {


        LaunchedEffect(Unit) {
            delay(2000)
            navController.navigate(Route.ONBOARDING_SCREEN){
                popUpTo(Route.SPLASH){
                    inclusive=true
                }
            }
        }
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

                Image(
                    painter = painterResource(id = R.drawable.img_splash),
                    contentDescription = "",
                    modifier=modifier.size(208.dp,208.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.img_safenest),
                    contentDescription = "",
                    modifier=modifier.size(156.dp,57.dp)
                )


        }
    }

    @Preview(showSystemUi = true, showBackground = true)
    @Composable
    private fun Preview() {
    //    SplashScreen()
    }