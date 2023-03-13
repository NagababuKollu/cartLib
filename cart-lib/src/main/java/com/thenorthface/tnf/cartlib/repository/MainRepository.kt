package com.thenorthface.tnf.cartlib.repository

import com.thenorthface.tnf.cartlib.api.ApiResponse
import com.thenorthface.tnf.cartlib.api.Resource


interface MainRepository {
    suspend fun getQuotes(): Resource<ApiResponse>
}