#include "cppincludes.h"

// double linked list and map<key, node>
struct Node {
  int key;
  int value;
  Node *next;
  Node *prev;

  Node(int key, int value)
      : key(key), value(value), next(nullptr), prev(nullptr) {}
};

class LRUCache {

  int capacity;
  unordered_map<int, Node *> listMap;
  Node *head;
  Node *tail;

public:
  LRUCache(int capacity) : capacity(capacity), head(nullptr), tail(nullptr) {}

  int get(int key) {
    if (listMap.find(key) != listMap.end()) {

      // key exists. update the access list
      Node *node = refresh(key);
      return node->value;
    } else {
      return -1;
    }
  }

  void removeHead() {
    if (head) {
      if (head->next) {
        head->next->prev = nullptr;
      }
      head = head->next;
    }
  }

  void appendNode(Node *node) {
    if (!head) {
      head = node;
      tail = node;
    } else {
      tail->next = node;
      node->prev = tail;
      tail = node;
    }
  }

  Node *refresh(int key) {
    Node *node = listMap[key];
    if (node != tail) {
      if (node == head) {
        removeHead();
      } else {
        node->prev->next = node->next;
        node->next->prev = node->prev;
      }
      appendNode(node);
      tail = node;
    }

    return node;
  }

  void put(int key, int value) {
    if (listMap.find(key) == listMap.end()) {

      Node *node = new Node(key, value);

      if (listMap.size() == capacity) {
        listMap.erase(head->key);
        removeHead();
      }
      appendNode(node);
      listMap[key] = node;

    } else {
      listMap[key]->value = value;
      refresh(key);
    }
  }
};
