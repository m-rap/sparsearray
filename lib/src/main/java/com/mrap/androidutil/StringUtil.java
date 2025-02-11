package com.mrap.androidutil;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class StringUtil {
  public static String stringJoin(String delimiter, Collection<? extends CharSequence> vals) {
    StringBuilder sb = new StringBuilder();
    int len = vals.size();
    Iterator iter = vals.iterator();
    for (int i = 0; i < len; i++) {
      sb.append(iter.next());
      if (i < len - 1) {
        sb.append(delimiter);
      }
    }
    return sb.toString();
  }

  public static String numJoin(String delimiter, ArrayList<? extends Number> list) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < list.size(); i++) {
      sb.append(list.get(i));
      if (i < list.size() - 1) {
        sb.append(delimiter);
      }
    }
    return sb.toString();
  }

  public static String numJoinArr(String delimiter, int[] numArr) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < numArr.length; i++) {
      sb.append(numArr[i] + "");
      if (i < numArr.length - 1) {
        sb.append(delimiter);
      }
    }
    return sb.toString();
  }

  private static final byte[] HEX_ARRAY = "0123456789ABCDEF".getBytes(StandardCharsets.US_ASCII);
  public static String bytesToHex(byte[] bytes) {
    byte[] hexChars = new byte[bytes.length * 2];
    for (int j = 0; j < bytes.length; j++) {
      int v = bytes[j] & 0xFF;
      hexChars[j * 2] = HEX_ARRAY[v >>> 4];
      hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
    }
    return new String(hexChars, StandardCharsets.UTF_8);
  }

  public static ArrayList<String> numArrToStrList(int[] numArr) {
    ArrayList<String> strList = new ArrayList<>();
    for (int i = 0; i < numArr.length; i++) {
      strList.add(numArr[i] + "");
    }
    return strList;
  }

  public static int[] numListToNumArr(List<Integer> numList) {
    int length = numList.size();
    int[] numArr = new int[length];
    for (int i = 0; i < length; i++) {
      numArr[i] = numList.get(i);
    }
    return numArr;
  }
}
