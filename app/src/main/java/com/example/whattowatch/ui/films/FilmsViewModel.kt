package com.example.whattowatch.ui.films

import android.app.Application
import android.util.Log
import androidx.annotation.NonNull
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.whattowatch.NetworkService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.whattowatch.ui.films.Response as MyResponse

class FilmsViewModel(application: Application, networkService: NetworkService) : AndroidViewModel(application) {

    var networkService = networkService
    var filmsLiveData : MutableLiveData<List<Film>> = MutableLiveData()

    var films:MutableList<Film> = mutableListOf()

    init {
        getFilms()
    }

    fun getFilms(){
        var offset = films.size
        networkService.getFilmsApi().getFilms(offset).enqueue(object : Callback<MyResponse>{
            override fun onResponse(@NonNull call: Call<MyResponse>, @NonNull response: Response<MyResponse>) {
                val myResponse = response.body()
                Log.d("Films", myResponse.toString())
                if (myResponse != null) {
                    films.addAll(myResponse!!.films)
                    filmsLiveData.postValue(films)
                }
            }

            override fun onFailure(@NonNull call: Call<MyResponse>, @NonNull t: Throwable) {
                Log.d("Films", t.localizedMessage)
            }

        })
    }

}