package uz.gita.examfinal3.util.navigation

import kotlinx.coroutines.flow.SharedFlow

interface NavigationHandler {
    val navigatorState: SharedFlow<NavigationArgs>
}