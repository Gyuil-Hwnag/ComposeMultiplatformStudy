package com.example.cmpstudy.app

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.cmpstudy.bookpedia.app.BookPediaScreen
import com.example.cmpstudy.date.DateTimeScreen
import com.example.cmpstudy.file.FilePickerScreenRoot
import com.example.cmpstudy.location.LocationScreen
import com.example.cmpstudy.login.LoginScreenRoot
import com.example.cmpstudy.map.MapScreenRoot
import com.example.cmpstudy.permission.PermissionScreenRoot
import com.example.cmpstudy.start.StartScreen
import com.example.cmpstudy.webview.WebViewScreenRoot

@Composable
fun AppRoot() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppRoutes.AppGraph
    ) {
        navigation<AppRoutes.AppGraph>(
            startDestination = AppRoutes.Start
        ) {
            composable<AppRoutes.Start> {
                StartScreen { route -> navController.navigate(route) }
            }

            composable<AppRoutes.BookPedia> {
                BookPediaScreen()
            }

            composable<AppRoutes.DateTime> {
                DateTimeScreen()
            }

            composable<AppRoutes.FilePicker> {
                FilePickerScreenRoot()
            }

            composable<AppRoutes.Location> {
                LocationScreen()
            }

            composable<AppRoutes.Login> {
                LoginScreenRoot()
            }

            composable<AppRoutes.Map> {
                MapScreenRoot()
            }

            composable<AppRoutes.Permission> {
                PermissionScreenRoot()
            }

            composable<AppRoutes.WebView> {
                WebViewScreenRoot()
            }
        }
    }
}
