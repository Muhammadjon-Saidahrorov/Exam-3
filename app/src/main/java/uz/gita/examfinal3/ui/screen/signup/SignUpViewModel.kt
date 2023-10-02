package uz.gita.examfinal3.ui.screen.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.examfinal3.domain.repository.AuthRepository
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val appRepository: AuthRepository,
    private val direction: SignUpDirection
) : SignUpContract.ViewModel, ViewModel() {

    override val container =
        container<SignUpContract.UiState, SignUpContract.SideEffect>(SignUpContract.UiState.Default)


    override fun eventDispatcher(intent: SignUpContract.Intent) {
        when (intent) {
            is SignUpContract.Intent.SignUpUser -> {
                appRepository.createUser(intent.email, intent.password).onEach {
                    it.onSuccess {
                        direction.openHomeScreen()
                    }
                    it.onFailure {
                        intent {
                            postSideEffect(
                                SignUpContract.SideEffect.HasError(
                                    it.message ?: "Exception occurred!"
                                )
                            )
                        }
                    }
                }.launchIn(viewModelScope)
            }
            is SignUpContract.Intent.Back -> {
                viewModelScope.launch {
                    direction.backSignInScreen()
                }
            }
        }
    }


}