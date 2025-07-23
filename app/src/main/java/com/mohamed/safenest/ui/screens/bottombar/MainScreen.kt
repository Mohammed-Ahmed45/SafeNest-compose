package com.mohamed.safenest.ui.screens.bottombar

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Contacts
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mohamed.safenest.ui.screens.alertscreen.AlertsScreen
import com.mohamed.safenest.ui.screens.contacts.ContactsScreen
import com.mohamed.safenest.ui.screens.help.HelpScreen
import com.mohamed.safenest.ui.screens.home.HomeScreen
import com.mohamed.safenest.ui.screens.profile.ProfileScreen


data class NavItem(
    val title: String,
    val icon: ImageVector,
)

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navController: NavController? = null,
) {

    val navItemList = listOf(
        NavItem("Home", icon = Icons.Default.Home),
        NavItem("Alerts", icon = Icons.Default.Warning),
        NavItem("Help", icon = Icons.Default.Help),
        NavItem("Contacts", icon = Icons.Default.Contacts),
        NavItem("Profile", icon = Icons.Default.Person)
    )

    var selectedIndex by remember { mutableIntStateOf(0) }

    Scaffold(
        modifier = modifier
            .padding(WindowInsets.safeDrawing.asPaddingValues())
            .fillMaxSize(),
        bottomBar = {
            NavigationBar {
                navItemList.forEachIndexed { index, navItem ->
                    NavigationBarItem(
                        selected = selectedIndex == index,
                        onClick = {
                            selectedIndex = index
                        },
                        icon = {
                            Icon(imageVector = navItem.icon, contentDescription = "")
                        },
                        label = { Text(text = navItem.title) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color.Red,
                            unselectedIconColor = Color.Black,
                            selectedTextColor = Color.Red,
                            unselectedTextColor = Color.Gray,
                            indicatorColor = Color.Transparent
                        )

                    )
                }
            }
        }
    )
    { innerPadding ->
        ContentScreen(
            modifier = modifier
                .padding(innerPadding),
            selectedIndex,
            navController = navController!!
        )
    }
}


@Composable
fun ContentScreen(
    modifier: Modifier = Modifier,
    selectedIndex: Int,
    navController: NavController,
) {
    when (selectedIndex) {
        0 -> HomeScreen(navController = navController)
        1 -> AlertsScreen(navController = navController)
        2 -> HelpScreen()
        3 -> ContactsScreen()
        4 -> ProfileScreen()
    }

}


@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun PrevBottomBar() {
    val navController = rememberNavController()
    MainScreen(navController = navController)
}