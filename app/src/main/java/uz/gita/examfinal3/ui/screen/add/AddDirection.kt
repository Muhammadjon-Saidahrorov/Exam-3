package uz.gita.examfinal3.ui.screen.add

import uz.gita.examfinal3.util.navigation.AppNavigator
import javax.inject.Inject

interface AddDirection {
    suspend fun back()
}

class AddDirectionImpl @Inject constructor(
    private val navigator: AppNavigator
) : AddDirection {
    override suspend fun back() {
        navigator.pop()
    }

}