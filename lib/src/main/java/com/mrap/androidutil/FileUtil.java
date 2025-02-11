package com.mrap.androidutil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {
  public static final String MSG_DST_IS_DIR = "dst is a dir";
  public static final String MSG_SRC_IS_DIR = "src is a dir";

  public static boolean isSymlink(File file) throws IOException {
    File canon;
    if (file.getParent() == null) {
      canon = file;
    } else {
      File canonDir = file.getParentFile().getCanonicalFile();
      canon = new File(canonDir, file.getName());
    }
    return !canon.getCanonicalFile().equals(canon.getAbsoluteFile());
  }

  private static void copyFile(File src, File dst) throws IOException {
    FileInputStream is = new FileInputStream(src);
    FileOutputStream os = new FileOutputStream(dst);
    byte[] buffer = new byte[1024];
    int length;
    while ((length = is.read(buffer)) > 0) {
      os.write(buffer, 0, length);
    }
    is.close();
    os.close();
  }

  public static void copy(File src, File dst, boolean recursive) throws Exception {
    if (!src.exists()) throw new Exception("can't copy, src does not exist. src: " + src.getPath()
      + ", dst: " + dst.getPath());
    dst = dst.getAbsoluteFile();
    if (src.isFile()) {
      File dst2;
      if (!dst.exists()) {
        if (!dst.getParentFile().exists()) {
          throw new Exception("Invalid dst");
        } else {
          dst2 = dst;
        }
      } else {
        if (dst.isDirectory()) {
          dst2 = new File(dst, src.getName());
        } else {
          dst2 = dst;
        }
      }
      copyFile(src, dst2);
      return;
    }

    if (!recursive) {
      throw new Exception(MSG_SRC_IS_DIR);
    }

    File dst2;
    if (!dst.exists()) {
      if (dst.getParentFile().exists()) {
        dst2 = dst;
      } else {
        throw new Exception("Invalid dst");
      }
    } else {
      if (dst.isFile()) {
        throw new Exception("Dst is file");
      } else {
        dst2 = new File(dst, src.getName());
      }
    }

    if (!dst2.exists()) {
      if (!dst2.mkdir()) {
        throw new Exception("Can't create " + dst2.getName());
      }
    }

    String[] contents = src.list();
    if (contents != null) {
      for (String c : contents) {
        File childSrc = new File(src, c);
        copy(childSrc, dst2, true);
      }
    }
  }

  public static boolean delete(File dst, boolean recursive) throws Exception {
    if (dst.isDirectory()) {
      if (!recursive) {
        throw new Exception(MSG_DST_IS_DIR);
      }
      File[] contents = dst.listFiles();
      if (contents != null) {
        for (File f : contents) {
          try {
            if (!isSymlink(f)) {
              delete(f, true);
            } else {
              if (!f.delete()) {
                throw new Exception("Unknown error deleting dst");
              }
            }
          } catch (Exception e) {
            throw e;
          }
        }
      }
    }
    try {
      return dst.delete();
    } catch (Exception e) {
      throw e;
    }
  }

  public static void move(File src, File dst) throws Exception {
    if (!src.exists()) throw new Exception("can't move, src does not exist. src: " + src.getPath()
      + ", dst: " + dst.getPath());
    try {
      if (src.getCanonicalFile().equals(dst.getCanonicalFile())) {
        throw new Exception("Src and dst can't be same.");
      }
      if (!src.renameTo(dst)) {
        try {
          moveUsingCopyDel(src, dst);
        } catch (Exception e) { }
      }
    } catch (Exception e) {
      moveUsingCopyDel(src, dst);
    }
  }

  private static void moveUsingCopyDel(File src, File dst) throws Exception {
    if (!src.exists()) throw new Exception("can't copy, src does not exist. src: " + src.getPath()
      + ", dst: " + dst.getPath());
    copy(src, dst.getParentFile(), true);
    delete(src, true);
  }

  public static void forEach(File dir, CallbackWithException<File> consumer) throws Exception {
    File[] contents = dir.listFiles();
    for (File f : contents) {
      consumer.onCallback(f);
    }
  }
}
