package com.mgarzondev.paymentspt.data.repository

import retrofit2.Response
import com.mgarzondev.paymentspt.data.network.dto.AnnulmentResponse
import com.mgarzondev.paymentspt.data.network.dto.AuthRequest
import com.mgarzondev.paymentspt.data.network.dto.AuthResponse
import com.mgarzondev.payments.data.repository.HeaderAuth
import com.mgarzondev.paymentspt.data.local.dao.AuthTransactionDao
import com.mgarzondev.paymentspt.data.local.entities.AnnulmentEntity
import com.mgarzondev.paymentspt.data.local.entities.AuthTransactionEntity
import com.mgarzondev.paymentspt.data.network.RetrofitClient
import com.mgarzondev.paymentspt.data.network.api.PaymentsApi
import com.mgarzondev.paymentspt.data.network.auth.AuthUtils
import com.mgarzondev.paymentspt.data.network.dto.AnnulmentRequest

class PaymentRepository(private val api: PaymentsApi = RetrofitClient.api, private val dao: AuthTransactionDao) {

    suspend fun authorizeTransaction(request: AuthRequest): Result<AuthResponse>  {
        val authHeader:String = AuthUtils.basicAuth(HeaderAuth.DEFAULT_COMMERCE_CODE, HeaderAuth.DEFAULT_TERMINAL_CODE)

        return try {
            val response: Response<AuthResponse> = api.authorize(authHeader, request)
            val body = response.body()

            if(body != null){
                val transactionEntity = AuthTransactionEntity(
                    id = request.id,
                    receiptId = body.receiptId,
                    rrn = body.rrn,
                    cardHolder = request.cardHolder,
                    amount = request.amount,
                    card = request.card,
                    timestamp = System.currentTimeMillis(),
                    nullable = false
                )

                //Guardar en Room
                dao.insert(transactionEntity)

                Result.success(body)
            } else {
                    Result.failure(Exception("Error: respuesta vacía del servidor"))
            }

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun annulmentTransaction(request: AnnulmentRequest): Result<AnnulmentResponse> {
        val authHeader:String = AuthUtils.basicAuth(HeaderAuth.DEFAULT_COMMERCE_CODE, HeaderAuth.DEFAULT_TERMINAL_CODE)

        return try {
            val response: Response<AnnulmentResponse> = api.annulment(authHeader, request)
            val body = response.body()

            if(body != null){
                val annulmentEntity= AnnulmentEntity(receiptId = request.receptId)

                //Obtener una transacción por su recibo
                val transactionForAnnulment = dao.getTransactionById(annulmentEntity.receiptId)

                if (transactionForAnnulment != null) {
                    dao.updateAuthorizationStatus(transactionForAnnulment.rrn, true)
                } else {
                    println("Transacción no encontrada para recibo ${annulmentEntity.receiptId}")
                }

                Result.success(body)

            } else {
                    Result.failure(Exception("Error: respuesta vacía del servidor"))
            }

        } catch (e: Exception) {
            Result.failure(e)
        }

    }

    suspend fun getAllTransactions(): List<AuthTransactionEntity> {
        return dao.getAllTransactions()
    }
}
