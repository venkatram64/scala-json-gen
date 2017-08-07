package com.venkat.scala.entity

/**
  * Created by VenkatramR on 6/13/2017.
  */
class Request() {

  private var empRequestID = ""
  private var requestDate = ""
  private var empProviderID = ""
  private var empProviderName = ""
  private var empProductID = ""
  private var empProductName = ""
  private var empProductClassification = ""
  private var empProductSubClassification = ""
  private var empReviewType = ""
  private var empRequestingGroup = ""
  private var questionnaireUrl = ""
  private var priority = 0

  def getEmpRequestID: String = empRequestID

  def setEmpRequestID(empRequestID: String): Unit = {
    this.empRequestID = empRequestID
  }

  def getRequestDate: String = requestDate

  def setRequestDate(requestDate: String): Unit = {
    this.requestDate = requestDate
  }

  def getEmpProviderID: String = empProviderID

  def setEmpProviderID(empProviderID: String): Unit = {
    this.empProviderID = empProviderID
  }

  def getEmpProviderName: String = empProviderName

  def setEmpProviderName(empProviderName: String): Unit = {
    this.empProviderName = empProviderName
  }

  def getEmpProductID: String = empProductID

  def setEmpProductID(empProductID: String): Unit = {
    this.empProductID = empProductID
  }

  def getEmpProductName: String = empProductName

  def setEmpProductName(empProductName: String): Unit = {
    this.empProductName = empProductName
  }

  def getEmpProductClassification: String = empProductClassification

  def setEmpProductClassification(empProductClassification: String): Unit = {
    this.empProductClassification = empProductClassification
  }

  def getEmpProductSubClassification: String = empProductSubClassification

  def setEmpProductSubClassification(empProductSubClassification: String): Unit = {
    this.empProductSubClassification = empProductSubClassification
  }

  def getEmpReviewType: String = empReviewType

  def setEmpReviewType(empReviewType: String): Unit = {
    this.empReviewType = empReviewType
  }

  def getEmpRequestingGroup: String = empRequestingGroup

  def setEmpRequestingGroup(empRequestingGroup: String): Unit = {
    this.empRequestingGroup = empRequestingGroup
  }

  def getQuestionnaireUrl: String = questionnaireUrl

  def setQuestionnaireUrl(questionnaireUrl: String): Unit = {
    this.questionnaireUrl = questionnaireUrl
  }

  def getPriority: Int = priority

  def setPriority(priority: Int): Unit = {
    this.priority = priority
  }


}
