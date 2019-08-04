package com.demo.study.actor.simple1

import akka.actor.{Actor, ActorSystem, Props}
import com.demo.study.actor.MessageProtocol.{QuoteRequest, QuoteResponse}

import scala.util.Random


/**
  * actor 在scala2.10.0 过时,2.11.5之后不再更新
  * akka 本地发送消息
  */

object StudentActorOps {
  def main(args: Array[String]): Unit = {
    // 第一步：构建Actor操作系统
    val actorSystem = ActorSystem("StudentActorSystem")
    // 第二步：actorSystem创建TeacherActor的代理对象ActorRef
    val teacherActorRef = actorSystem.actorOf(Props[TeacherActor])
    // 第三步：发送消息
    teacherActorRef ! QuoteRequest()

    Thread.sleep(200)
    // 第四步：关闭
    actorSystem.terminate() //旧版本是shutdown()
  }

}

class TeacherActor extends Actor {
  val quotes = List(
    "Moderation is for cowards",
    "Anything worth doing is worth overdoing",
    "The trouble is you think you have time",
    "You never gonna know if you never even try")

  override def receive = {
    case QuoteRequest() => {
      val random = new Random()

      val randomIndex = random.nextInt(quotes.size)
      val randomQuote = quotes(randomIndex)

      val response = QuoteResponse(randomQuote)
      println(response)
    }
  }
}

