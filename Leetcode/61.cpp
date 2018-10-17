#include <vector>
#include <map>
#include <algorithm>
#include <iostream>
using namespace std;
/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 */

struct ListNode {
	int val;
	ListNode *next;
	ListNode(int x) : val(x), next(NULL) {}
	
};
class Solution {

	int count(ListNode* head) {
		int length = 0;
		while (head != NULL) {
			length++;
			head = head->next;
		}
		return length;
	}
public:
	ListNode* rotateRight(ListNode* head, int k) {
		int len = count(head);
		if (len < 2 ) {
			return head;
		}
		k %= len;
		if (k == 0) {
			return head;
		}

		ListNode dummyHead(0);
		dummyHead.next = head;

		ListNode* tail = head, *tailPrev = &dummyHead, *kth = head, *kthPrev = &dummyHead;
		for (int i = 0; i < k; i++) {
			tail = tail->next;
		}
		while (tail != NULL) {
			tailPrev = tail;
			tail = tail->next;
			kthPrev = kth;
			kth = kth->next;
		}

		tailPrev->next = head;
		kthPrev->next = NULL;
		return kth;
		

	}
};