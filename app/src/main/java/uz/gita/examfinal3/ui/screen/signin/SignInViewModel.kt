package uz.gita.examfinal3.ui.screen.signin

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
class SignInViewModel @Inject constructor(
    private val appRepository: AuthRepository,
    private val direction: SignInDirection
) : SignInContract.ViewModel, ViewModel() {

    override val container =
        container<SignInContract.UiState, SignInContract.SideEffect>(SignInContract.UiState.Default)


    override fun eventDispatcher(intent: SignInContract.Intent) {
        when (intent) {
            is SignInContract.Intent.SignInUser -> {
                appRepository.signIn(intent.email, intent.password).onEach {
                    it.onSuccess {
                        direction.openHomeScreen()
                    }
                    it.onFailure {
                        intent {
                            postSideEffect(
                                SignInContract.SideEffect.HasError(
                                    it.message ?: "Exception occurred!"
                                )
                            )
                        }
                    }
                }.launchIn(viewModelScope)
            }
            is SignInContract.Intent.Back -> {
                viewModelScope.launch {
                    direction.backAccountScreen()
                }
            }
        }
    }


}