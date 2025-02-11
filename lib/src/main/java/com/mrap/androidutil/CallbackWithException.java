package com.mrap.androidutil;

public interface CallbackWithException<T> {
  void onCallback(T arg) throws Exception;
}
