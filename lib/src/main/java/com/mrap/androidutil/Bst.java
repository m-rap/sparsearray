package com.mrap.androidutil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

public class Bst<T> {

  public class Node {
    final T data;
    Node left = null;
    Node right = null;

    public Node(T data) {
      this.data = data;
    }
  }

  public Node root = null;

  private Comparator<T> comparator = null;

  public void setComparator(Comparator<T> comparator) {
    this.comparator = comparator;
  }

  public void insert(T data) {
    Node n = new Node(data);
    if (root == null) {
      root = n;
      return;
    }
    Node curr = root;

    while (true) {
      int compRes = comparator.compare(data, curr.data);
      if (compRes < 0) {
        if (curr.left == null) {
          curr.left = n;
          break;
        }
        curr = curr.left;
      } else {
        if (curr.right == null) {
          curr.right = n;
          break;
        }
        curr = curr.right;
      }
    }
  }

  public void insertAll(Collection<T> dataList) {
    for (Iterator<T> it = dataList.iterator(); it.hasNext(); ) {
      T data = it.next();
      insert(data);
    }
  }

  public ArrayList<T> inOrder(Node root, int limit) {
    ArrayList<T> res = new ArrayList<>();
    Node curr = root;
    if (curr != null) {
      res.addAll(inOrder(curr.left, limit));
      if (limit >= 0 && res.size() >= limit) {
        for (int i = res.size() - 1; i > limit - 1; i--) {
          res.remove(i);
        }
        return res;
      }
      res.add(curr.data);
      if (limit >= 0 && res.size() >= limit) {
        for (int i = res.size() - 1; i > limit - 1; i--) {
          res.remove(i);
        }
        return res;
      }
      res.addAll(inOrder(curr.right, limit));
      if (limit >= 0 && res.size() >= limit) {
        for (int i = res.size() - 1; i > limit - 1; i--) {
          res.remove(i);
        }
        return res;
      }
    }
    return res;
  }
}
