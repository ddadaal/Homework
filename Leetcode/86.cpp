#include "cppincludes.h"

class Solution {
public:
	ListNode* partition(ListNode* head, int x) {
		ListNode dummy(0);
		ListNode* pivot = &dummy;
		dummy.next = head;
		ListNode* curr = head, *prev = &dummy;


		while (curr != nullptr) {
			ListNode* next = curr->next;
			if (curr->val < x) {
				if (pivot == prev) {
					pivot = pivot->next;
					prev = curr;
					curr = next;
				}
				else {
					prev->next = next;
					curr->next = pivot->next;
					pivot->next = curr;
					curr = next;
					pivot = pivot->next;
				}

			}
			else {
				prev = curr;
				curr = next;
			}

		}

		return dummy.next;

	}
};