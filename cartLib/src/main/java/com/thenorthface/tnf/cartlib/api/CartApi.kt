package com.thenorthface.tnf.cartlib.api

import retrofit2.Response
import retrofit2.http.GET



interface CartApi {
    @GET(".")
    suspend fun getQuotes(): Response<ApiResponse>
}

