package uz.gita.examfinal3.ui.screen.home.shop

import org.orbitmvi.orbit.ContainerHost
import uz.gita.examfinal3.data.model.CategoryData
import uz.gita.examfinal3.data.model.ProductData

interface ShopContract {
    sealed interface ViewModel : ContainerHost<UiState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    sealed interface UiState {
        object LoadingData : UiState
        data class Data(val categories: List<String>, val products: List<ProductData>) : UiState

    }

    sealed interface SideEffect {

    }

    sealed interface Intent {
        object LoadData : Intent
    }
}