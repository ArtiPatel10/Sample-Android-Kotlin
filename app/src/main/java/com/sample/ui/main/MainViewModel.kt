package com.sample.ui.main

import androidx.lifecycle.LiveData
import com.sample.helper.rxjava.autoDispose
import com.sample.MyApplication
import com.sample.helper.base.BaseViewModel
import com.sample.helper.dummyData.Gym
import com.sample.helper.dummyData.TempData
import com.sample.room.ActivitiesTable
import com.sample.room.ClassesTable
import com.sample.room.CommomDao
import com.sample.room.GymsTable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executors

class MainViewModel : BaseViewModel() {
    companion object {
        val commonDao: CommomDao = MyApplication.getAppRoomDB().commomDao()

    }

    init {
    }

    fun fetchGymDataFromLocal(): LiveData<List<GymsTable>> {
        return commonDao.loadAllGyms()
    }

    fun fetchClassDataFromLocal(): LiveData<List<ClassesTable>> {
        return commonDao.loadAllClasses()
    }

    fun fetchActivitiesDataFromLocal(): LiveData<List<ActivitiesTable>> {
        return commonDao.loadAllActivities()
    }

    /*
    * This is temporary solution to not save same data again into local db
    * */
    fun checkIfDataExist() {
        commonDao.getRecord().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .doOnError {
                fetchDataFromJson.postValue(true)
            }.doOnComplete {
            fetchDataFromJson.postValue(true)

        }.subscribe({
            if (it == null) {
                fetchDataFromJson.postValue(true)
            }
        }, {
            fetchDataFromJson.postValue(true)

        }).autoDispose(compositeDisposable)
    }

    fun saveGymData(gyms: ArrayList<Gym>?) {

        Executors.newSingleThreadExecutor().execute {
            commonDao.insertAll(TempData().getAllActivities().toList())
            if (gyms != null && gyms.isNotEmpty()) {
                for (gym in gyms) {
                    val gymsTable = GymsTable(
                        id = gym.id,
                        title = gym.title,
                        rating = gym.rating,
                        price = gym.price,
                        favorite = gym.favorite,
                        date = gym.date
                    )
                    val id = commonDao.insert(gymsTable)
                    val classes = gym.popularClasses

                    if (classes != null && classes.isNotEmpty()) {
                        for (classInfo in classes) {
                            val classTable = ClassesTable(
                                localId = 0,
                                gymId = id,
                                favorite = classInfo.favorite,
                                title = classInfo.title,
                                location = classInfo.location,
                                price = classInfo.price,
                                time = classInfo.time
                            )

                            commonDao.insert(classTable)

                        }
                    }

                }
            }

        }
    }

    fun updateFavouriteStatus(id: Int, updatedStatus: Boolean) {
        Executors.newSingleThreadExecutor().execute {
            commonDao.updateFavouriteStatusForGym(id, updatedStatus)
        }
    }

    fun updateFavouriteStatusForClass(id: Int, updatedStatus: Boolean) {
        Executors.newSingleThreadExecutor().execute {
            commonDao.updateFavouriteStatusForClass(id, updatedStatus)
        }
    }

    fun updateSelectedStatusForActivity(id: Int, updatedStatus: Boolean) {
        Executors.newSingleThreadExecutor().execute {
            commonDao.updateSelectedStatusForActivity(id, updatedStatus)
        }
    }
}