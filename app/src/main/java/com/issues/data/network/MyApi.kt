package com.issues.data.network

import com.issues.ui.resp.CommentResponse
import com.issues.ui.resp.IssueResponse
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url
import java.util.concurrent.TimeUnit


interface MyApi {

    @GET("repos/square/okhttp/issues")
    fun getIssues(): Call<IssueResponse>

    /*@GET("repos/square/okhttp/issues")
    fun getComments(): Call<IssueResponse>*/

    @GET
    fun getComments(@Url url: String): Call<CommentResponse>

    companion object{
        operator fun invoke(): MyApi {
            val okHttpClient = OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build()

            return Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
                .create(MyApi::class.java)
        }

        private val okHttpClient = OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()
        var myApi: MyApi? = null
        fun getInstance(): MyApi {
            if(myApi == null){
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://api.github.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build()
                myApi = retrofit.create(MyApi::class.java)
            }
            return myApi!!
        }
    }
}