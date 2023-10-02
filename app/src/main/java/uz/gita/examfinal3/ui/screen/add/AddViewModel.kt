package uz.gita.examfinal3.ui.screen.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.examfinal3.domain.repository.AppRepository
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(
    private val appRepository: AppRepository,
    private val direction: AddDirection
) : AddContract.ViewModel, ViewModel() {

    override val container =
        container<AddContract.UiState, AddContract.SideEffect>(AddContract.UiState.LoadData( arrayListOf()))

    init {
        appRepository.getAllCategorys().onEach { result ->
            result.onSuccess {
                intent { reduce { AddContract.UiState.LoadData(it) } }
            }
            result.onFailure {
                intent {
                    postSideEffect(
                        AddContract.SideEffect.HasError(
                            it.message ?: "Exception occured!"
                        )
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    override fun eventDispatcher(intent: AddContract.Intent) {
        when (intent) {
            is AddContract.Intent.AddProduct -> {
                appRepository.addProducts(intent.productData).onEach {
                    it.onSuccess {
                        intent {
                            postSideEffect(
                                AddContract.SideEffect.HasSuccess(
                                    "Success!"
                                )
                            )
                        }
                        direction.back()
                    }
                    it.onFailure {
                        intent {
                            postSideEffect(
                                AddContract.SideEffect.HasError(
                                    it.message ?: "Exception occurred!"
                                )
                            )
                        }
                    }
                }.launchIn(viewModelScope)
            }
            is AddContract.Intent.AddCategory -> {
                appRepository.addCategory(intent.categoryData).onEach {
                    it.onSuccess {
                        intent {
                            postSideEffect(
                                AddContract.SideEffect.HasSuccess(
                                    "Success!"
                                )
                            )
                        }
                        appRepository.getAllCategorys().onEach { result ->
                            result.onSuccess {list ->
                                intent { reduce { AddContract.UiState.LoadData(list) } }
                            }
                            result.onFailure {
                                intent {
                                    postSideEffect(
                                        AddContract.SideEffect.HasError(
                                            it.message ?: "Exception occurred!"
                                        )
                                    )
                                }
                            }
                        }.launchIn(viewModelScope)
                    }
                    it.onFailure {
                        intent {
                            postSideEffect(
                                AddContract.SideEffect.HasError(
                                    it.message ?: "Exception occurred!"
                                )
                            )
                        }
                    }
                }.launchIn(viewModelScope)
            }
            is AddContract.Intent.Back -> {
                viewModelScope.launch {
                    direction.back()
                }
            }
        }
    }


}