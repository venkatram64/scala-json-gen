package com.venkat.scala.reader

import java.io.{FileNotFoundException, IOException}
import scala.io.Source._



/**
  * Created by VenkatramR on 6/13/2017.
  */
class FileReader {

  def getListOfWords(fileName:String): List[String] = {
    val source = fromFile(fileName)
    val wordList =
      try{

        val content = source.getLines().toList
        if(content.size > 0)
          content.filter(_.length > 5)
        else
          List[String]()
      }catch{
        case ex: FileNotFoundException => {
          println(s"Couldn't find the file $fileName")
          List[String]()
        }
        case ex: IOException => {
          println("Read failed.")
          List[String]()
        }
      }finally {
        source.close()
      }
    wordList
  }

}

object FileReader extends App{
  val fileReader = new FileReader
  val wordList = fileReader.getListOfWords("word-list.txt")
  wordList.foreach(println)
}
