package ru.smak.polynoms

import java.util.*
import kotlin.math.abs

class Lagrange(private val vals: MutableMap<Double, Double>) : Polynom(){

    init{
        createLagrange()
    }

    constructor(x: Array<Double>, y: Array<Double>): this(Hashtable<Double, Double>()){
        val min = if (x.size<=y.size) x else y
        min.indices.forEach { vals[x[it]] = y[it] }
        createLagrange()
    }

    private fun createLagrange(){
        if (vals.size>0) {
            var res = Polynom()
            for ((x, y) in vals) {
                res += createFundamentalLagrange(x) * y
            }
            c.clear()
            c.addAll(res.c)
        }
    }

    private fun createFundamentalLagrange(x_k: Double): Polynom {
        var res = Polynom(arrayOf(1.0))
        for ((x, y) in vals){
            if (abs(x_k-x)<1e-7) continue
            res *= Polynom(arrayOf(-x, 1.0)) / (x_k - x)
        }
        return res
    }
}