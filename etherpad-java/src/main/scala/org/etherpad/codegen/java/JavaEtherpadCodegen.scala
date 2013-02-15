package org.etherpad.codegen.java

import com.wordnik.swagger.codegen.BasicJavaGenerator

object JavaEtherpadCodegen extends BasicJavaGenerator {
  def main(args: Array[String]) = generateClient(args)

  // location of templates
  override def templateDir = "src/main/resources"

  // where to write generated code
  override def destinationDir = "target/generated-sources"

  // package for api invoker, error files
  override def invokerPackage = Some("org.etherpad.client")

  // package for models
  override def modelPackage = Some("org.etherpad.model")

  // package for api classes
  override def apiPackage = Some("org.etherpad.api")

  // response classes
  override def processResponseClass(responseClass: String): Option[String] = {
    responseClass match {
      case "void" => None
      case e: String => Some(typeMapping.getOrElse(e, e.replaceAll("\\[", "<").replaceAll("\\]", ">")))
    }
  }

  override def processResponseDeclaration(responseClass: String): Option[String] = {
    responseClass match {
      case "void" => None
      case e: String => Some(typeMapping.getOrElse(e, e.replaceAll("\\[", "<").replaceAll("\\]", ">")))
    }
  }

  // import/require statements for specific datatypes
  override def importMapping = Map(
    "Date" -> "java.util.Date",
    "Array" -> "java.util.*",
    "ArrayList" -> "java.util.*",
    "List" -> "java.util.*")

  // supporting classes
  override def supportingFiles =
    List(
      ("apiInvoker.mustache", destinationDir + java.io.File.separator + invokerPackage.get.replaceAll("\\.", java.io.File.separator) + java.io.File.separator, "ApiInvoker.java"),
      ("apiException.mustache", destinationDir + java.io.File.separator + invokerPackage.get.replaceAll("\\.", java.io.File.separator) + java.io.File.separator, "ApiException.java"),
      ("EtherpadClient.mustache", destinationDir + java.io.File.separator + apiPackage.get.replaceAll("\\.", java.io.File.separator) + java.io.File.separator, "EtherpadClient.java")
    )
}
