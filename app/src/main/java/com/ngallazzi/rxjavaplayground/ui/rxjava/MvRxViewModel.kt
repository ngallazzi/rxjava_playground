package com.ngallazzi.rxjavaplayground.ui.rxjava

import com.airbnb.mvrx.BaseMvRxViewModel
import com.airbnb.mvrx.BuildConfig
import com.airbnb.mvrx.MvRxState

open class MvRxViewModel<S : MvRxState>(state: S) :
    BaseMvRxViewModel<S>(state, debugMode = BuildConfig.DEBUG)