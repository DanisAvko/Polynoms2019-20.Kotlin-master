package ru.avk.polynoms
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class Newton(private var valx: ArrayList<Double>, private var valy: ArrayList<Double>):Polynom() {
    var res1=Polynom(arrayOf(1.0))
    var cal=Hashtable<Pair<Int,Int>,Double>()
    init{
        createNewton()
    }

    constructor(x: Array<Double>, y: Array<Double>): this(ArrayList(), ArrayList()){
        val min = if (x.size<=y.size) x else y
        min.indices.forEach {
            if(!valx.contains(x[it])) {
                valx.add(x[it])
                valy.add(y[it])
            }
        }
        createNewton()
    }

    constructor(vals:MutableMap<Double,Double>): this(ArrayList(), ArrayList()){
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
            res+=res1*getTermF(0,0)
            for (key in 1..(valx.size-1))
            {
                res1*=Polynom(arrayOf(-valx[key-1],1.0))
                res+=res1*getTermF(0,key)
            }
            c.clear()
            c.addAll(res.coeffs)
        }
    }

    private fun getTermF(begin:Int,end:Int): Double {
        if (begin == end) {
            cal[Pair(begin,begin)]=valy[begin]
            return valy[begin]
        } else if (end == begin + 1) {
            cal[Pair(begin,end)]=(valy[end] - valy[begin]) / (valx[end] - valx[begin])
            return cal.getValue(Pair(begin,end))
        } else {
            if(cal.keys.contains(Pair(begin,end)))return cal.getValue(Pair(begin,end))
            else {
                cal[Pair(begin,end)]=(getTermF(begin + 1, end) - getTermF(begin, end - 1)) / (valx[end] - valx[begin])
                return cal.getValue(Pair(begin,end))
            }
        }
    }
           fun addNode(x: Double, y: Double) {
               if(!valx.contains(x)) {
                   valx.add(x);
                   valy.add(y);

            var res = Polynom(c);
            res1*=Polynom(arrayOf(-valx[valx.size-2],1.0))
            res += res1 * getTermF(0, valx.size - 1);
            c.clear()
            c.addAll(res.coeffs)
               }
        }

}