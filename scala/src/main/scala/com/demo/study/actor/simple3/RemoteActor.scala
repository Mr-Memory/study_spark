package com.demo.study.actor.simple3

import akka.actor.{Actor, ActorLogging}
import com.demo.study.actor.{Header, Shutdown, Start, Stop}

class RemoteActor extends Actor with ActorLogging {
  override def receive: Receive = {
    case Start => { // 处理Start消息
      log.info("Remote Server Start ==>RECV Start event : " + Start)
    }
    case Stop => { // 处理Stop消息
      log.info("Remote Server Stop ==>RECV Stop event: " + Stop)
    }
    case Shutdown(waitSecs) => { // 处理Shutdown消息
      log.info("Remote Server Shutdown ==>Wait to shutdown: waitSecs=" + waitSecs)
      Thread.sleep(waitSecs)
      log.info("Remote Server Shutdown ==>Shutdown this system.")
      context.system.terminate() // 停止当前ActorSystem系统
    }
    case Header(id, len, encrypted) => log.info("Remote Server => RECV header: " + (id, len, encrypted)) // 处理Header消息
    case _ =>
  }
}
