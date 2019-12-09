package com.crave.bbq.calc

/**
 * Created by roberto on 18/11/17.
 */

class BarbecueCalc(
        private val menQuantity: Double,
        private val womenQuantity: Double,
        private val childrenQuantity: Double
) {

    val meatQuantity: Double
        get() = (this.menQuantity * 500 + this.womenQuantity * 300 + this.childrenQuantity * 200) / 1000

    val sausageQuantity: Double
        get() = (menQuantity * 450 + womenQuantity * 250 + childrenQuantity * 200) / 1000
}
