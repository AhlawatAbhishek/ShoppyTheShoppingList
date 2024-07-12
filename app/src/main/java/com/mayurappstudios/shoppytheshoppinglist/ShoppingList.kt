package com.mayurappstudios.shoppytheshoppinglist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

data class ShoppingItem(
    val id: Int,
    var name: String,
    var quantity: Int,
    val isEditing: Boolean = false
)

@Composable
fun ShoppingListScreen() {
    var shoppingItems = remember { mutableStateListOf<ShoppingItem>() }
    // Rest of your Composable function...
    var showDialog by remember {
        mutableStateOf(false)
    }
    var itemName by remember {
        mutableStateOf("")
    }
    var itemQuantity by remember {
        mutableStateOf("")
    }
    Column {
        var verticalArrangement = Arrangement.spacedBy(8.dp)
        Button(
            onClick = { showDialog = true },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Add Item")
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(shoppingItems) { item ->
                Column(modifier = Modifier.padding(8.dp)) {
                    Text(text = "Item Name: ${item.name}", style = MaterialTheme.typography.bodyLarge)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "Item Quantity: ${item.quantity}", style = MaterialTheme.typography.bodyMedium)


                }
            }
        }
    }
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                Button(onClick = {
                    showDialog = false
                    shoppingItems.add(
                        ShoppingItem(
                            shoppingItems.size,
                            itemName,
                            itemQuantity.toInt()
                        )
                    )
                }) {
                    Text(text = "Add Item")
                }
            },
            dismissButton = {
                Button(onClick = { showDialog = false }) {
                    Text(text = "Cancel")

                }
            },
            text = {
                Column {
                    OutlinedTextField(
                        value = itemName,
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        onValueChange = { itemName = it },
                        label = { Text(text = "Shopping Item Name") })
                    Spacer(modifier = Modifier.height(16.dp))
                    OutlinedTextField(
                        value = itemQuantity,
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        onValueChange = { itemQuantity = it },
                        label = { Text(text = "Shopping Item Quantity") })
                }
            },
            title = {
                Text(
                    text = "Add Shopping Item",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            })
    }
}