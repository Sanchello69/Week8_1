package com.vas.feature_details_screen.data.local

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.vas.core.utils.Constants
import com.vas.feature_details_screen.data.model.HeroData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DetailsLocalFile(private val context: Context) {

    private val moshi = Moshi.Builder().build()
    private val adapter = moshi.adapter(HeroData::class.java)

    fun getHeroesDetailsFile(name: String) : LiveData<HeroData> = liveData(Dispatchers.IO) {
        try {
            val fileInputStream = context.openFileInput(Constants.HEROES_FILE_DETAILS + name)
            val bytes = ByteArray(fileInputStream.available())
            fileInputStream.read(bytes)
            val heroesJson = String(bytes)
            fileInputStream?.close()

            val heroDetails = adapter.fromJson(heroesJson)

            emit(heroDetails!!)

        } catch (e: Exception){
            Log.d("error_get_file", e.message.toString())
        }
    }

    suspend fun saveHeroesDetailsFile(hero: HeroData){
        withContext(Dispatchers.IO){
            val heroJson = adapter.toJson(hero)

            try {
                val fileOutputStream = context.openFileOutput(Constants.HEROES_FILE_DETAILS + hero.name, Context.MODE_PRIVATE)
                fileOutputStream.write(heroJson.toByteArray())
                fileOutputStream?.close()
            } catch (e: Exception){
                Log.d("error_save_file", e.message.toString())
            }
        }
    }
}