/** MACHINE-GENERATED FROM AVRO SCHEMA. DO NOT EDIT DIRECTLY */
package test.model

import org.apache.avro.Schema

import org.oedura.scavro.{AvroMetadata, AvroReader, AvroSerializeable}

import model.model.UnionRecord

import model.v2.model.NestedRecord

import model.{UnionRecord => JUnionRecord}

import model.v2.{NestedRecord => JNestedRecord}

import test.{ComplexExternalDependency => JComplexExternalDependency}

case class ComplexExternalDependency(nestedrecord: NestedRecord) extends AvroSerializeable {
  type J = JComplexExternalDependency
  override def toAvro: JComplexExternalDependency = {
    new JComplexExternalDependency(nestedrecord match {
      case NestedRecord(nestedunion) => new JNestedRecord(nestedunion match {
        case Some(x) => x match {
          case UnionRecord(blah) => new JUnionRecord(blah)
        }
        case None => null
      })
    })
  }
}

object ComplexExternalDependency {
  implicit def reader = new AvroReader[ComplexExternalDependency] {
    override type J = JComplexExternalDependency
  }
  implicit val metadata: AvroMetadata[ComplexExternalDependency, JComplexExternalDependency] = new AvroMetadata[ComplexExternalDependency, JComplexExternalDependency] {
    override val avroClass: Class[JComplexExternalDependency] = classOf[JComplexExternalDependency]
    override val schema: Schema = JComplexExternalDependency.getClassSchema()
    override val fromAvro: (JComplexExternalDependency) => ComplexExternalDependency = {
      (j: JComplexExternalDependency) => ComplexExternalDependency(NestedRecord(j.getNestedrecord.getNestedunion match {
        case null => None
        case _ => Some(UnionRecord(j.getNestedrecord.getNestedunion.getBlah.toString))
      }))
    }
  }
}