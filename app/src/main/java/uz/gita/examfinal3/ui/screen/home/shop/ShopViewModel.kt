package uz.gita.examfinal3.ui.screen.home.shop

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.examfinal3.data.model.ProductData
import uz.gita.examfinal3.domain.repository.AppRepository
import uz.gita.examfinal3.util.myLog
import javax.inject.Inject

@HiltViewModel
class ShopViewModel @Inject constructor(
    private val appRepository: AppRepository,
    private val direction: ShopDirection
) : ShopContract.ViewModel, ViewModel() {

    override fun onEventDispatcher(intent: ShopContract.Intent) {
        when (intent) {
            ShopContract.Intent.LoadData -> {

                val products = arrayListOf<ProductData>()
                val categories = arrayListOf<String>()

                viewModelScope.launch {

                    appRepository.getAllCategorys().onEach { result ->
                        result.onSuccess { listCategory ->
                            categories.addAll(listCategory)
                            appRepository.getAllProducts().onEach { result ->
                                result.onSuccess {
                                    products.addAll(it)
                                    intent { reduce { ShopContract.UiState.Data(categories, products) } }
                                }

                                result.onFailure {

                                }
                            }.launchIn(viewModelScope).join()
                        }
                        result.onFailure {

                        }
                    }.launchIn(viewModelScope).join()


                }
            }
        }
    }

    override val container =
        container<ShopContract.UiState, ShopContract.SideEffect>(ShopContract.UiState.LoadingData)

}