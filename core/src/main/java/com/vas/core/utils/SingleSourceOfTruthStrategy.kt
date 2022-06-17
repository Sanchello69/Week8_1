package com.vas.core.utils

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import kotlinx.coroutines.Dispatchers

/**
 * Файл будет являться единственным источником истины
 * Поэтому UI получает данные только из файла
 */
fun <T, A> resultLiveData(fileQuery: () -> LiveData<T>,
                          networkCall: suspend () -> Result<A>,
                          saveCallResult: suspend (A) -> Unit): LiveData<Result<T>> =
    liveData(Dispatchers.IO) {
        emit(Result.loading<T>())
        val source = fileQuery.invoke().map { Result.success(it) }
        emitSource(source)
        Log.d("resultLiveData", "ok")
        val responseStatus = networkCall.invoke()
        if (responseStatus.status == Result.Status.SUCCESS) {
            saveCallResult(responseStatus.data!!)
            Log.d("resultLiveData", responseStatus.data!!.toString())
            val source = fileQuery.invoke().map { Result.success(it) }
            emitSource(source)
        } else if (responseStatus.status == Result.Status.ERROR) {
            Log.d("resultLiveData", responseStatus.message!!)
            emit(Result.error<T>(responseStatus.message!!))
            emitSource(source)
        }
    }