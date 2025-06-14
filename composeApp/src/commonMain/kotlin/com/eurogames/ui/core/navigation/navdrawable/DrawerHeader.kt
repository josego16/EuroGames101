package com.eurogames.ui.core.navigation.navdrawable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DrawerHeader(
    fullName: String? = null,
    email: String? = null,
    onCloseClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onCloseClick) {
                Icon(Icons.AutoMirrored.Default.ArrowBack, contentDescription = "Close Drawer")
            }
        }
        if (fullName != null) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = fullName, modifier = Modifier.padding(top = 8.dp))
                Text(text = email ?: "example@gmail.com", modifier = Modifier.padding(top = 8.dp))
            }
        } else {
            Icon(imageVector = Icons.Default.Image, contentDescription = "Default")
        }
    }
}