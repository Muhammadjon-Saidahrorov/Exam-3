package uz.gita.examfinal3.domain.repository

import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun signIn(email: String, password: String): Flow<Result<Unit>>
    fun createUser(email: String, password: String): Flow<Result<Unit>>
}