package uz.gita.examfinal3.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.gita.examfinal3.ui.screen.account.AccountDirection
import uz.gita.examfinal3.ui.screen.account.AccountDirectionImpl
import uz.gita.examfinal3.ui.screen.add.AddDirection
import uz.gita.examfinal3.ui.screen.add.AddDirectionImpl
import uz.gita.examfinal3.ui.screen.home.shop.ShopDirection
import uz.gita.examfinal3.ui.screen.home.shop.ShopDirectionImpl
import uz.gita.examfinal3.ui.screen.home.shopping.ShoppingDirection
import uz.gita.examfinal3.ui.screen.home.shopping.ShoppingDirectionImpl
import uz.gita.examfinal3.ui.screen.signin.SignInDirection
import uz.gita.examfinal3.ui.screen.signin.SignInDirectionImpl
import uz.gita.examfinal3.ui.screen.signup.SignUpDirection
import uz.gita.examfinal3.ui.screen.signup.SignUpDirectionImpl

@Module
@InstallIn(ViewModelComponent::class)
interface DirectionModule {
    @Binds
    fun accountDirection(impl: AccountDirectionImpl): AccountDirection

    @Binds
    fun signInDirection(impl: SignInDirectionImpl): SignInDirection

    @Binds
    fun signUpDirection(impl: SignUpDirectionImpl): SignUpDirection

    @Binds
    fun shoppingDirection(impl: ShoppingDirectionImpl): ShoppingDirection

    @Binds
    fun shopDirection(impl: ShopDirectionImpl): ShopDirection

    @Binds
    fun addDirection(impl: AddDirectionImpl): AddDirection
}