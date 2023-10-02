package uz.gita.examfinal3.ui.screen.home.shopping

import uz.gita.examfinal3.ui.screen.add.AddScreen
import uz.gita.examfinal3.util.navigation.AppNavigator
import javax.inject.Inject


interface ShoppingDirection {
    suspend fun openAddScreen()
}

class ShoppingDirectionImpl @Inject constructor(
    private val navigator: AppNavigator
) : ShoppingDirection {
    override suspend fun openAddScreen() {
        navigator.navigateTo(AddScreen())
    }
}