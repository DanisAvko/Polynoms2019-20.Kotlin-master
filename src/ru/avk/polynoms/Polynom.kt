package ru.avk.polynoms

open class Polynom(){

    protected val c: ArrayList<Double>

    val coeffs: ArrayList<Double>
        get(){
            val cf = ArrayList<Double>()
            cf.addAll(c)
            return cf
        }

    init{
        c = ArrayList()
        c.add(0.0)
    }

    constructor(coeffs: ArrayList<Double>): this(){
        c.clear()
        c.addAll(coeffs)
        correctDeg()
    }

    constructor(coeffs: Array<Double>): this(){
        c.clear()
        coeffs.forEach { c.add(it) }
        correctDeg()
    }

    fun correctDeg(){
        var i = deg
        while (i > 0 && Math.abs(c[i]) < 1e-7) {
            c.removeAt(i)
            i--
        }
    }

    val deg: Int
        get() = c.size - 1

    operator fun plus(other: Polynom): Polynom{
        val (min_p, max_p) =
            if (deg<other.deg) Pair(this, other)
            else Pair(other, this)
        val cf = Array(max_p.deg+1)
            {if (it<=min_p.deg) min_p.c[it]+max_p.c[it]
             else max_p.c[it]
            }
        return Polynom(cf)
    }

    operator fun times(x: Double) =
        Polynom(Array(c.size){c[it]*x})

    operator fun div(x: Double) = this * (1.0 / x)

    fun getValue(x: Double): Double {
        var px = 1.0
        return c.reduce{ s, i -> px*=x; s + i * px }
    }

    operator fun times(other: Polynom): Polynom {
        val cf =
            Array(deg + other.deg + 1){0.0}
        for (i in 0..deg) {
            for (j in 0..other.deg) {
                cf[i + j] += c[i] * other.c[j]
            }
        }
        return Polynom(cf)
    }

    operator fun minus(other: Polynom) =
        this + other * -1.0

    override fun toString() =
        c.indices.reversed().joinToString(separator = ""){
            val res = StringBuilder()
            if (Math.abs(c[it]) > 1e-6) {
                if (c[it] < 0)
                    res.append(" - ")
                else if (c[it] > 0 && it != c.size - 1)
                    res.append(" + ")
                if (Math.abs(Math.abs(c[it]) - 1) > 1e-6 || it == 0)
                    res.append(Math.abs(c[it]))
                if (it > 0) {
                    res.append('x')
                    if (it > 1) {
                        res.append("^")
                        res.append(it)
                    }
                }
                res.toString()
            } else if (c.size == 1) "0" else ""
        }

}