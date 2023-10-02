package uz.gita.examfinal3.ui.screen.signup

import org.orbitmvi.orbit.ContainerHost

class SignUpContract {
    interface ViewModel: ContainerHost<UiState, SideEffect> {
        fun eventDispatcher(intent: Intent)
    }

    sealed interface UiState {
        object Default: UiState
    }

    sealed interface Intent {
        data class SignUpUser(val email: String, val password: String): Intent
        object Back: Intent
    }

    sealed interface SideEffect {
        data class HasError(val message: String): SideEffect
    }

}