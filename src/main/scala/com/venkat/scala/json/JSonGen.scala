package com.venkat.scala.json


import scala.util.Random
import java.text.SimpleDateFormat
import java.util.{Calendar, Date, UUID}

import org.json4s.JsonAST.{JArray, JObject}
import scala.collection.mutable.ListBuffer
import org.json4s._
import org.json4s.JsonDSL._
import org.json4s.jackson.JsonMethods._
import org.json4s.jackson.Serialization.{read, write}
import java.io._

import com.venkat.scala.entity._
//import com.venkat.scala.reader._

/**
  * Created by VenkatramR on 6/13/2017.
  */

class JSonGen {
  import com.venkat.scala.json.JSonGen._

  val JSON_FILE = "request.json"
  var list : scala.collection.immutable.List[JsonAST.JObject]   = List()

  def reqJsonGenerator : Unit = {
    val listBuffer = createRequests
    list = listBuffer.map{ req =>
      var json:JObject =
        ("empRequestID",req.getEmpRequestID) ~
          ("requestDate", req.getRequestDate) ~
          ("empProviderID", req.getEmpProviderID) ~
          ("empProviderName", req.getEmpProviderName) ~
          ("empProductID", req.getEmpProductID) ~
          ("empProductName", req.getEmpProductName) ~
          ("empProductClassification", req.getEmpProductClassification) ~
          ("empProductSubClassification", req.getEmpProductSubClassification) ~
          ("empReviewType", req.getEmpReviewType) ~
          ("empReviewType", req.getEmpReviewType) ~
          ("empRequestingGroup", req.getEmpRequestingGroup) ~
          ("QuestionnaireUrl", req.getQuestionnaireUrl) ~
          ("Priority", req.getPriority)

      json
    }.toList

    var jarray = JArray(list)
    implicit val formats = DefaultFormats
    var ser = write(jarray)
    //println(pretty(ser))
    var prettyJsonString = pretty(render(parse(ser)))
    println(prettyJsonString)
    val pw = new PrintWriter(new File(JSON_FILE))
    pw.write(prettyJsonString)
    pw.close()
    /*jarray.arr.map{
      x => println(pretty(x))
    }*/
    /*println("size is " + list.size)
    //println(prettyJsonString)
    list.map {
     x => println(pretty(x))
    }*/
  }

}

object JSonGen{

  private val CHAR_LIST = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"
  private val RANDOM_STRING_LENGTH = 10
  private val CLASSIFICATION = Array[String]("Differentiated Data", "Industry Data", "Market Data", "Research,Publications/Newsletters", "Other")
  private val EMP_REVIEW_TYPE = Array[String]("Samples", "Trial/POC", "Subscription", "Other")
  private val EMP_REQ_GROUP = Array[String]("XYZ", "Mario")
  private val DOMAIN_GROUPS = Array[String](".com", ".org", ".net")
  private val DATE_RANGE = Array[Integer](10, 20, 30, 40, 50, 60, 70, 80, 90, -10, -20, -30, -40, -50, -60, -70, -80, -90)

  private def getProvderIdForProviderName(reqList: ListBuffer[Request], providerName: String):String = {

    for (r <- reqList) {
      if (r.getEmpProviderName != null && r.getEmpProviderName.equals(providerName))
        r.getEmpProviderID
    }
    null
  }

  def createRequests : ListBuffer[Request] = {
    var reqListBuffer = new ListBuffer[Request]

    for(i <- 1 to 100){
      var request = new Request
      var uuid = UUID.randomUUID()
      request.setEmpRequestID(uuid.toString)
      request.setRequestDate(formattedDate)
      var providerName = getMeaningFulWords.trim
      val providerId = getProvderIdForProviderName(reqListBuffer, providerName)
      request.setEmpProviderName(providerName)
      if (providerId != null) {
        request.setEmpProviderID(providerId)
      }
      else {
        uuid = UUID.randomUUID
        request.setEmpProviderID(uuid.toString)
      }

      uuid = UUID.randomUUID
      request.setEmpProductID(uuid.toString)
      val prodName = getMeaningFulWords.trim

      request.setEmpProductName(prodName)
      request.setEmpProductClassification(CLASSIFICATION(getRandomNumber(6)))
      request.setEmpProductSubClassification(generateRandomString)

      request.setEmpReviewType(EMP_REVIEW_TYPE(getRandomNumber(5)))

      request.setEmpRequestingGroup(EMP_REQ_GROUP(getRandomNumber(3)))

      request.setQuestionnaireUrl("http://" + generateRandomString + DOMAIN_GROUPS(getRandomNumber(4)))

      var value: Int = getRandomNumber(11)
      if (value == 0) {
        value = 1
      }
      request.setPriority(value)
      reqListBuffer += request

    }
    reqListBuffer
  }



  private def formattedDate :String = {
    val format = new SimpleDateFormat("MM/dd/yyyy")
    val c = Calendar.getInstance
    c.setTime(new Date())
    val randomDate = DATE_RANGE(getRandomNumber(18))
    c.add(Calendar.DATE, randomDate)
    val d = c.getTime
    format.format(d)
  }

  def getMeaningFulWords: String = {
    val fileReader = new com.venkat.scala.reader.FileReader
    val wordList = fileReader.getListOfWords("word-list.txt")
    var name = StringBuilder.newBuilder
    for(i <- 0 to getRandomNumber(4)){
      val rand = getRandomNumber(100)
      name.append(wordList(rand))
      name.append("  ")
    }
    name.toString()
  }

  def generateRandomString: String = {
    var builder = StringBuilder.newBuilder
    for(i <- 1 to RANDOM_STRING_LENGTH){
      var number = getRandomNumber(CHAR_LIST.length())
      var ch = CHAR_LIST.charAt(number)
      builder.append(ch)
    }
    builder.toString
  }

  private def getRandomNumber(length: Int) = {
    var randomInt = 0
    val randomGenerator = new Random()
    randomInt = randomGenerator.nextInt(length)
    if (randomInt - 1 == -1) randomInt
    else randomInt - 1
  }

}

object JSonGenTest extends App{
  var jsonGen  = new JSonGen
  /*var listBuffer = jsonGen.createRequests

  listBuffer.foreach(req =>{
    println("***********")
    println(req.getEmpProductID)
    println(req.getQuestionnaireUrl)
    println(req.getRequestDate)
    println(req.getEmpProductClassification)
    println(req.getEmpProductName)
    println(req.getEmpProviderName)
    println(req.getEmpProviderID)
    println(req.getEmpProductSubClassification)
    println(req.getEmpReviewType)
    println(req.getEmpRequestingGroup)
    println(req.getPriority)
  })*/
  jsonGen.reqJsonGenerator
}
