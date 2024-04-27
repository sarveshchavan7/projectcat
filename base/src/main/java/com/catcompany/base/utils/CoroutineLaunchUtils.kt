package com.catcompany.base.utils

import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

fun CoroutineScope.safeLaunch(
    start: CoroutineStart = CoroutineStart.DEFAULT,
    coroutineContext: CoroutineContext = EmptyCoroutineContext,
    context: Context,
    block: suspend CoroutineScope.() -> Unit,
): Job {
    return launch(coroutineContext + coroutineExceptionHandler(context), start, block)
}

fun LifecycleOwner.safeLaunch(
    start: CoroutineStart = CoroutineStart.DEFAULT,
    coroutineContext: CoroutineContext = EmptyCoroutineContext,
    context: Context,
    showToastOnError: Boolean = true,
    onError: ((Exception) -> Unit)? = null,
    block: suspend CoroutineScope.() -> Unit,
): Job {
    return lifecycleScope.safeLaunch(start, coroutineContext, context) {
        try {
            block()
        } catch (e: Exception) {
            if (showToastOnError && e !is CancellationException) {
                onError?.invoke(e)
                context.showToast(e)
                throw e
            }
        }
    }
}

fun LifecycleOwner.safeLaunchWithRepeatOnLifecycle(
    start: CoroutineStart = CoroutineStart.DEFAULT,
    coroutineContext: CoroutineContext = EmptyCoroutineContext,
    showToastOnError: Boolean = true,
    state: Lifecycle.State,
    onError: ((Exception) -> Unit)? = null,
    block: suspend CoroutineScope.() -> Unit,
): Job {
    return safeLaunch(start, coroutineContext, this as Context, showToastOnError, onError) {
        repeatOnLifecycle(state) { block() }
    }
}

fun AndroidViewModel.safeLaunch(
    start: CoroutineStart = CoroutineStart.DEFAULT,
    coroutineContext: CoroutineContext = EmptyCoroutineContext,
    showToastOnError: Boolean = true,
    onError: ((Exception) -> Unit)? = null,
    block: suspend CoroutineScope.() -> Unit,
): Job {
    val applicationContext: Context = this.getApplication()
    return viewModelScope.safeLaunch(start, coroutineContext, this.getApplication()) {
        try {
            block()
        } catch (e: Exception) {
            if (showToastOnError && e !is CancellationException) {
                applicationContext.showToast(e)
                onError?.invoke(e)
                throw e
            }
        }
    }
}
