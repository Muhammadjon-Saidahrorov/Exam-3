package uz.gita.examfinal3.ui.screen.home.shopping

import org.orbitmvi.orbit.ContainerHost
import uz.gita.examfinal3.data.model.ProductData

interface ShoppingContract {
    interface ViewModel : ContainerHost<UiState, SideEffect> {
        fun eventDispatcher(intent: Intent)
    }

    sealed interface UiState {
        object Default : UiState
        data class LoadData(val products: List<ProductData>) : UiState
    }

    sealed interface Intent {
        object OpenAdd : Intent
        object LoadData : Intent
    }

    sealed interface SideEffect
}