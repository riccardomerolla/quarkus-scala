package io.riccardomerolla;

import one.microstream.reflect.ClassLoaderProvider;
import one.microstream.storage.embedded.types.EmbeddedStorage;
import one.microstream.storage.embedded.types.EmbeddedStorageManager;

import java.nio.file.Paths;

public class MicrostreamStorage {

  public static EmbeddedStorageManager init(ClassLoader classLoader) {

    EmbeddedStorageManager storage = EmbeddedStorage.Foundation(Paths.get("/tmp/micro"))
      .onConnectionFoundation(cf ->
        cf.setClassLoaderProvider(ClassLoaderProvider.New(
          classLoader
        ))
      )
      .start();

    return storage;
  }
}
