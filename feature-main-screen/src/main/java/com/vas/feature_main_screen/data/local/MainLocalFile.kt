package com.vas.feature_main_screen.data.local

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.vas.core.utils.Constants.HEROES_FILE
import com.vas.feature_main_screen.data.model.HeroData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainLocalFile(private val context: Context) {

    private val moshi = Moshi.Builder().build()
    private val type = Types.newParameterizedType(List::class.java, HeroData::class.java)
    private val adapter = moshi.adapter<List<HeroData>>(type)

    fun getHeroesFile() : LiveData<List<HeroData>> = liveData(Dispatchers.IO) {
        try {
            val fileInputStream = context.openFileInput(HEROES_FILE)
            val bytes = ByteArray(fileInputStream.available())
            fileInputStream.read(bytes)
            val heroesJson = String(bytes)
            fileInputStream?.close()

            val heroesList = adapter.fromJson(heroesJson)

            emit(heroesList!!)

        } catch (e: Exception){
            Log.d("error_get_file", e.message.toString())
            emit(emptyList())
        }
    }

    suspend fun saveHeroesFile(heroesList: List<HeroData>){
        withContext(Dispatchers.IO){
            val heroesJson = adapter.toJson(heroesList)

            try {
                val fileOutputStream = context.openFileOutput(HEROES_FILE, MODE_PRIVATE)
                fileOutputStream.write(heroesJson.toByteArray())
                fileOutputStream?.close()
            } catch (e: Exception){
                Log.d("error_save_file", e.message.toString())
            }
        }
    }

}