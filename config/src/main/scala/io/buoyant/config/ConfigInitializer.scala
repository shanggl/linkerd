package io.buoyant.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.jsontype.NamedType

/*所有配置信息都继承这个trait

 */
trait ConfigInitializer {

  def configClass: Class[_]
  def configId: String = configClass.getName

  lazy val namedType = new NamedType(configClass, configId)//finagle 的name 空间？

  def registerSubtypes(mapper: ObjectMapper): Unit =
    mapper.registerSubtypes(namedType)
}
