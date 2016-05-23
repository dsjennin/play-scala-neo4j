package controllers

import play.api._
import play.api.mvc._
import play.api.cache.Cache
import play.api.Play.current
import play.api.db._
import org.anormcypher._
import play.api.libs.ws._



object Application extends Controller {

  def index = Action {
    Ok(views.html.index(null))
      }


  def db = Action {

    // Provide an instance of WSClient
    implicit val wsclient = ning.NingWSClient()

    // without auth
    implicit val connection: Neo4jConnection = Neo4jREST("localhost", 7474)

    // Provide an ExecutionContext
    implicit val ec = scala.concurrent.ExecutionContext.global

    // create some test nodes
    Cypher("""create (anorm:anormcyphertest {name:"AnormCypher"}), (test:anormcyphertest {name:"Test"})""").execute()

    // a simple query
    val req = Cypher("match (n:anormcyphertest) return n.name")

    // get a stream of results back
    val stream = req()

    // get the results and put them into a list
   val results: List[(String)] =  stream.map(row => {row[String]("n.name")}).toList

    // remove the test nodes
    Cypher("match (n:anormcyphertest) delete n")()

    // shut down WSClient
    wsclient.close()

    Ok(views.html.index(results))
  }
}
