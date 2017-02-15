package com.aliapesha.testtask.core.entity

import java.util.UUID


object HouseStat {
  def fromArray(arr: Array[String]
               ): HouseStat = new HouseStat(
    UUID.randomUUID(),
    arr(0).toDouble,
    arr(1).toDouble,
    arr(2).toDouble,
    arr(3).toInt,
    arr(4).toDouble,
    arr(5).toDouble,
    arr(6).toDouble,
    arr(7).toDouble,
    arr(8).toInt,
    arr(9).toDouble,
    arr(10).toDouble,
    arr(11).toDouble,
    arr(12).toDouble,
    arr(13).toDouble
  )
}

case class HouseStat(id: UUID,
                     crime: Double,
                     zn: Double,
                     indus: Double,
                     chas: Int,
                     nox: Double,
                     averagerooms: Double,
                     ageproportion: Double,
                     weighteddistance: Double,
                     rad: Int,
                     tax: Double,
                     ptratio: Double,
                     bindex: Double,
                     lstat: Double,
                     medianvalue: Double
                    )

/*
1. CRIM      per capita crime rate by town
2. ZN        proportion of residential land zoned for lots over
25,000 sq.ft.
3. INDUS     proportion of non-retail business acres per town
4. CHAS      Charles River dummy variable (= 1 if tract bounds
river; 0 otherwise)
5. NOX       nitric oxides concentration (parts per 10 million)
6. RM        average number of rooms per dwelling
7. AGE       proportion of owner-occupied units built prior to 1940
8. DIS       weighted distances to five Boston employment centres
9. RAD       index of accessibility to radial highways
10. TAX      full-value property-tax rate per $10,000
11. PTRATIO  pupil-teacher ratio by town
12. B        1000(Bk - 0.63)^2 where Bk is the proportion of blacks
by town
13. LSTAT    % lower status of the population
14. MEDV     Median value of owner-occupied homes in $1000's
*/