package com.ubaya.advweek4.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubaya.advweek4.model.Student

class DetailViewModel(application: Application):AndroidViewModel(application) {
    private var TAG ="volTag"
    private var queue: RequestQueue?= null
    var studentId="95-1452169"
    val studentLD = MutableLiveData<Student>()

    fun setIdStudent(Id:String){
        studentId=Id
    }

    fun fetch() {
        var url="http://adv.jitusolution.com/student.php?id=$studentId"
        queue = Volley.newRequestQueue(getApplication())
        var stringRequest = StringRequest(
            Request.Method.GET,url,
            {response->
                val sType= object : TypeToken<Student>(){}.type
                val result = Gson().fromJson<Student>(response,sType)
                studentLD.value=result
                Log.d("showChoose", response.toString())

            },
            {
                Log.d("showChoose", it.toString())
            })
        stringRequest.tag=TAG
        queue?.add(stringRequest)
    }

}
