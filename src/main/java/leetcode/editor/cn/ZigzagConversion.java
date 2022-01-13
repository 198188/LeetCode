package leetcode.editor.cn;

//将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。 
//
// 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下： 
//
// 
//P   A   H   N
//A P L S I I G
//Y   I   R 
//
// 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。 
//
// 请你实现这个将字符串进行指定行数变换的函数： 
//
// 
//string convert(string s, int numRows); 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "PAYPALISHIRING", numRows = 3
//输出："PAHNAPLSIIGYIR"
// 
//示例 2：
//
// 
//输入：s = "PAYPALISHIRING", numRows = 4
//输出："PINALSIGYAHRPI"
//解释：
//P     I    N
//A   L S  I G
//Y A   H R
//P     I
// 
//
// 示例 3： 
//
// 
//输入：s = "A", numRows = 1
//输出："A"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s 由英文字母（小写和大写）、',' 和 '.' 组成 
// 1 <= numRows <= 1000 
// 
// Related Topics 字符串 👍 1438 👎 0

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ZigzagConversion{
    public static void main(String[] args) {
        Solution solution = new ZigzagConversion().new Solution();
        Test test = new ZigzagConversion().new Test();
        String s = "PAYPALISHIRING";
        test.print(s,3);
        System.out.println("result: "+solution.convert(s, 3));
        System.out.println("------------------");

        s = "A";
        test.print(s,1);
        System.out.println("result: "+solution.convert(s, 1));
        System.out.println("------------------");



    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     * 执行耗时:5 ms,击败了74.98% 的Java用户
     * 内存消耗:39.1 MB,击败了33.08% 的Java用户
     * @param s 字符串
     * @param numRows 行数
     * @return
     */
    public String convert(String s, int numRows) {
        if (numRows == 1) return s;
        // 定义一个列表，存储每行的字符串
        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < Math.min(numRows, s.length()); i++) {
            rows.add(new StringBuilder());
        }
        // 移动方向，选择当前行的StringBuilder对象
        boolean directional = false;
        // 当前行
        int curRow = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // 在当前行上拼接字符串
            rows.get(curRow).append(c);
            // 根据方向判断当前行是否前和后退
            curRow += directional?-1:1;
            // 当前行为0和最后一行时，改变方向
            if (curRow  == 0 || curRow == numRows-1) directional = !directional;
        }
        StringBuilder builder = new StringBuilder();
        for (StringBuilder row : rows) {
            builder.append(row);
        }
        return builder.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
    class Test {

    /**
     * 找规律
     * 执行耗时:2 ms,击败了99.85% 的Java用户
     * 	内存消耗:38.6 MB,击败了81.93% 的Java用户
     * @param s
     * @param numRows
     * @return
     */
        public String method1(String s, int numRows) {
            if (numRows == 1) return s;
            StringBuilder builder = new StringBuilder();
            int length = s.length();
            // 按照V形状进行分组，分组中最大个数
            int groupNum = 2 * numRows - 2;
            // 分组组数
            int group;
            if (length % groupNum == 0) {
                group = length / groupNum;
            } else {
                group = length / groupNum + 1;
            }
            // 按照行数进行遍历
            for (int i = 0; i < numRows; i++) {
                // 特殊情况 第一行和最后一行，每组中只取第一个数
                if (i == 0 || i == numRows - 1) {
                    int index = i;
                    // 获取每个分组的第一个数，直到下标超过字符串长度结束。
                    while (index < length) {
                        char c = s.charAt(index);
                        builder.append(c);
                        index = index + groupNum;
                    }
                } else {
                    // 中间情况，从每个分组中获取2个字符串下标
                    for (int j = 0; j < group; j++) {
                        char c;
                        // 获取第一个下标
                        // 行数 + 第几个分组 * 每个分组的个数
                        int index = i + j * groupNum;
                        if (index < length) {
                            c = s.charAt(index);
                            builder.append(c);
                        }
                        // 获取第二个下标
                        // 第一个下标 + 当前行数到倒数第二行之间的行数 * 每个分组每行的2个下标
                        index += (numRows - i - 1) * 2;
                        if (index < length) {
                            c = s.charAt(index);
                            builder.append(c);
                        }

                    }
                }

            }
            return builder.toString();
        }


        public void print(String s, int numRows) {
            if (numRows == 1) {
                System.out.println(s);
                return;
            }
            int length = s.length();
            int lenX = 0;
            int group = 2 * numRows - 2;
            int a = length % group;
            if (a == 0) {
                lenX = length / group * (numRows -1);
            } else {
                lenX = length / group * (numRows -1) + (a > numRows?a-numRows+1:1);
            }
            char[][] c = new char[lenX][numRows];

            for (int i = 0; i < length; i++) {
                int i1 = i % group;
                int i2 = i / group;

                int x = i2 * (numRows -1);
                int y = i1;
                if (i1 >= numRows) {
                    x = x + i1 - numRows + 1;
                    y = (numRows - 2) - (i1 - numRows);
                }
                c[x][y] = s.charAt(i);
            }
            print(c);
        }

        public void print(char[][] c) {
            int lenX = c.length;
            int lenY = c[0].length;
            for (int i = 0; i < lenY; i++) {
                for (int j = 0; j < lenX; j++) {
                    char c1 = c[j][i];
                    if (c1 == '\0') {
                        System.out.print("  ");
                    } else {
                        System.out.print(c1+" ");
                    }
                }
                System.out.println();
            }
        }
}

}