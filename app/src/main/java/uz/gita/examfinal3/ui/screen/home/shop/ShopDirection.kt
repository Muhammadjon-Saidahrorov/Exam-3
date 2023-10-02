package uz.gita.examfinal3.ui.screen.home.shop

import uz.gita.examfinal3.util.navigation.AppNavigator
import javax.inject.Inject

interface ShopDirection {
}

class ShopDirectionImpl @Inject constructor(
    private val navigator: AppNavigator
) : ShopDirection {

}