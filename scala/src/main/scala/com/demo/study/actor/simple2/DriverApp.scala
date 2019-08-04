package com.demo.study.actor.simple2

import akka.actor.{ActorSystem, Props}
import com.demo.study.actor.MessageProtocol.InitSign

object DriverApp {
  def main(args: Array[String]): Unit = {
    val actorSystem: ActorSystem = ActorSystem("teacherStudentSystem")
    // 老师的代理对象
    val teacherActorRef = actorSystem.actorOf(Props[TeacherActor], "teacherActor")
    // 学生的代理对象
    val studentActorRef = actorSystem.actorOf(Props[StudentActor](new StudentActor(teacherActorRef)), "studentActor")

    studentActorRef ! InitSign

    Thread.sleep(2000)

    actorSystem.terminate() // //旧版本是shutdown()
  }
}
