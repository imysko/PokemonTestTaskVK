package com.imysko.pokemontesttaskvk.presentation.ui.commom

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imysko.pokemontesttaskvk.data.remote.entities.RequestException
import com.imysko.pokemontesttaskvk.utils.NoConnectivityException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.internal.http.HTTP_NOT_FOUND
import java.net.SocketTimeoutException

open class BaseViewModel : LifecycleObserver, ViewModel() {

    protected fun <T> call(
        useCaseCall: suspend () -> T?,
        onSuccess: ((T) -> Unit)? = null,
        onNullResult: (() -> Unit)? = null,
        onError: ((Throwable) -> Unit)? = null,
        onNetworkUnavailable: (suspend () -> Unit)? = null,
        onTimeout: (() -> Unit)? = null,
        onFinally: (() -> Unit)? = null,
    ) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            try {
                val result = useCaseCall.invoke()

                result?.let { value ->
                    onSuccess?.invoke(value)
                } ?: run {
                    onNullResult?.invoke()
                }
            } catch (ex: NoConnectivityException) {
                onNetworkUnavailable?.invoke() ?: run {
                    onError?.invoke(ex)
                }
            } catch (ex: SocketTimeoutException) {
                onTimeout?.invoke() ?: run {
                    onError?.invoke(ex)
                }
            } catch (ex: RequestException) {
                if (ex.statusCode == HTTP_NOT_FOUND) {
                    onNullResult?.invoke()
                }
            } catch (ex: Exception) {
                onError?.invoke(ex)
            } finally {
                onFinally?.invoke()
            }
        }
    }

    protected fun <T> call(
        useCaseCall: () -> Flow<T>,
        onCollect: ((T) -> Unit)? = null,
        onError: ((Throwable) -> Unit)? = null,
    ) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            useCaseCall()
                .cancellable()
                .catch { exception ->
                    onError?.invoke(exception)
                }
                .collectLatest { result ->
                    onCollect?.invoke(result)
                }
        }
    }

    companion object {
        const val PAGE_SIZE = 20
    }
}
