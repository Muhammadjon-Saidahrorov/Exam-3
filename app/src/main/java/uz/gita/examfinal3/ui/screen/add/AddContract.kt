package uz.gita.examfinal3.ui.screen.add

import org.orbitmvi.orbit.ContainerHost
import uz.gita.examfinal3.data.model.CategoryData
import uz.gita.examfinal3.data.model.ProductData

interface AddContract {
    interface ViewModel : ContainerHost<UiState, SideEffect> {
        fun eventDispatcher(intent: Intent)
    }

    sealed interface UiState {
        object Default : UiState
        data class LoadData(val categoryList: List<String>) : UiState
    }

    sealed interface Intent {
        data class AddProduct(val productData: ProductData) : Intent
        data class AddCategory(val categoryData: CategoryData) : Intent
        object Back: Intent
    }

    sealed interface SideEffect{
        data class HasError(val message: String): SideEffect
        data class HasSuccess(val message: String): SideEffect
    }
}