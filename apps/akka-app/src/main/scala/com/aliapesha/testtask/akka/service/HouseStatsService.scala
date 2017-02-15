package com.aliapesha.testtask.akka.service

import com.aliapesha.testtask.akka.database.ProductionDatabase
import com.aliapesha.testtask.core.entity.HouseStat
import com.outworkers.phantom.dsl._

import scala.concurrent.Future


trait HouseStatsService extends ProductionDatabase {

  /**
    * Find sample
    *
    * @param
    * @return
    */
  def sample(): Future[Option[HouseStat]] = {
    database.houseStatsModel.sample()
  }

  /**
    * Find stat by Id
    *
    * @param id
    * @return
    */
  def getById(id: UUID): Future[Option[HouseStat]] = {
    database.houseStatsModel.getById(id)
  }

  /**
    * Save a stat
    *
    * @param stat
    * @return
    */
  def saveOrUpdate(stat: HouseStat): Future[ResultSet] = {
    database.houseStatsModel.store(stat)
  }

  /**
    * Delete a song in both table
    *
    * @param stat
    * @return
    */
  def delete(stat: HouseStat): Future[ResultSet] = {
      database.houseStatsModel.deleteById(stat.id)
  }
}

/**
  * Let available a singleton instance of this service class, to prevent unnecessary instances
  */
object HouseStatsService extends HouseStatsService with ProductionDatabase
