package io.riccardomerolla

import one.microstream.storage.embedded.types.{EmbeddedStorageManager}

class GreetingRepository {

  var storageManager: Option[EmbeddedStorageManager] = None
  def getStoreManager = storageManager.get

  def shutdown() {
    storageManager.get.shutdown()
  }
}

object GreetingRepository {
  def apply(path: Option[String]): GreetingRepository = {
    val gr = new GreetingRepository
    gr.storageManager = Some(MicrostreamStorage.init(Thread.currentThread().getContextClassLoader()))
    gr
  }
}
