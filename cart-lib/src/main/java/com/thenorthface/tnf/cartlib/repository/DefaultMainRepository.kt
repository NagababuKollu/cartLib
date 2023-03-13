package com.thenorthface.tnf.cartlib.repository

import com.thenorthface.tnf.cartlib.api.ApiResponse
import com.thenorthface.tnf.cartlib.api.CartApi
import com.thenorthface.tnf.cartlib.api.Resource
import java.lang.Exception
import javax.inject.Inject

/**
 *Created by Zaid Zakir
 */
class DefaultMainRepository @Inject constructor(
    private val kanyeWestApi: CartApi
):MainRepository {
    override suspend fun getQuotes(): Resource<ApiResponse> {
        return try {
            val response = kanyeWestApi.getQuotes()
            val result = response.body()
            if (response.isSuccessful && result != null){
                Resource.Success(result)
            }else{
                Resource.Error("An Error occurred")
            }
        }catch (e:Exception){
            println("kanyeWestApi $e")
            Resource.Error("An $e occurred")
        }
    }
}