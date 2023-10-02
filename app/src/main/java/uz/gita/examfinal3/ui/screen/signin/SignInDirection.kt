package uz.gita.examfinal3.ui.screen.signin

import uz.gita.examfinal3.ui.screen.home.HomeScreen
import uz.gita.examfinal3.ui.screen.signup.SignUpScreen
import uz.gita.examfinal3.util.navigation.AppNavigator
import javax.inject.Inject

interface SignInDirection {
    suspend fun openHomeScreen()
    suspend fun backAccountScreen()
}

class SignInDirectionImpl @Inject constructor(
    private val navigator: AppNavigator
): SignInDirection{
    override suspend fun openHomeScreen() {
        navigator.navigateTo(HomeScreen())
    }

    override suspend fun backAccountScreen() {
        navigator.pop()
    }

}