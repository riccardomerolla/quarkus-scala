package io.riccardomerolla

import javax.ws.rs.{GET, Path, Produces}
import javax.ws.rs.core.MediaType

@Path("/hello")
class GreetingResource {

  val storageManager = GreetingRepository(None).getStoreManager

  @GET
  @Produces(Array[String](MediaType.APPLICATION_JSON))
  @Path("/world")
  def hello() = {

    if (null == storageManager.root()) {
      storageManager.setRoot(DataRoot(Content("Hello RESTEasy") :: Nil))
      storageManager.storeRoot()
    }

    val root = storageManager.root().asInstanceOf[DataRoot]
    val list = root.contents.last.text.toCharArray.map(c => c.isLower match {
        case true => c.toUpper
        case _    => c.toLower
    })

    root.contents = root.contents :+ Content(list.mkString)
    storageManager.storeRoot()

    println(root.contents)
    root.contents.last.text
  }

  @GET
  @Produces(Array[String](MediaType.TEXT_PLAIN))
  @Path("/count")
  def count() = {
    val root = storageManager.root().asInstanceOf[DataRoot]
    root.contents.size
  }
}
