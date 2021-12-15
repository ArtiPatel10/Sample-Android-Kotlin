package com.sample.helper.dummyData

import com.sample.R
import com.sample.room.ActivitiesTable

class TempData {

    fun getAllActivities(): Array<ActivitiesTable> {
        return arrayOf(ActivitiesTable(R.drawable.ic_run,false),ActivitiesTable(R.drawable.ic_aerobics,false),ActivitiesTable(R.drawable.ic_dances,false),ActivitiesTable(R.drawable.ic_swimming,false),ActivitiesTable(R.drawable.ic_yoga,false))
    }
}
