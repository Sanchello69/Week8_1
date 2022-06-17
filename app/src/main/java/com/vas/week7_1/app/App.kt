package com.vas.week7_1.app

import android.app.Application
import com.vas.feature_details_screen.di.DetailsDepsStore
import com.vas.feature_main_screen.di.MainDepsStore
import com.vas.week7_1.di.AppComponent
import com.vas.week7_1.di.AppModule
import com.vas.week7_1.di.DaggerAppComponent

class App : Application(){

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder().appModule(AppModule(context = this)).build()

        MainDepsStore.deps = appComponent
        DetailsDepsStore.deps = appComponent
    }
}