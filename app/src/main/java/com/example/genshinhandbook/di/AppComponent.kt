package com.example.genshinhandbook.di


import com.example.genshinhandbook.di.module.ApiModule
import com.example.genshinhandbook.di.module.AppModule
import com.example.genshinhandbook.di.module.DataModule
import com.example.genshinhandbook.di.module.RetrofitModule
import com.example.genshinhandbook.presentation.homefragment.HomeFragment
import com.example.genshinhandbook.presentation.iteminfofragment.ItemInfoFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ApiModule::class, DataModule::class, RetrofitModule::class] )
interface AppComponent {

    fun injectHomeFragment(homeFragment: HomeFragment)

    fun injectItemInfoFragment(itemInfoFragment: ItemInfoFragment)

}