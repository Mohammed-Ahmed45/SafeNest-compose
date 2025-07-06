package com.mohamed.safenest.ui.screens.alertscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mohamed.safenest.data.api.AlertsViewModel

@Composable
fun AlertsScreen(
    modifier: Modifier = Modifier,
    viewModel: AlertsViewModel = hiltViewModel(),
    tabIndex: Int = 0,
    navController: NavController,
) {

    val tabList = listOf("Fire", "Water", "Gas")
    var selectedTabIndex by remember { mutableIntStateOf(tabIndex) }
    LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.initializeAlertCounts()
    }

    LaunchedEffect(tabIndex) {
        selectedTabIndex = tabIndex
    }

    Column(modifier = modifier.fillMaxSize()) {
        TabRow(
            modifier = modifier.padding(start = 10.dp),
            selectedTabIndex = selectedTabIndex,
        ) {
            tabList.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = {
                        Text(
                            text = title,
                            fontSize = 16.sp
                        )
                    }
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
        ) {
            when (selectedTabIndex) {
                0 -> FireTabContent(viewModel = viewModel)
                1 -> WaterTabContent(viewModel = viewModel)
                2 -> GasTabContent(viewModel = viewModel)
            }
        }
    }
}
