package uz.gita.examfinal3.app

import android.app.Application
import androidx.compose.ui.platform.LocalContext
import dagger.hilt.android.HiltAndroidApp
import uz.gita.examfinal3.data.sourse.LocalStorage

@HiltAndroidApp
class App: Application(){
    override fun onCreate() {
        super.onCreate()
        LocalStorage.init(this)
    }
}