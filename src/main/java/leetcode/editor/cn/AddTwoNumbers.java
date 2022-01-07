package leetcode.editor.cn;

//给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
//
// 请你将两个数相加，并以相同形式返回一个表示和的链表。
//
// 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
//
//
//
// 示例 1：
//
//
//输入：l1 = [2,4,3], l2 = [5,6,4]
//输出：[7,0,8]
//解释：342 + 465 = 807.
//
//
// 示例 2：
//
//
//输入：l1 = [0], l2 = [0]
//输出：[0]
//
//
// 示例 3：
//
//
//输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
//输出：[8,9,9,9,0,0,0,1]
//
//
//
//
// 提示：
//
//
// 每个链表中的节点数在范围 [1, 100] 内
// 0 <= Node.val <= 9
// 题目数据保证列表表示的数字不含前导零
//
// Related Topics 递归 链表 数学 👍 7307 👎 0

public class AddTwoNumbers {
    public static void main(String[] args) {
        Solution solution = new AddTwoNumbers().new Solution();
        int[] i1 = new int[]{2,4};
        int[] i2 = new int[]{5,6,4};
        ListNode node1 = new ListNode(i1);
        ListNode node2 = new ListNode(i2);
        ListNode listNode = solution.addTwoNumbers(node1, node2);

//        ListNode node = new ListNode(0);
//        ListNode listNode = solution.addTwoNumbers(node, node);



//        ListNode convert = solution.convert(465);
//        System.out.println(convert);

        System.out.println(listNode);

    }

//leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode result = new ListNode();
            ListNode preNode = result;
            int jin = 0;

            while (l1 != null || l2 != null) {
                int val1 = l1 == null ? 0 : l1.val;
                int val2 = l2 == null ? 0 : l2.val;
                int sum = val1 + val2 + jin;

                jin = sum > 9 ? 1 : 0;
                sum = sum % 10;

                preNode.next = new ListNode(sum);
                preNode = preNode.next;


                if (l1 != null) {
                    l1 = l1.next;
                }
                if (l2 != null) {
                    l2 = l2.next;
                }
            }
            if (jin > 0) {
                preNode.next = new ListNode(jin);
            }

            return result.next;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        ListNode(int[] node) {
            if (node.length != 0) {
                this.val = node[0];
                ListNode preNode = this;
                for (int i = 1; i < node.length; i++) {
                    preNode.next  = new ListNode(node[i]);
                    preNode = preNode.next;
                }

            }
        }


        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }
}