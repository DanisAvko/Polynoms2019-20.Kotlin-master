package ru.avk.polynoms
import java.util.*
import kotlin.collections.ArrayList

class Newton(private var valx: ArrayList<Double>, private var valy: ArrayList<Double>):Polynom() {
    init{
        createNewton()
    }

    constructor(x: Array<Double>, y: Array<Double>): this(ArrayList(), ArrayList()){
        val min = if (x.size<=y.size) x else y
        min.indices.forEach {
            valx.add(x[it])
            valy.add(y[it])
        }
        createNewton()
    }

    constructor(vals:Hashtable<Double,Double>): this(ArrayList(), ArrayList()){
        vals.keys.forEach {
            valx.add(it)
            valy.add(vals.getValue(it))
        }
        createNewton()
    }
    private fun createNewton() {
        if(valx.size>0)
        {
            var res=Polynom()
            for (key in valx.indices)
            {
                var res1=Polynom(arrayOf(1.0))
                for(k in 0..(key-1))
                {
                    res1=res1*Polynom(arrayOf(-valx[k],1.0))
                }
                res+=res1*getTerm(0,key);
            }
            c.clear()
            c.addAll(res.c)
        }
    }

    private fun getTerm(begin:Int,end:Int): Double {
        if(begin==end) {
            return valy[begin]
        }
        else if(end==begin+1) {
            return (valy[end]-valy[begin])/(valx[end]-valx[begin])
        }
        else {
            return (getTerm(begin+1,end)-getTerm(begin,end-1))/(valx[end]-valx[begin]);
        }
    }
}