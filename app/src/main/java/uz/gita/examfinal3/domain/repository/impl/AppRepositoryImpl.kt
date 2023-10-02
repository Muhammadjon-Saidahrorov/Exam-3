package uz.gita.examfinal3.domain.repository.impl

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import uz.gita.examfinal3.data.model.CategoryData
import uz.gita.examfinal3.data.model.ProductData
import uz.gita.examfinal3.data.sourse.LocalStorage
import uz.gita.examfinal3.domain.repository.AppRepository
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor() : AppRepository {

    private val db = Firebase.firestore
    private val localStage = LocalStorage.getInstance()!!

    override fun addProducts(productData: ProductData): Flow<Result<Unit>> = callbackFlow {
        val myMap = hashMapOf(
            "userId" to localStage.getEmail(),
            "name" to productData.name,
            "price" to productData.price,
            "size" to productData.size,
            "category" to productData.category,
            "amount" to productData.amount
        )

        db.collection("products")
            .add(myMap)
            .addOnSuccessListener {
                trySend(Result.success(Unit))
            }
            .addOnFailureListener {
                trySend(Result.failure(it))
            }

        awaitClose()
    }

    override fun addCategory(categoryData: CategoryData): Flow<Result<Unit>> = callbackFlow {
        val myMap = hashMapOf(
            "name" to categoryData.name,
        )
        db.collection("category")
            .add(myMap)
            .addOnSuccessListener {
                trySend(Result.success(Unit))
            }
            .addOnFailureListener {
                trySend(Result.failure(it))
            }

        awaitClose()
    }

    override fun getAllProducts(): Flow<Result<List<ProductData>>> = callbackFlow {
        db.collection("products")
            .get()
            .addOnSuccessListener { querysnabshot ->
                val list = ArrayList<ProductData>()
                querysnabshot.forEach {
                    val productData = ProductData(
                        it.id,
                        it.get("userId") as String,
                        it.get("name") as String,
                        it.get("price") as String,
                        it.get("size") as String,
                        it.get("category") as String,
                        it.get("amount") as String,
                    )
                    list.add(productData)
                }

                trySend(Result.success(list))
            }
            .addOnFailureListener {
                trySend(Result.failure(it))
            }
        awaitClose()
    }

    override fun getAllProductsByUserId(): Flow<Result<List<ProductData>>> {
        return callbackFlow {
            db.collection("products")
                .get()
                .addOnSuccessListener { querysnabshot ->
                    val list = ArrayList<ProductData>()

                    querysnabshot.forEach {
                        val bookData = ProductData(
                            it.id,
                            it.get("userId") as String,
                            it.get("name") as String,
                            it.get("price") as String,
                            it.get("size") as String,
                            it.get("category") as String,
                            it.get("amount") as String,
                        )
                        val userId = it.get("userId") as String
                        if (userId == localStage.getEmail()){
                            list.add(bookData)
                        }
                    }
                    trySend(Result.success(list))
                }
                .addOnFailureListener {
                    trySend(Result.failure(it))
                }
            awaitClose()
        }
    }

    override fun getAllCategorys(): Flow<Result<List<String>>> = callbackFlow {
        db.collection("category")
            .get()
            .addOnSuccessListener { querysnabshot ->
                val list = ArrayList<String>()

                querysnabshot.forEach {
                    list.add(it.get("name") as String)
                }
                trySend(Result.success(list))
            }
            .addOnFailureListener {
                trySend(Result.failure(it))
            }
        awaitClose()
    }

}