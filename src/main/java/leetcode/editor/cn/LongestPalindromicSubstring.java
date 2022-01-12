package leetcode.editor.cn;

//给你一个字符串 s，找到 s 中最长的回文子串。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "babad"
//输出："bab"
//解释："aba" 同样是符合题意的答案。
// 
//
// 示例 2： 
//
// 
//输入：s = "cbbd"
//输出："bb"
// 
//
// 示例 3： 
//
// 
//输入：s = "a"
//输出："a"
// 
//
// 示例 4： 
//
// 
//输入：s = "ac"
//输出："a"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s 仅由数字和英文字母（大写和/或小写）组成 
// 
// Related Topics 字符串 动态规划 👍 4554 👎 0

import com.sun.deploy.util.StringUtils;

public class LongestPalindromicSubstring{
    public static void main(String[] args) {
        Solution solution = new LongestPalindromicSubstring().new Solution();
        String str = "babad";
        System.out.println("--r1--"+solution.longestPalindrome(str));

        str = "cbbd";
        System.out.println("--r2--"+solution.longestPalindrome(str));

        str = "a";
        System.out.println("--r3--"+solution.longestPalindrome(str));

        str = "ac";
        System.out.println("--r4--"+solution.longestPalindrome(str));

        str = "bb";
        System.out.println("--r5--"+solution.longestPalindrome(str));



    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     * 动态规划法
     * 执行耗时:169 ms,击败了38.43% 的Java用户
     * 内存消耗:42.4 MB,击败了31.09% 的Java用户
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 2) return s;
        int len = s.length();

        int start = 0,maxLen = 1;

        boolean[][] dp = new boolean[len][len];

        for (int j = 1; j < len; j++) {
            for (int i = 0; i < j; i++) {
                // j -i <= 2 字符串收尾相同，且长度小于等于2，直接返回true
                if (s.charAt(i) == s.charAt(j) && (j - i <= 2 || dp[i+1][j-1])) {
                    dp[i][j] = true;
                    if (j - i + 1  > maxLen) {
                        maxLen = j - i + 1;
                        start = i;
                    }
                }
            }

        }
        return s.substring(start,start+maxLen);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    class Test {
        // 暴力破解  Time Limit Exceeded
        public String method1(String s) {
            int length = s.length();
            if (length == 1) return s;


            String str = "";
            int maxLen = 0;
            for (int i = 0; i < length; i++) {
                for (int j = i+1; j <= length; j++) {
                    String sub = s.substring(i, j);
                    if (isPalindromic(sub) && sub.length() > maxLen) {
                        str = sub;
                        maxLen = sub.length();
                    }
                }
            }
            return str;
        }
        private boolean isPalindromic(String s) {
            if (s == null) {
                return false;
            }
            int len = s.length();
            for (int i = 0; i < len / 2; i++) {
                if (s.charAt(i) != s.charAt(len-i-1)) {
                    return false;
                }
            }
            return true;
        }

        /**
         * 扩展法
         * 回文数是对称，所以从中间向两边扩展
         * 时间复杂度 O(n²)
         * 空间复杂度 O(1)
         * 	执行耗时:25 ms,击败了81.27% 的Java用户
         * 	内存消耗:38.5 MB,击败了73.16% 的Java用户
         * @param s
         * @return
         */
        public String method2(String s) {
            if (null == s || s.length() < 1) return "";
            int start = 0,end = 0;
            for (int i = 0; i < s.length(); i++) {
                // 回文数是单数
                int len1 = expandAroundCenter(s,i,i);
                // 回文数是双数
                int len2 = expandAroundCenter(s,i,i+1);
                int len = Math.max(len1,len2);
                if (len > end - start + 1) {
                    start = i - (len - 1) / 2;
                    end = i + len / 2;
                }
            }

            return s.substring(start,end+1);
        }
        private int expandAroundCenter(String s, int left, int right) {
            int L = left, R = right;
            while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
                L--;
                R++;
            }
            return R - L - 1;
        }


    }



}