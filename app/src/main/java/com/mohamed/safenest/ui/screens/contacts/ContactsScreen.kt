package com.mohamed.safenest.ui.screens.contacts

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mohamed.safenest.R
import com.mohamed.safenest.ui.theme.colors

data class ItemsList(
    val image: Int? = null,
    val title: String? = null,
    val phoneNumber: String? = null,
)

@Composable
fun ContactsScreen() {

    val contactsList = listOf(
        ItemsList(
            image = R.drawable.ic_emergancy,
            title = "Fire Department",
            phoneNumber = "180"
        ),
        ItemsList(
            image = R.drawable.ic_emergancy,
            title = "Police Emergency",
            phoneNumber = "122"
        ),
        ItemsList(
            image = R.drawable.ic_emergancy,
            title = "Medical Emergency",
            phoneNumber = "123"
        )
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
            items(contactsList) { items ->
                ContactsItems(items)
            }
        }
    }
}

@Composable
fun ContactsItems(items: ItemsList, modifier: Modifier = Modifier) {
    val context = LocalContext.current

    Column {
        Card(
            modifier = modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = modifier.padding(20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    Image(
                        painter = painterResource(id = items.image!!),
                        contentDescription = "",
                        modifier = Modifier.size(40.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(
                            text = items.title!!,
                            fontSize = 16.sp
                        )
                        items.phoneNumber?.let { phoneNumber ->
                            Text(
                                text = phoneNumber,
                                fontSize = 14.sp,
                                color = Color.Gray
                            )
                        }
                    }
                }

                // أيكون الهاتف للاتصال
                IconButton(
                    onClick = {
                        items.phoneNumber?.let { phoneNumber ->
                            makePhoneCall(context, phoneNumber)
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Phone,
                        contentDescription = "Call",
                        tint = colors.Blue,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}

// Function للاتصال
private fun makePhoneCall(context: Context, phoneNumber: String) {
    val intent = Intent(Intent.ACTION_CALL).apply {
        data = Uri.parse("tel:$phoneNumber")
    }

    try {
        context.startActivity(intent)
    } catch (e: Exception) {
        // في حالة فشل الاتصال المباشر، افتح الداتلر
        val dialIntent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phoneNumber")
        }
        context.startActivity(dialIntent)
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PrevContactsScreen() {
    ContactsScreen()
}