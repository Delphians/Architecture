package com.che.architecture.base.android.service.lifecycle

import kotlinx.coroutines.flow.Flow

interface AndroidLifecycleService {
    fun observeLifecycleEvents(): Flow<LifecycleEvent>
}
