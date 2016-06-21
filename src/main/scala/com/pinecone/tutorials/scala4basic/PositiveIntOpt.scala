package com.pinecone.tutorials.scala4basic

/**
  * Created by ivan on 2016/6/16.
  */
class PositiveIntOpt(val n: Int) extends AnyVal {
  def isEmpty: Boolean =
    n <= 0
  def get: Int =
    n
}