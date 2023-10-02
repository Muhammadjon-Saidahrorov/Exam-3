package uz.gita.examfinal3.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.gita.examfinal3.data.model.CategoryData
import uz.gita.examfinal3.data.model.ProductData

interface AppRepository {
    fun addProducts(productData: ProductData): Flow<Result<Unit>>
    fun addCategory(categoryData: CategoryData): Flow<Result<Unit>>
    fun getAllProducts(): Flow<Result<List<ProductData>>>
    fun getAllProductsByUserId(): Flow<Result<List<ProductData>>>
    fun getAllCategorys(): Flow<Result<List<String>>>
}