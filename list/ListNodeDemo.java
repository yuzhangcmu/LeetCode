package Algorithms.list;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

/**
 * REFS:
 * http://blog.csdn.net/fightforyourdream/article/details/16353519  
 * http://blog.csdn.net/luckyxiaoqiang/article/details/7393134 轻松搞定面试中的链表题目 
 * http://www.cnblogs.com/jax/archive/2009/12/11/1621504.html 算法大全（1）单链表 
 *  
 * 目录： 
 * 1. 求单链表中结点的个数: getListLength 
 * 2. 将单链表反转: reverseList（遍历），reverseListRec（递归） 
 * 3. 查找单链表中的倒数第K个节点（k > 0）: reGetKthNode 
 * 4. 查找单链表的中间结点: getMiddleNode 
 * 5. 从尾到头打印单链表: reversePrintListStack，reversePrintListRec（递归） 
 * 6. 已知两个单链表pHead1 和pHead2 各自有序，把它们合并成一个链表依然有序: mergeSortedList, mergeSortedListRec 
 * 7. 判断一个单链表中是否有环: hasCycle 
 * 8. 判断两个单链表是否相交: isIntersect 
 * 9. 求两个单链表相交的第一个节点: getFirstCommonNode 
 * 10. 已知一个单链表中存在环，求进入环中的第一个节点: detectCycle, getFirstNodeInCycleHashMap 
 * 11. 给出一单链表头指针pHead和一节点指针pToBeDeleted，O(1)时间复杂度删除节点pToBeDeleted: delete 
 *  
 */  

public class ListNodeDemo {
    
    // create the link Node class.
    // why use static:
    // http://duqiangcise.iteye.com/blog/697476
    private static class ListNode {
        int val;
        ListNode next;
        public ListNode(int val) {
            this.val = val;
            this.next = null;
        }
        
    }
    
    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        ListNode n6 = new ListNode(6);
        
        ListNode m1 = new ListNode(5);
        ListNode m2 = new ListNode(8);
        ListNode m3 = new ListNode(9);
        m1.next = m2;
        m2.next = m3;
        
        ListNode c1 = new ListNode(1);
        ListNode c2 = new ListNode(12);
        c1.next = c2;
        //c2.next = n1;
        
        ListNode mergeNode = mergeLink(m1, c1);
        //ListNode mergeNode2 = mergeLink(m1, c1);
        //ListNode mergeNode = mergeSortedList(m1, c1);
        printList(mergeNode);
        //printList(mergeNode2);
        
        System.out.println();
        
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        
        printList(n1);
        
        Delete(n1, n5);
        
        printList(n1);
        
        // create a cycle
        //n5.next = n3;
        //n5.next = n6;
        //n6.next = n4;
        
//        System.out.println(hasCycle(n1));
//        System.out.println(getListLength(n1));
//        printList(n1);
//        
//        //n1 = reverseList(n1);
//        n1 = reverseListRec(n1);
//        printList(n1);
        
//        ListNode ret = reGetKthNode(n1, 7);
//        if (ret != null) {
//            System.out.println(ret.val);
//        } else {
//            System.out.println("null");
//        }
        
        //reGetKthNodeRec(n1, 1);
//        reGetKthNodeRec2(n1, 3);
//        ListNode ret = getMiddleNode(n1);
//        
//        if (ret != null) {
//            System.out.println(ret.val);
//        }                
//        
//        reversePrintListStackRec(n1);
//        reversePrintListStack(n1);
//        reversePrintListRev(n1);
        
        //printList(n1);
        
        System.out.println(isIntersect(n1, c1));
        
        ListNode cross = getFirstCommonNode(n1, c1);
        if (cross == null) {
            System.out.println("null");
        } else {
            System.out.println(cross.val);
        }
        
        ListNode cross2 = getFirstNodeInCycleHashMap(c1);
        if (cross2 == null) {
            System.out.println("null");
        } else {
            System.out.println(cross2.val);
        }
    }
    
    public static void printList(ListNode head) {  
        while (head != null) {
            System.out.print(head.val + " ");         
            head = head.next;
        }
        
        System.out.println();
    } 
    
    // 获得ListNode 的长度
    public static int getListLength(ListNode head) {
        if (head == null) {
            return 0;
        }
        
        int len = 0;        
        while (head != null) {
            len++;
            head = head.next;
        }
        
        return len;
    }
    
    /*
     * 反转链表
     * 此算法亮点是：一次只需要处理把下一个节点指向curr，
     * 不断循环，即可完成任务
     * */
    public static ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        
        ListNode next = head.next;
        ListNode curr = head;
        
        // 先把头节点的下一个指定.
        curr.next = null;
        
        // 每次只需要将curr的next指向curr即可，
        // 终止条件是：next是null 表示我们将所有的节点进行了翻转
        while (next != null) {
            ListNode tmp = next.next;
            next.next = curr;
            curr = next;
            next = tmp;
        }
        
        return curr;
    }
    
    // 翻转递归（递归）  
    // 递归的精髓在于你就默认reverseListRec已经成功帮你解决了子问题了！但别去想如何解决的  
    // 现在只要处理当前node和子问题之间的关系。最后就能圆满解决整个问题。  
    /* 
         head 
            1 -> 2 -> 3 -> 4 
         
          head 
            1-------------- 
                                | 
                   4 -> 3 -> 2                    // Node reHead = reverseListRec(head.next); 
               reHead      head.next 
             
                   4 -> 3 -> 2 -> 1               // head.next.next = head; 
               reHead 
                
                    4 -> 3 -> 2 -> 1 -> null      // head.next = null; 
               reHead 
     */  
    public static ListNode reverseListRec(ListNode head) {
        // if there is no node, or only one node, just return;
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode reHead = reverseListRec(head.next); // 先求解子问题。
        head.next.next = head;  // 将head 与被解决的子串的尾部相连。
        head.next = null;       // head的下一个必须指向 null，因为head 是新的尾部.
        
        return reHead;
    }
    
    /** 
     * 查找单链表中的倒数第K个结点（k > 0） 
     * 最普遍的方法是，先统计单链表中结点的个数，然后再找到第（n-k）个结点。注意链表为空，k为0，k为1，k大于链表中节点个数时的情况 
     * 。时间复杂度为O（n）。代码略。 这里主要讲一下另一个思路，这种思路在其他题目中也会有应用。 
     * 主要思路就是使用两个指针，先让前面的指针走到正向第k个结点 
     * ，这样前后两个指针的距离差是k，之后前后两个指针一起向前走，前面的指针走到空时，后面指针所指结点就是倒数第k个结点
     * when k = 1, it should be the last node. 
     */  
    public static ListNode reGetKthNode(ListNode head, int k) {
        if (head == null) {
            return null;
        }        
        
        ListNode fast = head;
        
        // 使fast and slow之间差 k
        while (k > 0) {
            if (fast == null) {
                // 发生这种情况，说明k > sizeOfList.
                return null;
            }
            fast = fast.next;
            k--;            
        }
        
        while (fast != null) {
            fast = fast.next;
            head = head.next;
        }        
        
        return head;
    }
    
    /*
     * 递归打印出倒数第k个节点。
     * */
    static int level = 0;
    public static void reGetKthNodeRec(ListNode head, int k) {
        if (head == null) {
            return;
        }        
        
        reGetKthNodeRec(head.next, k);
        level++;
        if (level == k) {
            System.out.println(head.val);
        }        
    }
    
    /*
     * 递归打印出倒数第k个节点。
     * return: the length of the link.
     * 此为改进的递归算法，使用此算法，不需要加入辅助变量。
     * */
    public static int reGetKthNodeRec2(ListNode head, int k) {
        if (head == null) {
            return 0;
        }        
        
        int len = reGetKthNodeRec2(head.next, k);
        if (len == k - 1) {
            System.out.println(head.val);
        }
        
        return len + 1;
    }

    /** 
     * 判断一个单链表中是否有环 
     * 这里也是用到两个指针。如果一个链表中有环，也就是说用一个指针去遍历，是永远走不到头的。因此，我们可以用两个指针去遍历，一个指针一次走两步 
     * ，一个指针一次走一步，如果有环，两个指针肯定会在环中相遇。时间复杂度为O（n） 
     */  
    public static boolean hasCycle(ListNode head) {
        ListNode slow = head; // 快指针每次前进两步  
        ListNode fast = head; // 慢指针每次前进一步  
        
        // 如果fast没有到达尾部，那么slow也不会。所以不需要判定slow是不是null
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) { // 相遇，存在环
                return true;
            }
        }
        
        return false;
        
    }
        
    /*
     *  4. 查找单链表的中间结点: getMiddleNode
     *     这里只处理n个数为 奇数的情况
     *  我们可以设置2个 指针，一个快，一个慢
     *  1-2-3-null
     *  当fast前进2n时，它到达3，链表长度是2n + 1
     *  中间节点应为(2n+1)/2 + 1 = n + 1;
     *  所以，slow节点前进n恰好可以到达中间。
     *  
     *  边界:
     *  n = 1时，一开始就可以退出，仍然可以满足
     *  此算法特点：
     *  1->2->3->4
     *  返回2
     */     
    public static ListNode getMiddleNode(ListNode head) {
        if (head == null) {
            return null;
        }
        
        ListNode fast = head;
        ListNode slow = head;
        
        while (fast != null && fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;            
        }
        
        return slow;
    }
    
    /**
     * 5. 从尾到头打印单链表: reversePrintListStack，reversePrintListRec（递归）
     * @param head
     * @return
     */
    public static void reversePrintListStackRec(ListNode head) {
        if (head == null) {
            return;
        }
        
        // print the next first.
        reversePrintListStackRec(head.next);
        System.out.print(head.val + " ");
    }
    
    /** 
     * 从尾到头打印单链表 
     * 对于这种颠倒顺序的问题，我们应该就会想到栈，后进先出。所以，这一题要么自己使用栈，要么让系统使用栈，也就是递归。注意链表为空的情况 
     * 。时间复杂度为O（n）
     * 
     * 还有一种做法，是可以把链表反转，我们也可以从头到尾打印。
     */  
    public static void reversePrintListStack(ListNode head) {
        if (head == null) {
            return;
        }
        
        System.out.println();
        
        Stack<Integer> s = new Stack<Integer>();
        while (head != null) {
            s.push(head.val);
            head = head.next;
        }
        
        // print the next first.
        while (!s.isEmpty()) {
            System.out.print(s.pop() + " ");
        }
    }
    
    /** 
     * 从尾到头打印单链表 
     * 是可以把链表反转，我们也可以从头到尾打印。 为了不影响原有的链表，可以再反转回来
     */  
    public static void reversePrintListRev(ListNode head) {
        if (head == null) {
            return;
        }
        
        ListNode rev = reverseList(head);
        
        System.out.println();
        
        // print the next first.
        ListNode curr = rev;
        while (curr != null) {
            System.out.print(rev.val + " ");
            curr = curr.next;
        }
                
        System.out.println();
        
        //printList(rev);
        reverseList(rev);
    }
    
    /*
     * 6. 已知两个单链表pHead1 和pHead2 各自有序，把它们合并成一个链表依然有序: mergeSortedList, mergeSortedListRec
     * 
     * 与merge sort思想类似.
     * */
    public static ListNode mergeSortedList(ListNode head1, ListNode head2) {
        if (head1 == null) {
            return head2;
        } else if (head2 == null) {
            return head1;
        }
        
        // 作为头节点的前一个节点
        ListNode dummyNode = new ListNode(0);
        ListNode curr = dummyNode;
        while (head1 != null && head2 != null) {
            if (head1.val < head2.val) {
                curr.next = head1;
                head1 = head1.next;
            } else {
                curr.next = head2;
                head2 = head2.next;
            }
            
            curr = curr.next;
        }
        
        // 把没有取完的链直接接在结果上即可。
        if (head1 != null) {
            curr.next = head1;
        } else {
            curr.next = head2;
        }
        
        return dummyNode.next;
    } 
    
    static class Node {
        Node next;
        int val;
        Node (int val) {
            this.val = val;
        }
    } 
    
    public static ListNode mergeLink (ListNode aLink, ListNode bLink) {
        ListNode dummy = new ListNode(0);

        ListNode root = dummy;

        while (aLink != null && bLink != null) {
            if (aLink.val < bLink.val) {
                dummy.next = aLink;
                dummy = aLink;
                aLink = aLink.next;
                
            } else {
                dummy.next = bLink;
                dummy = bLink;
                bLink = bLink.next;
                
            }       
        }

        if (aLink != null) {
            dummy.next = aLink;
        } else {
            dummy.next = bLink;
        }

        return root.next;
    }
    
    /*
     * 先完成的算法，应该会简单一点儿。
     * 简直是优雅的算法啊！太棒了！只不过 为什么自己很难想出这么棒的算法呢？
     * 虽然递归的原理每个人都懂，但是写出漂亮的递归真的是考验功底呢。
     * 
     * 精髓就是: 在每一次的Merge的处理时，只需要考虑merge一个元素，也就是提取出一个元素，而下一步的merge，交给下一步的recursion
     * 来处理。
     */
    public static ListNode mergeSortedListRec(ListNode head1, ListNode head2) {
        if (head1 == null) {
            return head2;
        }
        
        if (head2 == null) {
            return head1;
        }
        
        ListNode headMerge = null;
        if (head1.val < head2.val) {
            headMerge = head1;
            head1.next = mergeSortedListRec(head1.next, head2);
        } else {
            headMerge = head2;
            head2.next = mergeSortedListRec(head1, head2.next);
        }
        
        return headMerge;
    } 
    
    
    // http://fisherlei.blogspot.com/2013/11/leetcode-linked-list-cycle-ii-solution.html
    // 更进一步，寻找环的起点，实际上，2点相遇后，我们再将某点放回起点，让它再走X的距离（X为起点到环的交点的距离）,
    // 即可让2个点相交在环的点处。
    public static ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) {
                ListNode n1 = head;
                while (true) {
                    if (n1 == slow) { // 注意，要选判断，再移动，
                        return n1;    // because 环的起点有可能就在开始的地方。
                    }
                    n1 = n1.next;
                    slow = slow.next;
                }
            }
        }
        
        return null;        
    }
    
    /*
     *  * 8. 判断两个单链表是否相交: isIntersect
     *  http://blog.csdn.net/yunzhongguwu005/article/details/11527675
     *  求两个单链表是否相交分三种情况讨论:
     *  1,如果两个链表一个有环，一个无环则一定不相交  
     *  2.如果都没有环，则判断两个链表的最后节点是否相同，如果相同则相交，不相同则不相交。
     *  3.如果都有环，则判断一个链表环里的节点是否是另一个链表环里的节点。如果是则相交，如果不是则不相交。
     *    
     *  步骤如下：
     *  1. 先判断2个链表有没有环。
     *  2. 如果都没有环，查最后节点是否相同
     *  3. 如果都有环，则将b链的环节点跑一圈，如果遇到A链中的节点，则返回true    
     */
    public static boolean isIntersect(ListNode head1, ListNode head2) {
        if (head1 == null || head2 == null) {
            return false;
        }
        
        ListNode head1C = hasCycleRetNode(head1);
        ListNode head2C = hasCycleRetNode(head2);
        
        // 两个链表都是有环的。
        if (head1C != null && head2C != null) {
            ListNode tmp = head1C;
            do {
                // 放在前面判断，因为有可能当前节点就是要求的结果
                if (head1C == head2C) {
                    return true;
                }
                
                // 跑一圈来找是不是同一个圈。
                head1C = head1C.next;
            } while (tmp != head1C);
            
            return false;
        // 两个链表都是没有环的
        } else if (head1C == null && head2C == null){
            while (head1.next != null) {
                head1 = head1.next;
            }
            
            while (head2.next != null) {
                head2 = head2.next;
            }
            
            // 无环的话，应该具有相同的尾节点.
            return head1 == head2;
        } else {
            return false;
        }
    }
    
    /**
     * 如果有环，返回在环内的某节点。否则返回null 
     */
    public static ListNode hasCycleRetNode(ListNode head) {
        if (head == null) {
            return head;
        }
        
        ListNode s = head;
        ListNode f = head;
        
        while (f != null && f.next != null) {
            f = f.next.next;
            s = s.next;
            if (f == s) {
                return f;
            }
        }
        
        return null;
    }
    
    /*
     * * 9. 求两个单链表相交的第一个节点: getFirstCommonNode
     * 分为2种情况：
     * 
     * 1. 没有环的情况.
     * 求两个单链表相交的第一个节点 对第一个链表遍历，计算长度len1，同时保存最后一个节点的地址。 
     * 对第二个链表遍历，计算长度len2，同时检查最后一个节点是否和第一个链表的最后一个节点相同，若不相同，不相交，结束。 
     * 两个链表均从头节点开始，假设len1大于len2 
     * ，那么将第一个链表先遍历len1-len2个节点，此时两个链表当前节点到第一个相交节点的距离就相等了，然后一起向后遍历，直到两个节点的地址相同。 
     * 时间复杂度，O(len1+len2) 
     *  
     *              ----    len2 
     *                   |__________ 
     *                   | 
     *       ---------   len1 
     *       |---|<- len1-len2
     *       
     * 2. 有环的情况
     *   （1）. 交点在环上
     *       这样子的话，实际上我们可以求出2个交点。我们只要判断2个交点是不是相等。不相等，把2个交点返回任何一个。
     *   （2）. 交点不在环上，则计算出环的交点，然后len1 = 起点至环的交点，len2 = 起点至环的交点，然后如方法1相同的做法。           
     */  
    public static ListNode getFirstCommonNode(ListNode head1, ListNode head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        
        ListNode cross1 = detectCycle(head1);
        ListNode cross2 = detectCycle(head2);
        
        // There is no CIRCLE
        if (cross1 == null && cross2 == null) {
            int len1 = getListLength(head1);
            int len2 = getListLength(head2);
            
            //将长的链截短
            if (len1 > len2) {
                while (len1 > len2) {
                    head1 = head1.next;
                    len1--;
                }
            } else {
                while (len2 > len1) {
                    head2 = head2.next;
                    len2--;
                }
            }
            
            while (head1 != null) {
                if (head1.next == head2.next) {
                    return head1.next;
                }
            }
            
            return null;
        } else if (cross1 != null && cross2 != null) {
            return cross1;
        }
        
        return null;
    }
    
    /** 
     * 求进入环中的第一个节点 用HashSet做 一个无环的链表，它每个结点的地址都是不一样的。 
     * 但如果有环，指针沿着链表移动，那这个指针最终会指向一个已经出现过的地址 以地址为哈希表的键值，每出现一个地址，
       只要出现重复的元素，就找到了环的交点.
     */ 
    public static ListNode getFirstNodeInCycleHashMap(ListNode head) {
        if (head == null) {
            return null;
        }
        
        HashSet<ListNode> h = new HashSet<ListNode>();
        while (head != null) {
            if (h.contains(head)) {
                return head;
            } else {
                h.add(head);
            }
            head = head.next;
        }
        
        return null;
    }
    
    /*
     * 对于删除节点，我们普通的思路就是让该节点的前一个节点指向该节点的下一个节点，这种情况需要遍历找到该节点的前一个节点，
     * 时间复杂度为O(n)。对于链表，链表中的每个节点结构都是一样的，所以我们可以把该节点的下一个节点的数据复制到该节点，
     * 然后删除下一个节点即可。要注意最后一个节点的情况，这个时候只能用常见的方法来操作，先找到前一个节点，
     * 但总体的平均时间复杂度还是O(1)。参考代码如下：
     * */
    public static void Delete(ListNode head, ListNode toBeDeleted) {
        if (head == null) {
            return;
        }
        
        if (toBeDeleted.next != null) {
            toBeDeleted.val = toBeDeleted.next.val;
            toBeDeleted.next = toBeDeleted.next.next;
        } else {
            while (head != null) {
                if (head.next == toBeDeleted) {
                    head.next = toBeDeleted.next;
                    return;
                }
                head = head.next;
            }
        }
        
        return;
    }
}
