package com.aliapesha.testtask.akka.model

import java.util.UUID

import com.aliapesha.testtask.core.entity.HouseStat
import com.outworkers.phantom.dsl._

import scala.concurrent.Future


class HouseStatModel extends CassandraTable[ConcreteHouseStatModel, HouseStat] {

  override def tableName: String = "housestats"

  object id extends UUIDColumn(this) with PartitionKey {
    override lazy val name = "id"
  }

  object crime extends DoubleColumn(this)
  object zn extends DoubleColumn(this)
  object indus extends DoubleColumn(this)
  object chas extends IntColumn(this)
  object nox extends DoubleColumn(this)
  object averagerooms extends DoubleColumn(this)
  object ageproportion extends DoubleColumn(this)
  object weighteddistance extends DoubleColumn(this)
  object rad extends IntColumn(this)
  object tax extends DoubleColumn(this)
  object ptratio extends DoubleColumn(this)
  object bindex extends DoubleColumn(this)
  object lstat extends DoubleColumn(this)
  object medianvalue extends DoubleColumn(this)


  override def fromRow(r: Row): HouseStat = HouseStat(
    id(r),
    crime(r),
    zn(r),
    indus(r),
    chas(r),
    nox(r),
    averagerooms(r),
    ageproportion(r),
    weighteddistance(r),
    rad(r),
    tax(r),
    ptratio(r),
    bindex(r),
    lstat(r),
    medianvalue(r))
}

/**
  * Define the available methods for this model
  */
abstract class ConcreteHouseStatModel extends HouseStatModel with RootConnector {

  def sample(): Future[Option[HouseStat]] = {
    select
      .consistencyLevel_=(ConsistencyLevel.ONE)
        .one()
  }

  def getById(id: UUID): Future[Option[HouseStat]] = {
    select
      .where(_.id eqs id)
      .consistencyLevel_=(ConsistencyLevel.ONE)
      .one()
  }

  def store(stat: HouseStat): Future[ResultSet] = {
    insert
      .value(_.id, stat.id)
      .value(_.zn, stat.zn)
      .value(_.crime, stat.crime)
      .value(_.indus, stat.indus)
      .value(_.tax, stat.tax)
      .value(_.lstat, stat.lstat)
      .value(_.nox, stat.nox)
      .value(_.averagerooms, stat.averagerooms)
      .value(_.ageproportion, stat.ageproportion)
      .value(_.bindex, stat.bindex)
      .value(_.chas, stat.chas)
      .value(_.weighteddistance, stat.weighteddistance)
      .value(_.ptratio, stat.ptratio)
      .consistencyLevel_=(ConsistencyLevel.ONE)
      .future()
  }

  def deleteById(id: UUID): Future[ResultSet] = {
    delete
      .where(_.id eqs id)
      .consistencyLevel_=(ConsistencyLevel.ONE)
      .future()
  }
}


