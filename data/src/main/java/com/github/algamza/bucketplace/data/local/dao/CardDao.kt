package com.github.algamza.bucketplace.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.algamza.bucketplace.data.local.entities.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CardDao {
    @Query("SELECT * FROM CardEntity")
    fun getCardEntities() : Flow<List<CardEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCardEntities(entities: List<CardEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCardEntity(entities: CardEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCardRecommendEntities(entities: List<CardRecommendEntity>)

    @Query("DELETE FROM CardEntity")
    fun deleteCardEntities()

    @Query("SELECT * FROM UserEntity")
    fun getUserEntities() : Flow<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserEntities(entities: List<UserEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserEntity(entities: UserEntity)

    @Query("DELETE FROM UserEntity")
    fun deleteUserEntities()

    @Query("SELECT * FROM HomeEntity WHERE id =:id")
    fun getHomeEntity(id: Int) : Flow<HomeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHomeEntity(entity: HomeEntity)

    @Query("DELETE FROM HomeEntity WHERE id =:id")
    fun deleteHomeEntity(id: Int)

    @Query("SELECT * FROM CardEntity WHERE id =:id")
    fun getCardDetailEntity(id: Int) : Flow<CardDetailEntity>

    @Query("SELECT * FROM UserEntity WHERE id =:id")
    fun getUserDetailEntity(id: Int) : Flow<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLogInEntity(entity: LoginEntity)

    @Query("SELECT * FROM LoginEntity WHERE id =:id")
    fun getLogInEntity(id: Int) : Flow<LoginEntity>
}