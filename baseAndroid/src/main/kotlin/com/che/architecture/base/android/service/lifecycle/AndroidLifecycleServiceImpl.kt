package com.che.architecture.base.android.service.lifecycle

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class AndroidLifecycleServiceImpl @Inject constructor(
    private val processLifecycleOwner: LifecycleOwner
) : AndroidLifecycleService, DefaultLifecycleObserver {

    private val currentLifecycleFlow = MutableSharedFlow<LifecycleEvent>(
        replay = 1
    )

    init {
        processLifecycleOwner.lifecycle.addObserver(this)
    }

    override fun observeLifecycleEvents(): Flow<LifecycleEvent> =
        currentLifecycleFlow.asSharedFlow()

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        currentLifecycleFlow.tryEmit(LifecycleEvent.Started)
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        currentLifecycleFlow.tryEmit(LifecycleEvent.Resumed)
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        currentLifecycleFlow.tryEmit(LifecycleEvent.Paused)
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        currentLifecycleFlow.tryEmit(LifecycleEvent.Stopped)
    }
}
