package com.sample.room

import androidx.lifecycle.LiveData
import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable

@Dao
interface CommomDao {

    @Query("SELECT * FROM ${TableConstants.GymsTable}")
    fun loadAllGyms(): LiveData<List<GymsTable>>

    @Query("SELECT * FROM ${TableConstants.GymsTable} limit 1")
    fun getRecord(): Maybe<GymsTable>

    @Insert
    fun insert(obj: GymsTable): Long

    @Insert
    fun insert(obj: ClassesTable)

    @Insert
    fun insert(obj: ActivitiesTable)

    @Delete
    fun delete(obj: GymsTable): Completable

    @Delete
    fun delete(obj: ClassesTable): Completable

    @Query("DELETE FROM ${TableConstants.GymsTable}")
    fun deleteAll(): Completable

    @Query("UPDATE ${TableConstants.GymsTable} SET favorite =:updatedStatus WHERE id=:id")
    fun updateFavouriteStatusForGym(id: Int, updatedStatus: Boolean)

    @Query("UPDATE ${TableConstants.ClassesTable} SET favorite =:updatedStatus WHERE localId=:id")
    fun updateFavouriteStatusForClass(id: Int, updatedStatus: Boolean)

    @Query("UPDATE ${TableConstants.ActivitiesTable} SET selected =:updatedStatus WHERE res=:res")
    fun updateSelectedStatusForActivity(res: Int, updatedStatus: Boolean)

    @Query("SELECT * FROM ${TableConstants.ClassesTable} ")
    fun loadAllClasses(): LiveData<List<ClassesTable>>

    @Query("SELECT * FROM ${TableConstants.ActivitiesTable} ")
    fun loadAllActivities(): LiveData<List<ActivitiesTable>>

    @Insert
    fun insertAll(toList: List<ActivitiesTable>)
}