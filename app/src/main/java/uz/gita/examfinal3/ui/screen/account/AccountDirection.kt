package uz.gita.examfinal3.ui.screen.account

import uz.gita.examfinal3.ui.screen.signin.SignInScreen
import uz.gita.examfinal3.ui.screen.signup.SignUpScreen
import uz.gita.examfinal3.util.navigation.AppNavigator
import javax.inject.Inject

interface AccountDirection {
    suspend fun openSignInScreen()
    suspend fun openSignUpScreen()
    suspend fun backEnterScreen()
}

class AccountDirectionImpl @Inject constructor(
    private val navigator: AppNavigator
): AccountDirection{
    override suspend fun openSignInScreen() {
        navigator.navigateTo(SignInScreen())
    }

    override suspend fun openSignUpScreen() {
        navigator.navigateTo(SignUpScreen())
    }

    override suspend fun backEnterScreen() {
        navigator.pop()
    }

}