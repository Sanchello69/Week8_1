package com.vas.feature_main_screen.data.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.vas.core.utils.Constants.BASE_URL
import com.vas.core.utils.Constants.HEROES_URL
import com.vas.core.utils.Result
import com.vas.feature_main_screen.data.model.HeroData
import okhttp3.OkHttpClient
import okhttp3.Request

class NetworkClient {

    private val client = OkHttpClient()

    private val moshi = Moshi.Builder().build()
    private val type = Types.newParameterizedType(List::class.java, HeroData::class.java)
    private val adapter = moshi.adapter<List<HeroData>>(type)

    fun getHeroesList(): Result<List<HeroData>> {
        try {
            val request = Request.Builder()
                .url(BASE_URL + HEROES_URL)
                .build()
            client.newCall(request).execute().use { response ->
                return if (response.isSuccessful)
                    Result(
                        status = Result.Status.SUCCESS,
                        data = adapter.fromJson(response.body!!.source())!!,
                        message = response.message
                    )
                else
                    Result(
                        status = Result.Status.ERROR,
                        data = null,
                        message = response.message
                    )
            }
        } catch (e: Exception){
            return Result(
                status = Result.Status.ERROR,
                data = null,
                message = e.message
            )
        }
    }

}