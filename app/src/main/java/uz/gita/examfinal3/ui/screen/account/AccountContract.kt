package uz.gita.examfinal3.ui.screen.account

import org.orbitmvi.orbit.ContainerHost

interface AccountContract {
    interface ViewModel: ContainerHost<UiState, SideEffect> {
        fun eventDispatcher(intent: Intent)
    }

    sealed interface UiState {
        object Default: UiState
    }

    sealed interface Intent {
        object OpenSignIn: Intent
        object OpenSignUp: Intent
        object Back: Intent
    }

    sealed interface SideEffect
}