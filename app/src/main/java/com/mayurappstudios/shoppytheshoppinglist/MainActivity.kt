package com.mayurappstudios.shoppytheshoppinglist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import com.mayurappstudios.shoppytheshoppinglist.ui.theme.ShoppyTheShoppingListTheme
//ddd
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShoppyTheShoppingListTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Navigation(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
@Composable
fun Navigation( modifier: Modifier =Modifier){
    val navController = rememberNavController()
    val viewModel : LocationViewModel = viewModel()
    val context = LocalContext.current
    val locationUtils = LocationUtils(context)
    NavHost(navController, startDestination="shoppinglistscreen"){
        composable("shoppinglistscreen"){
            ShoppingListApp(modifier ,
                locationUtils ,
                navController ,
                context ,
                viewModel,
                address = viewModel.address.value.firstOrNull()?.formatted_address ?: "No Address Found"
                )
        }
        dialog("locationselectionscreen"){
           backstackEntry -> viewModel.location.value?.let {locationData->
               LocationSelectionScreen(modifier, locationData, onLocationSelected = {
                   viewModel.fetchAddress("${locationData.latitude},${locationData.longitude}")
                   navController.popBackStack()
               })
        }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ShoppingListScreenPreview() {
    ShoppyTheShoppingListTheme {
//        ShoppingListApp()
    }
}