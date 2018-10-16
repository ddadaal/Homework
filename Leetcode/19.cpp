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
	ListNode(int x) : val(x), next(nullptr) {}
	
};
class Solution {
public:
	ListNode* removeNthFromEnd(ListNode* head, int n) {
		ListNode *p = head, *nth = head, *prev = nullptr;
		for (int i = 0; i < n; i++) {
			p = p->next;
		}

		// [1,2] 1
		// 
		while (p != nullptr) {
			p = p->next;
			prev = nth;
			nth = nth->next;
		}

		if (prev == nullptr) {
			return head->next;
		}
		else {
			prev->next = nth->next;
			return head;
		}
	}
};