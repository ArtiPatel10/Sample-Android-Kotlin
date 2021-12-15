package com.sample.helper

class AppConstant {

    /**
     * This annotation class is used for trade request list view type in adaptor class.
     */
    annotation class RecyclerViewType {
        companion object {
            const val Date: Int = 0
            const val Item: Int = 1
            const val NoData: Int = 2
        }
    }
}