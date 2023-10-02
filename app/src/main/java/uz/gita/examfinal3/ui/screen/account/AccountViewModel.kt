package uz.gita.examfinal3.ui.screen.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.examfinal3.domain.repository.AuthRepository
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val appRepository: AuthRepository,
    private val direction: AccountDirection
) : AccountContract.ViewModel, ViewModel() {

    override val container =
        container<AccountContract.UiState, AccountContract.SideEffect>(AccountContract.UiState.Default)


    override fun eventDispatcher(intent: AccountContract.Intent) {
        when (intent) {
            is AccountContract.Intent.OpenSignIn -> {
                viewModelScope.launch {
                    direction.openSignInScreen()
                }
            }
            is AccountContract.Intent.OpenSignUp -> {
                viewModelScope.launch {
                    direction.openSignUpScreen()
                }
            }
            is AccountContract.Intent.Back -> {
                viewModelScope.launch {
                    direction.backEnterScreen()
                }
            }
        }
    }


}