package com.sample.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = TableConstants.GymsTable)
class GymsTable(

    @PrimaryKey
    @field:ColumnInfo(name = "id")
    var id: Int = 0,

    @field:ColumnInfo(name = "title")
    var title: String? = null,

    @field:ColumnInfo(name = "rating")
    var rating: Double? = null,

    @field:ColumnInfo(name = "price")
    var price: Int? = null,

    @field:ColumnInfo(name = "favorite")
    var favorite: Boolean = false,

    @field:ColumnInfo(name = "date")
    var date: String? = null
)

@Entity(tableName = TableConstants.ClassesTable)
class ClassesTable(
    @PrimaryKey(autoGenerate = true)
    @field:ColumnInfo(name = "localId")
    var localId: Int = 0,

    @field:ColumnInfo(name = "gymId")
    var gymId: Long=0,

    @field:ColumnInfo(name = "title")
    var title: String? = null,

    @field:ColumnInfo(name = "price")
    var price: Int? = null,

    @field:ColumnInfo(name = "favorite")
    var favorite: Boolean=false,

    @field:ColumnInfo(name = "location")
    var location: String? = null,

    @field:ColumnInfo(name = "time")
    var time: String? = null
)

@Entity(tableName = TableConstants.ActivitiesTable)
class ActivitiesTable(

    @PrimaryKey(autoGenerate = true)
    @field:ColumnInfo(name = "res")
    var resourse: Int=0,

    @field:ColumnInfo(name = "selected")
    var selected: Boolean=false
)

