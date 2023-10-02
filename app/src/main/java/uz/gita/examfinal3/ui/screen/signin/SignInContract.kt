package uz.gita.examfinal3.ui.screen.signin

import org.orbitmvi.orbit.ContainerHost

class SignInContract {
    interface ViewModel: ContainerHost<UiState, SideEffect> {
        fun eventDispatcher(intent: Intent)
    }

    sealed interface UiState {
        object Default: UiState
    }

    sealed interface Intent {
        data class SignInUser(val email: String, val password: String): Intent
        object Back: Intent
    }

    sealed interface SideEffect {
        data class HasError(val message: String): SideEffect
    }

}