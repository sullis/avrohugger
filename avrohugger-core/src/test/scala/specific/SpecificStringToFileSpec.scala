package avrohugger
package test
package specific

import avrohugger._
import avrohugger.format.SpecificRecord
import org.specs2._

class SpecificStringToFileSpec extends mutable.Specification {

  "SpecificRecord Generator stringToFiles method" should {

    // tests common to fileToX and stringToX
    "1. correctly generate from a protocol with messages" in {
      val inputString = util.Util.readFile("avrohugger-core/src/test/avro/mail.avpr")
      val gen = new Generator(SpecificRecord)
      val outDir = gen.defaultOutputDir + "/specific/"
      gen.stringToFile(inputString, outDir)
      
      val sourceTrait = util.Util.readFile(s"$outDir/example/proto/Mail.scala")
      val sourceRecord = util.Util.readFile(s"$outDir/example/proto/Message.scala")

      sourceTrait === util.Util.readFile("avrohugger-core/src/test/expected/specific/example/proto/Mail.scala")
      sourceRecord === util.Util.readFile("avrohugger-core/src/test/expected/specific/example/proto/Message.scala")
    }

    "2. correctly generate a simple case class definition in a package" in {
      val inputString = util.Util.readFile("avrohugger-core/src/test/avro/user.avsc")
      val gen = new Generator(SpecificRecord)
      val outDir = gen.defaultOutputDir + "/specific/"
      gen.stringToFile(inputString, outDir)
      
      val source = util.Util.readFile("target/generated-sources/specific/example/User.scala")
      
      source === util.Util.readFile("avrohugger-core/src/test/expected/specific/example/User.scala")
    }

    "3. correctly generate a simple case class definition in the default package" in {
      val inputString = util.Util.readFile("avrohugger-core/src/test/avro/AvroTypeProviderTestNoNamespace.avsc")
      val gen = new Generator(SpecificRecord)
      val outDir = gen.defaultOutputDir + "/specific/"
      gen.stringToFile(inputString, outDir)
      
      val source = util.Util.readFile("target/generated-sources/specific/AvroTypeProviderTestNoNamespace.scala")
      
      source === util.Util.readFile("avrohugger-core/src/test/expected/specific/AvroTypeProviderTestNoNamespace.scala")
    }

    "4. correctly generate a nested case class definition from a schema" in {
      val inputString = util.Util.readFile("avrohugger-core/src/test/avro/nested.avsc")
      val gen = new Generator(SpecificRecord)
      val outDir = gen.defaultOutputDir + "/specific/"
      gen.stringToFile(inputString, outDir)

      val source0 = util.Util.readFile("target/generated-sources/specific/example/Level0.scala")
      val source1 = util.Util.readFile("target/generated-sources/specific/example/Level1.scala")
      val source2 = util.Util.readFile("target/generated-sources/specific/example/Level2.scala")

      source0 === util.Util.readFile("avrohugger-core/src/test/expected/specific/example/Level0.scala")
      source1 === util.Util.readFile("avrohugger-core/src/test/expected/specific/example/Level1.scala")
      source2 === util.Util.readFile("avrohugger-core/src/test/expected/specific/example/Level2.scala")
    }

    "5. correctly generate a nested case class from IDL" in {
      val inputString = util.Util.readFile("avrohugger-core/src/test/avro/nested.avdl")
      val gen = new Generator(SpecificRecord)
      val outDir = gen.defaultOutputDir + "/specific/"
      gen.stringToFile(inputString, outDir)
      
      val source = util.Util.readFile("target/generated-sources/specific/example/idl/NestedProtocol.scala")
      
      source === util.Util.readFile("avrohugger-core/src/test/expected/specific/example/idl/NestedProtocol.scala")
    }

    "6. correctly generate a recursive case class from IDL" in {
      val inputString = util.Util.readFile("avrohugger-core/src/test/avro/recursive.avdl")
      val gen = new Generator(SpecificRecord)
      val outDir = gen.defaultOutputDir + "/specific/"
      gen.stringToFile(inputString, outDir)

      val source = util.Util.readFile("target/generated-sources/specific/example/idl/Recursive.scala")
      
      source === util.Util.readFile("avrohugger-core/src/test/expected/specific/example/idl/Recursive.scala")
    }

    "7. correctly generate enums from schema" in {
      val inputString = util.Util.readFile("avrohugger-core/src/test/avro/enums.avsc")
      val gen = new Generator(SpecificRecord)
      val outDir = gen.defaultOutputDir + "/specific/"
      gen.stringToFile(inputString, outDir)

      val source = util.Util.readFile("target/generated-sources/specific/example/Suit.java")
      
      source === util.Util.readFile("avrohugger-core/src/test/expected/specific/example/Suit.java")
    }

    "8. correctly generate enums from protocol" in {
      val inputString = util.Util.readFile("avrohugger-core/src/test/avro/enums.avpr")
      val gen = new Generator(SpecificRecord)
      val outDir = gen.defaultOutputDir + "/specific/"
      gen.stringToFile(inputString, outDir)

      val sourceEnum = util.Util.readFile("target/generated-sources/specific/example/proto/Suit.java")
      val sourceRecord = util.Util.readFile("target/generated-sources/specific/example/proto/Card.scala")
      
      sourceEnum === util.Util.readFile("avrohugger-core/src/test/expected/specific/example/proto/Suit.java")
      sourceRecord === util.Util.readFile("avrohugger-core/src/test/expected/specific/example/proto/Card.scala")
    }
 
    "9. correctly generate enums from IDL" in {
      val inputString = util.Util.readFile("avrohugger-core/src/test/avro/enums.avdl")
      val gen = new Generator(SpecificRecord)
      val outDir = gen.defaultOutputDir + "/specific/"
      gen.stringToFile(inputString, outDir)

      val sourceEnum = util.Util.readFile("target/generated-sources/specific/example/idl/Suit.java")
      val sourceRecord = util.Util.readFile("target/generated-sources/specific/example/idl/Card.scala")

      sourceEnum === util.Util.readFile("avrohugger-core/src/test/expected/specific/example/idl/Suit.java")
      sourceRecord === util.Util.readFile("avrohugger-core/src/test/expected/specific/example/idl/Card.scala")
    }
    
    "10. correctly generate nested enums" in {
      val inputString = util.Util.readFile("avrohugger-core/src/test/avro/enums_nested.avsc")
      val gen = new Generator(SpecificRecord)
      val outDir = gen.defaultOutputDir + "/specific/"
      gen.stringToFile(inputString, outDir)
      
      val sourceEnum = util.Util.readFile("target/generated-sources/specific/example/Direction.java")
      val sourceRecord = util.Util.readFile("target/generated-sources/specific/example/Compass.scala")
      
      sourceEnum === util.Util.readFile("avrohugger-core/src/test/expected/specific/example/Direction.java")
      sourceRecord === util.Util.readFile("avrohugger-core/src/test/expected/specific/example/Compass.scala")
    }

    "11. correctly generate bytes from schema" in {
      val inputString = util.Util.readFile("avrohugger-core/src/test/avro/bytes.avsc")
      val gen = new Generator(SpecificRecord)
      val outDir = gen.defaultOutputDir + "/specific/"
      gen.stringToFile(inputString, outDir)

      val source = util.Util.readFile("target/generated-sources/specific/example/BinarySc.scala")
      
      source === util.Util.readFile("avrohugger-core/src/test/expected/specific/example/BinarySc.scala")
    }

    "12. correctly generate bytes from protocol" in {
      val inputString = util.Util.readFile("avrohugger-core/src/test/avro/bytes.avpr")
      val gen = new Generator(SpecificRecord)
      val outDir = gen.defaultOutputDir + "/specific/"
      gen.stringToFile(inputString, outDir)

      val source = util.Util.readFile("target/generated-sources/specific/example/proto/BinaryPr.scala")

      source === util.Util.readFile("avrohugger-core/src/test/expected/specific/example/proto/BinaryPr.scala")
    }

    "13. correctly generate bytes from IDL" in {
      val inputString = util.Util.readFile("avrohugger-core/src/test/avro/bytes.avdl")
      val gen = new Generator(SpecificRecord)
      val outDir = gen.defaultOutputDir + "/specific/"
      gen.stringToFile(inputString, outDir)

      val source = util.Util.readFile("target/generated-sources/specific/example/idl/BinaryIdl.scala")
    
      source === util.Util.readFile("avrohugger-core/src/test/expected/specific/example/idl/BinaryIdl.scala")
    }

    "14. correctly generate records depending on others defined in a different- and same-namespaced AVDL and AVSC" in {
      val inputString = util.Util.readFile("avrohugger-core/src/test/avro/import.avdl")
      val gen = new Generator(SpecificRecord)
      val outDir = gen.defaultOutputDir + "/specific/"
      gen.stringToFile(inputString, outDir) must throwA(new java.lang.RuntimeException("Imports not supported in String IDLs, only avdl files."))
    }

    "15. correctly generate an empty case class definition" in {
      val inputString = util.Util.readFile("avrohugger-core/src/test/avro/AvroTypeProviderTestEmptyRecord.avdl")
      val gen = new Generator(SpecificRecord)
      val outDir = gen.defaultOutputDir + "/specific/"
      gen.stringToFile(inputString, outDir)
    
      val source = util.Util.readFile("target/generated-sources/specific/test/Calculator.scala")
    
      source === util.Util.readFile("avrohugger-core/src/test/expected/specific/test/Calculator.scala")
    }
   
    "16. correctly generate default values" in {
      val inputString = util.Util.readFile("avrohugger-core/src/test/avro/defaults.avdl")
      val gen = new Generator(SpecificRecord)
      val outDir = gen.defaultOutputDir + "/specific/"
      gen.stringToFile(inputString, outDir)

      val sourceRecord = util.Util.readFile("target/generated-sources/specific/example/idl/Defaults.scala")
      val sourceEnum = util.Util.readFile("target/generated-sources/specific/example/idl/DefaultEnum.java")

      sourceRecord === util.Util.readFile("avrohugger-core/src/test/expected/specific/example/idl/Defaults.scala")
      sourceEnum === util.Util.readFile("avrohugger-core/src/test/expected/specific/example/idl/DefaultEnum.java")
    }

    import util.GlobalTests
    for ((test, idx) <- GlobalTests.tests.zipWithIndex) {
      s"${idx + 17}. ${test.description}" in {
        test.toSpec(SpecificRecord).checkStringToFile
      }
    }
  }
}
