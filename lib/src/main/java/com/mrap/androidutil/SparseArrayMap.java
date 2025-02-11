package com.mrap.androidutil;

import android.util.SparseArray;

public class SparseArrayMap<K, V> {
  private SparseArray<K> keySparseArray = new SparseArray<>();
  private SparseArray<V> valSparseArray = new SparseArray<>();

  private int currIndexKey = 0;

  public void put(K key, V val) {
    for (int i = 0; i < keySparseArray.size(); i++) {
      if (keySparseArray.valueAt(i).equals(key)) {
        valSparseArray.put(keySparseArray.keyAt(i), val);
        return;
      }
    }
    keySparseArray.put(currIndexKey, key);
    valSparseArray.put(currIndexKey, val);
    currIndexKey++;
  }

  public V get(K key) {
    for (int i = 0; i < keySparseArray.size(); i++) {
      if (keySparseArray.valueAt(i).equals(key)) {
        return valSparseArray.get(keySparseArray.keyAt(i));
      }
    }
    return null;
  }

  public boolean contains(K key) {
    for (int i = 0; i < keySparseArray.size(); i++) {
      if (keySparseArray.valueAt(i).equals(key)) {
        return true;
      }
    }
    return false;
  }

  public K keyOfValue(V val) {
    for (int i = 0; i < valSparseArray.size(); i++) {
      if (valSparseArray.valueAt(i).equals(val)) {
        return keySparseArray.get(valSparseArray.keyAt(i));
      }
    }
    return null;
  }

  public V remove(K key) {
    for (int i = 0; i < keySparseArray.size(); i++) {
      if (keySparseArray.valueAt(i).equals(key)) {
        int indexKey = keySparseArray.keyAt(i);
        V val = valSparseArray.get(indexKey);
        keySparseArray.removeAt(i);
        valSparseArray.remove(indexKey);
        return val;
      }
    }
    return null;
  }

  public SparseArray<V> values() {
    return valSparseArray;
  }

  public SparseArray<K> keys() {
    return keySparseArray;
  }

  public int size() {
    return keySparseArray.size();
  }

  public void clear() {
    keySparseArray.clear();
    valSparseArray.clear();
  }
}
