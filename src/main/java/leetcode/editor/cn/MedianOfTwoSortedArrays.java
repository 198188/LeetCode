package leetcode.editor.cn;

//给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。 
//
// 算法的时间复杂度应该为 O(log (m+n)) 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums1 = [1,3], nums2 = [2]
//输出：2.00000
//解释：合并数组 = [1,2,3] ，中位数 2
// 
//
// 示例 2： 
//
// 
//输入：nums1 = [1,2], nums2 = [3,4]
//输出：2.50000
//解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
// 
//
// 示例 3： 
//
// 
//输入：nums1 = [0,0], nums2 = [0,0]
//输出：0.00000
// 
//
// 示例 4： 
//
// 
//输入：nums1 = [], nums2 = [1]
//输出：1.00000
// 
//
// 示例 5： 
//
// 
//输入：nums1 = [2], nums2 = []
//输出：2.00000
// 
//
// 
//
// 提示： 
//
// 
// nums1.length == m 
// nums2.length == n 
// 0 <= m <= 1000 
// 0 <= n <= 1000 
// 1 <= m + n <= 2000 
// -10⁶ <= nums1[i], nums2[i] <= 10⁶ 
// 
// Related Topics 数组 二分查找 分治 👍 4723 👎 0

import java.util.Arrays;

public class MedianOfTwoSortedArrays{
    public static void main(String[] args) {
        Solution solution = new MedianOfTwoSortedArrays().new Solution();
        int[] nums1 = {1,3};
        int[] nums2 = {2};
        double result1 = solution.findMedianSortedArrays(nums1, nums2);
        System.out.println("result1: "+result1);

        nums1 = new int[]{1,2};
        nums2 = new int[]{1,2,3,4,5,6,7,8,9,10};
        double result2 = solution.findMedianSortedArrays(nums1, nums2);
        System.out.println("result2: "+result2);

        nums1 = new int[]{1};
        nums2 = new int[]{0};
        double result3 = solution.findMedianSortedArrays(nums1, nums2);
        System.out.println("result3: "+result3);

        nums1 = new int[]{1,2};
        nums2 = new int[]{3,4};
        double result4 = solution.findMedianSortedArrays(nums1, nums2);
        System.out.println("result4: "+result4);

        nums1 = new int[]{0,0};
        nums2 = new int[]{0,0};
        double result5 = solution.findMedianSortedArrays(nums1, nums2);
        System.out.println("result5: "+result5);

//        int kthElement = solution.getKthElement(nums1, nums2, 3);
//        System.out.println(kthElement);


    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class  Solution {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int len1 = nums1.length;
            int len2 = nums2.length;
            int totalLen = len1 + len2;
            int k = (len1 + len2) / 2;
            if (totalLen % 2 == 0) {
                // 偶数 取中间2位数之和
                int result1 = getKthElement(nums1,nums2,k);
                int result2 = getKthElement(nums1,nums2,k+1);
                return (double) (result1 + result2)*0.5;
            } else {
                // 奇数 取中间值
                return getKthElement(nums1,nums2,k+1);
            }
        }

        public int getKthElement(int[] nums1, int[] nums2, int k) {
            int len1 = nums1.length, len2 = nums2.length;

            int k2 = 0;
            int startIndex1 = 0 ,startIndex2 = 0;
            while (true) {
                if (startIndex1 == len1) {
                    return nums2[startIndex2+k-1];
                }
                if (startIndex2 == len2) {
                    return nums1[startIndex1+k-1];
                }
                if (k == 1) {
                    return Math.min(nums1[startIndex1] , nums2[startIndex2]);
                }

                k2 = k/2;
                int n1 = startIndex1+Math.min(k2,len1);
                int n2 = startIndex2+Math.min(k2,len2);
                if (nums1[n1-1] > nums2[n2-1]) {
                    startIndex2 = n2;
                    k = k - Math.min(k2,len2);
                } else {
                    startIndex1 = n1;
                    k = k - Math.min(k2,len1);
                }

            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    class Solution1 {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int length1 = nums1.length, length2 = nums2.length;
            int totalLength = length1 + length2;
            if (totalLength % 2 == 1) {
                // 奇数，取中间一个值 下标从0开始
                int midIndex = totalLength / 2;
                double median = getKthElement(nums1, nums2, midIndex + 1);
                return median;
            } else {
                // 偶数
                int midIndex1 = totalLength / 2 - 1, midIndex2 = totalLength / 2;
                double median = (getKthElement(nums1, nums2, midIndex1 + 1) + getKthElement(nums1, nums2, midIndex2 + 1)) / 2.0;
                return median;
            }
        }
        public int getKthElement(int[] nums1, int[] nums2, int k) {
            /* 主要思路：要找到第 k (k>1) 小的元素，那么就取 pivot1 = nums1[k/2-1] 和 pivot2 = nums2[k/2-1] 进行比较
             * 这里的 "/" 表示整除
             * nums1 中小于等于 pivot1 的元素有 nums1[0 .. k/2-2] 共计 k/2-1 个
             * nums2 中小于等于 pivot2 的元素有 nums2[0 .. k/2-2] 共计 k/2-1 个
             * 取 pivot = min(pivot1, pivot2)，两个数组中小于等于 pivot 的元素共计不会超过 (k/2-1) + (k/2-1) <= k-2 个
             * 这样 pivot 本身最大也只能是第 k-1 小的元素
             * 如果 pivot = pivot1，那么 nums1[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums1 数组
             * 如果 pivot = pivot2，那么 nums2[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums2 数组
             * 由于我们 "删除" 了一些元素（这些元素都比第 k 小的元素要小），因此需要修改 k 的值，减去删除的数的个数
             */

            int length1 = nums1.length, length2 = nums2.length;
            int index1 = 0, index2 = 0;
            int kthElement = 0;

            while (true) {
                // 边界情况
                if (index1 == length1) {
                    return nums2[index2 + k - 1];
                }
                if (index2 == length2) {
                    return nums1[index1 + k - 1];
                }
                if (k == 1) {
                    return Math.min(nums1[index1], nums2[index2]);
                }

                // 正常情况
                int half = k / 2;
                int newIndex1 = Math.min(index1 + half, length1) - 1;
                int newIndex2 = Math.min(index2 + half, length2) - 1;
                int pivot1 = nums1[newIndex1], pivot2 = nums2[newIndex2];
                if (pivot1 <= pivot2) {
                    k -= (newIndex1 - index1 + 1);
                    index1 = newIndex1 + 1;
                } else {
                    k -= (newIndex2 - index2 + 1);
                    index2 = newIndex2 + 1;
                }
            }
        }
    }
}