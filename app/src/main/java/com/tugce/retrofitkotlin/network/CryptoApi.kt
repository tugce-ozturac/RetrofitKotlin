package com.tugce.retrofitkotlin.network

import com.tugce.retrofitkotlin.model.Crypto
import retrofit2.http.GET

interface CryptoApi {
    @GET("atilsamancioglu/K21-JSONDataSet/master/crypto.json")
    suspend fun getCryptos(): List<Crypto>
}