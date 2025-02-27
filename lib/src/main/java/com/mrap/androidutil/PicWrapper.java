package com.mrap.androidutil;

public class PicWrapper {
  public static final int ORIENTATION_UNDEFINED = 0;
  public static final int ORIENTATION_NORMAL = 1;
  public static final int ORIENTATION_FLIP_HORIZONTAL = 2;
  public static final int ORIENTATION_ROTATE_180 = 3;
  public static final int ORIENTATION_FLIP_VERTICAL = 4;
  public static final int ORIENTATION_TRANSPOSE = 5;
  public static final int ORIENTATION_ROTATE_90 = 6;
  public static final int ORIENTATION_TRANSVERSE = 7;
  public static final int ORIENTATION_ROTATE_270 = 8;

  public byte[] data;
  public int orientation;
}
