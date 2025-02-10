package com.mrap.androidutil;

import android.util.Pair;
import android.util.SparseArray;

import java.util.ArrayList;

public class SparseArrayUtil {
  public static <T extends Object> int indexOfValueByValue(SparseArray<T> map, T value) {
    for (int i = 0; i < map.size(); i++) {
      if (value == null) {
        if (map.valueAt(i) == null) {
          return i;
        }
      } else {
        if (value.equals(map.valueAt(i))) {
          return i;
        }
      }
    }
    return -1;
  }

  public static <T extends Object> int keyOfValueByValue(SparseArray<T> map, T value) {
    int idx = indexOfValueByValue(map, value);
    if (idx != -1) {
      return map.keyAt(idx);
    }
    return -1;
  }

  public static <T extends Object> ArrayList<T> toArrayList(SparseArray<T> map) {
    ArrayList<T> list = new ArrayList<>();
    for (int i = 0; i < map.size(); i++) {
      list.add(map.valueAt(i));
    }
    return list;
  }

  public static <T extends Object> ArrayList<Pair<Integer, T>> toPairArrayList(SparseArray<T> map) {
    ArrayList<Pair<Integer, T>> list = new ArrayList<>();
    for (int i = 0; i < map.size(); i++) {
      list.add(new Pair<>(map.keyAt(i), map.valueAt(i)));
    }
    return list;
  }

  public static <T extends Object> int pairArrayListIndexOfKey(ArrayList<Pair<Integer, T>> list, int searchKey) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).first == searchKey) {
        return i;
      }
    }
    return -1;
  }

  public static <T extends Object> int pairArrayListIndexOfValue(ArrayList<Pair<Integer, T>> list, T searchVal) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).second.equals(searchVal)) {
        return i;
      }
    }
    return -1;
  }
}
