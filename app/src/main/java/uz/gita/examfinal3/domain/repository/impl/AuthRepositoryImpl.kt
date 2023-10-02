package uz.gita.examfinal3.domain.repository.impl

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import uz.gita.examfinal3.data.sourse.LocalStorage
import uz.gita.examfinal3.domain.repository.AuthRepository
import uz.gita.examfinal3.util.myLog
import javax.inject.Inject

var user: FirebaseUser? = null

class AuthRepositoryImpl @Inject constructor(): AuthRepository {

    private val auth: FirebaseAuth = Firebase.auth
    private val localStage = LocalStorage.getInstance()!!


    override fun signIn(email: String, password: String): Flow<Result<Unit>> = callbackFlow {

        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                user = auth.currentUser
                user?.email?.let { it1 -> localStage.saveEmail(it1) }
                trySend(Result.success(Unit))
            }
            .addOnFailureListener {
                trySend(Result.failure(it))
            }
            .addOnCanceledListener {
                trySend(Result.failure(Exception("Cancelled Operation")))
            }
        awaitClose { }
    }

    override fun createUser(email: String, password: String): Flow<Result<Unit>> = callbackFlow {
        myLog("AuthRepository -> signUp")

        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                user = auth.currentUser
                user?.email?.let { it1 -> localStage.saveEmail(it1) }
                trySend(Result.success(Unit))
            }
            .addOnFailureListener {
                trySend(Result.failure(it))
            }
            .addOnCanceledListener {
                trySend(Result.failure(Exception("Cancelled Operation")))
            }
        awaitClose { }
    }
}