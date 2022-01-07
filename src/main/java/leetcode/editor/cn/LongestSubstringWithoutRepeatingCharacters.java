package leetcode.editor.cn;

//给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。 
//
// 
//
// 示例 1: 
//
// 
//输入: s = "abcabcbb"
//输出: 3 
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
// 
//
// 示例 2: 
//
// 
//输入: s = "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
// 
//
// 示例 3: 
//
// 
//输入: s = "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
// 
//
// 示例 4: 
//
// 
//输入: s = ""
//输出: 0
// 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length <= 5 * 10⁴ 
// s 由英文字母、数字、符号和空格组成 
// 
// Related Topics 哈希表 字符串 滑动窗口 👍 6706 👎 0

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters{
    public static void main(String[] args) {
        Solution solution = new LongestSubstringWithoutRepeatingCharacters().new Solution();
        int r1 = solution.lengthOfLongestSubstring("abcabcbb");
        System.out.println("r1 = "+r1 +"   "+(r1 == 3));

        System.out.println("---------------------------");

        int r2 = solution.lengthOfLongestSubstring("bbbbb");
        System.out.println("r2 = "+r2 +"   "+(r2 == 1));

        System.out.println("---------------------------");

        int r3 = solution.lengthOfLongestSubstring("pwwkew");
        System.out.println("r3 = "+r3 +"   "+(r3 == 3));

        System.out.println("---------------------------");

        int r4 = solution.lengthOfLongestSubstring("");
        System.out.println("r4 = "+r4 +"   "+(r4 == 0));

        System.out.println("---------------------------");


        int r5 = solution.lengthOfLongestSubstring("dvdf");
        System.out.println("r5 = "+r5 +"   "+(r5==3));

        System.out.println("---------------------------");

        int r6 = solution.lengthOfLongestSubstring("nfpdmpi");
        System.out.println("r6 = "+r6 +"   "+(r6==5));

        System.out.println("---------------------------");

        int r7 = solution.lengthOfLongestSubstring("abba");
        System.out.println("r7 = "+r7 +"   "+(r7==2));


    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) return 0;
        int result = 0;
//        Set<Character> set = new HashSet<>();
//        int start = 0,end = 0;
//        int len = s.length();
//        char c;
//        for (int i = 0; i < len; i++) {
//            c = s.charAt(i);
//            boolean add = set.add(c);
//            if (!add) {
//                int index = s.substring(0, i).lastIndexOf(c);
//                start = Math.max(start,index + 1);
//            }
//            end = i;
//            result = Math.max(result,end - start + 1);
//        }
        int left = 0;
        char c;
        Map<Character,Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            if (map.containsKey(c)) {
                left = Math.max(left,map.get(c)+1);
            }
            map.put(c,i);
            result = Math.max(result,i-left+1);
        }
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}