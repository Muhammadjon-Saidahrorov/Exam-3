package uz.gita.examfinal3.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.examfinal3.util.navigation.AppNavigator
import uz.gita.examfinal3.util.navigation.NavigationDispatcher
import uz.gita.examfinal3.util.navigation.NavigationHandler
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NavigatorModule {

    @[Binds Singleton]
    fun bindAppNavigator(impl : NavigationDispatcher)  : AppNavigator

    @[Binds Singleton]
    fun bindNavigatorHandler(impl : NavigationDispatcher)  : NavigationHandler

}