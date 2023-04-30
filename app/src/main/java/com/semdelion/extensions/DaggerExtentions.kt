package com.semdelion.extensions

import android.content.Context
import androidx.fragment.app.Fragment
import com.semdelion.app.App
import com.semdelion.di.IAppComponent

val Context.appComponent: IAppComponent
    get() = when (this) {
        is App -> appComponent
        else -> this.applicationContext.appComponent
    }

val Fragment.appComponent: IAppComponent
    get() = requireActivity().applicationContext.appComponent