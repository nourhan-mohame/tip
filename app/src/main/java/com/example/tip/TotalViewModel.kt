package com.example.tip



import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TotalViewModel:ViewModel() {

    private var total =0.0
    var livetotalamount:MutableLiveData<Double>?=null

    fun totalvalue(cost:Double, tip: Double, function: () -> Unit) {
        total=cost*tip
        livetotalamount?.value=total
    }

    fun getlivetotal():LiveData<Double>?{
        livetotalamount=livetotalamount?: MutableLiveData()
        return livetotalamount
    }
}