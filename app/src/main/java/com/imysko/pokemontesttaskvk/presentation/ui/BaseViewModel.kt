package com.imysko.pokemontesttaskvk.presentation.ui

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imysko.pokemontesttaskvk.utils.NoConnectivityException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.SocketTimeoutException

open class BaseViewModel : LifecycleObserver, ViewModel() {

    protected fun <T> call(
        useCaseCall: suspend () -> Result<T>,
        onSuccess: ((T) -> Unit)? = null,
        onError: ((Throwable) -> Unit)? = null,
        onNetworkUnavailable: (suspend () -> Unit)? = null,
        onTimeout: (() -> Unit)? = null,
    ) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            try {
                val result = useCaseCall.invoke()

                result.getOrNull()?.let { value ->
                    onSuccess?.invoke(value)
                }

                result.exceptionOrNull()?.let { error ->
                    onError?.invoke(error)
                }
            } catch (ex: NoConnectivityException) {
                onNetworkUnavailable?.invoke()
            } catch (ex: SocketTimeoutException) {
                onTimeout?.invoke()
            } catch (ex: Exception) {
                onError?.invoke(ex)
            }
        }
    }
}