package com.demo.study.actor.simple2

import akka.actor.{Actor, ActorLogging, ActorRef}
import com.demo.study.actor.MessageProtocol.{InitSign, QuoteRequest, QuoteResponse}

class StudentActor(teacherActorRef: ActorRef) extends Actor with ActorLogging  {
  override def receive = {
    case InitSign => {
      teacherActorRef ! QuoteRequest()
      //            println("student send request")
    }
    case QuoteResponse(resp) => {
      log.info(s"$resp")
    }
  }
}
