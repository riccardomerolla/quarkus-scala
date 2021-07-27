package io.riccardomerolla


case class Content(text: String)
case class DataRoot(var contents: List[Content])
