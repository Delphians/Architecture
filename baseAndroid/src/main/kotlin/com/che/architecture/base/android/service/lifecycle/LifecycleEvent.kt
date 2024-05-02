package com.che.architecture.base.android.service.lifecycle

sealed class LifecycleEvent {
    data object Started : LifecycleEvent()
    data object Resumed : LifecycleEvent()
    data object Stopped : LifecycleEvent()
    data object Paused : LifecycleEvent()
}
