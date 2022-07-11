package com.example.marvelcompose.ui.screens.charactersdetail

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalUriHandler
import com.example.marvelcompose.data.entities.Url

@ExperimentalMaterialApi
@Composable
fun AppBarOverflowMenu(urls: List<Url>) {
    if (urls.isEmpty()) return

    var showMenu by remember { mutableStateOf(false) }
    val uriHandler = LocalUriHandler.current

    IconButton(onClick = { showMenu = !showMenu }) {
        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = "More Actions"
        )
        DropdownMenu(
            expanded = showMenu,
            onDismissRequest = { showMenu = false }
        ) {
            urls.forEach {
                DropdownMenuItem(onClick = {
                    uriHandler.openUri(it.destination)
                    showMenu = false
                }) {
                    ListItem(text = { Text(it.type) })
                }
            }
        }
    }
}