package com.aliapesha.testtask.akka.database

import com.aliapesha.testtask.akka.connector.Connector._
import com.aliapesha.testtask.akka.model.ConcreteHouseStatModel
import com.outworkers.phantom.dsl._


class HouseStatsDatabase(override val connector: KeySpaceDef) extends Database[HouseStatsDatabase](connector) {

  object houseStatsModel extends ConcreteHouseStatModel with connector.Connector
}

/**
  * This is the production database, it connects to a secured cluster with multiple contact points
  */
object ProductionDb extends HouseStatsDatabase(connector)

trait ProductionDatabaseProvider {
  def database: HouseStatsDatabase
}

trait ProductionDatabase extends ProductionDatabaseProvider {
  override val database = ProductionDb
}

/**
  * Thanks for the Phantom plugin, you can start an embedded cassandra in memory,
  * in this case we are using it for tests
  */
object EmbeddedDb extends HouseStatsDatabase(testConnector)

trait EmbeddedDatabaseProvider {
  def database: HouseStatsDatabase
}

trait EmbeddedDatabase extends EmbeddedDatabaseProvider {
  override val database = EmbeddedDb
}
