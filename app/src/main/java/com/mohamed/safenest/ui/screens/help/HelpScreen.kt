package com.mohamed.safenest.ui.screens.help

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mohamed.safenest.ui.theme.colors


@Composable
fun HelpScreen(modifier: Modifier = Modifier) {

    val helpList = listOf(
        HelpItems("1- Assess the Situation: Quickly determine the nature of the danger. Is it a fire, gas leak, intruder, or medical emergency?"),
        HelpItems("2- Call for Help: Dial emergency services immediately. In Egypt, the emergency number is 122 for police, 123 for ambulance, and 180 for fire"),
        HelpItems("3- Evacuate if Necessary: If there's a fire or gas leak, evacuate the premises immediately. Do not use elevators"),
        HelpItems("4- Secure the Area: If there's an intruder, lock yourself in a safe room and call the police"),
        HelpItems("5- First Aid: If someone is injured, provide first aid while waiting for medical help."),
        HelpItems("6- Stay Informed: Keep a battery-powered radio or phone to receive updates and instructions from authorities."),
        HelpItems("7- Emergency Kit: Have an emergency kit ready with essentials like water, food, flashlight, batteries, and a first aid kit."),
    )
    Column {
        Card(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = colors.Blue)
        ) {
            Text(
                text = "Steps when there is danger",
                fontSize = 18.sp,
                color = Color.White,
                modifier = Modifier.padding(10.dp)
            )
        }
        LazyColumn {
            items(helpList) { items ->
                HelpContact(items = items)
            }
        }
    }
}

@Composable
fun HelpContact(items: HelpItems) {

    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = items.title!!,
            modifier = Modifier.padding(10.dp),
            fontSize = 14.sp
        )
    }

}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun HelpPrev() {
    HelpScreen()
}