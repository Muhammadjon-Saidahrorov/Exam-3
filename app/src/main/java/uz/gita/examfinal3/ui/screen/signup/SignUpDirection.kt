package uz.gita.examfinal3.ui.screen.signup

import uz.gita.examfinal3.ui.screen.home.HomeScreen
import uz.gita.examfinal3.ui.screen.signin.SignInDirection
import uz.gita.examfinal3.util.navigation.AppNavigator
import javax.inject.Inject

interface SignUpDirection {
    suspend fun openHomeScreen()
    suspend fun backSignInScreen()
}

class SignUpDirectionImpl @Inject constructor(
    private val navigator: AppNavigator
): SignUpDirection {
    override suspend fun openHomeScreen() {
        navigator.navigateTo(HomeScreen())
    }

    override suspend fun backSignInScreen() {
        navigator.pop()
    }

}