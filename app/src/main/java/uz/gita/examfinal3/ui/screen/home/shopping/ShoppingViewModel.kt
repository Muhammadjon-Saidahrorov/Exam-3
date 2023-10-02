package uz.gita.examfinal3.ui.screen.home.shopping

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.examfinal3.data.model.ProductData
import uz.gita.examfinal3.domain.repository.AppRepository
import uz.gita.examfinal3.domain.repository.AuthRepository
import javax.inject.Inject

@HiltViewModel
class ShoppingViewModel @Inject constructor(
    private val appRepository: AppRepository,
    private val direction: ShoppingDirection
) : ShoppingContract.ViewModel, ViewModel() {

    override val container =
        container<ShoppingContract.UiState, ShoppingContract.SideEffect>(ShoppingContract.UiState.Default)



    override fun eventDispatcher(intent: ShoppingContract.Intent) {
        when (intent) {
            is ShoppingContract.Intent.OpenAdd -> {
                viewModelScope.launch {
                    direction.openAddScreen()
                }
            }
            is ShoppingContract.Intent.LoadData -> {
                val products = arrayListOf<ProductData>()

                appRepository.getAllProductsByUserId().onEach { result ->
                    result.onSuccess {
                        products.addAll(it)
                    }
                    intent { reduce { ShoppingContract.UiState.LoadData(products) } }
                    result.onFailure {

                    }
                }.launchIn(viewModelScope)
            }
        }
    }


}