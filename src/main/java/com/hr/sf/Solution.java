package com.hr.sf;

import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;
import java.sql.SQLOutput;
import java.util.*;

/**
 * @author herong22384
 * @date 2021/8/3 22:40
 */
public class Solution {

    /**
     * 寻找最小排序数组
     * */
    public int findUnsortedSubarray(int[] nums) {
        int length = nums.length;
        int [] tempNums = new int[length];
        for (int i = 0;i<length;i++){
            tempNums[i] = nums[i];
        }
        for(int i=0;i<length - 1;i++){
            for(int j=0;j<length -1 -i;j++){
                if(nums[j] > nums[j+1]){
                    int temp = nums[j+1];
                    nums[j+1] = nums[j];
                    nums[j] = temp;
                }
            }
        }
        int f = 0;
        for(int i=0;i < length;i++){
            if(tempNums[i] == nums[i]){
                f++;
            }else{
                break;
            }
        }
        int b = 0;
        for(int i=length-1;i>=0;i--){
            if(tempNums[i] == nums[i]){
                b++;
            }else{
                break;
            }
        }
        if(f == length || b==length){
            return 0;
        }
        return length - f - b;
    }

    /**
     * 罗马字符串转数字
     * */
    public int romanToInt(String s) {
        Map<Character,Integer> data = new HashMap();
        data.put('I',1);
        data.put('V',5);
        data.put('X',10);
        data.put('L',50);
        data.put('C',100);
        data.put('D',500);
        data.put('M',1000);
        int result = 0;
        int length = s.length();
        for(int i = 0;i<length;i++){
            int j = length - 1;
            if((i < j) && (data.get(s.charAt(i)) < data.get(s.charAt(i+1)))){
                result += data.get(s.charAt(i+1)) - data.get(s.charAt(i));
                i++;
            }else{
                result += data.get(s.charAt(i));
            }
        }
        return result;
    }

    /**
     * 合并两个有序链表
     * */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null){
            return l2;
        }
        if (l2 == null){
            return l1;
        }
        if (l1.val > l2.val){
            l2.next = mergeTwoLists(l1,l2.next);
            return l2;
        }else {
            l1.next = mergeTwoLists(l1.next,l2);
            return l1;
        }
    }

    /**
     * 搜索插入位置
     * */
    public int searchInsert(int[] nums, int target) {
        int result = 0;
        int length = nums.length;
        if(length == 0 || nums[0] >= target) return result;
        for(int i = 0;i<length;i++){
            if(nums[i] >= target){
                result = i;
            }
            if (result != 0) return result;
            if (i == length-1) result = length;
        }
        return result;
    }

    /**
     * 贪心算法解决最大连续子序和
     * */
    public int maxSubArray(int[] nums) {
        if (nums.length == 1){
            return nums[0];
        }
        int sum = Integer.MIN_VALUE;
        int count = 0;
        for (int i = 0; i < nums.length; i++){
            count += nums[i];
            // 取区间累计的最大值（相当于不断确定最大子序终止位置）
            sum = Math.max(sum, count);
            if (count <= 0){
                // 相当于重置最大子序起始位置，因为遇到负数一定是拉低总和
                count = 0;
            }
        }
        return sum;
    }

    /**
     * 最后一个单词长度
     * */
//    public int lengthOfLastWord(String s) {
//        s = s.trim();
//        int length = s.length();
//        int count =0;
//        for(int i =0;i<length;i++){
//            if(s.charAt(i) != ' '){
//                count ++;
//            }else{
//                count = 0;
//            }
//        }
//        return count;
//    }

    public int lengthOfLastWord(String s) {
        int length = s.length();
        int count =0;
        List<Integer> sum = new ArrayList();
        for(int i =0;i<length;i++){
            if(s.charAt(i) != ' '){
                count ++;
            }else if(count != 0){
                sum.add(count);
                count = 0;
            }
        }
        if(s.charAt(length -1) != ' '){
            return count;
        }else{
            return sum.get(sum.size() -1);
        }
    }

    /**
     * 数组加1
     * */
    public int[] plusOne(int[] digits) {
        int length = digits.length;
        for (int i = length -1;i>=0;i--){
            if (digits[i] != 9){
                digits[i] ++;
                return digits;
            }
            digits[i] = 0;
        }
        int[] result = new int[length + 1];
        result[0] = 1;
        return result;
    }

    /**
     * 二进制求和
     * */
    public String addBinary(String a, String b) {
        int alen = a.length();
        int blen = b.length();
        if (alen > blen){
            b = fillBit(b,alen - blen);
        }else {
            a = fillBit(a,blen - alen);
        }
        int pre = 0;
        StringBuffer buffer = new StringBuffer();
        for (int i =a.length() -1;i>=0;i--){
            int result = (a.charAt(i) - '0') + (b.charAt(i) - '0') + pre;
            if (result >= 2){
                result = result % 2;
                pre = 1;
            }else {
                pre = 0;
            }
            buffer.append(result);
        }
        if(pre == 1){
            buffer.append(1);
        }
        return buffer.reverse().toString();
    }

    public String fillBit(String s,int num){
        if (num > 0){
            StringBuffer buffer = new StringBuffer();
            for (int i =0;i<num;i++){
                buffer.append("0");
            }
            buffer.append(s);
            return buffer.toString();
        }
        return s;
    }

    /**
     * 二分法
     * x的平方根
     * */
    public int mySqrt(int x) {
        if (x == 0){
            return 0;
        }
        if (x == 1){
            return 1;
        }
        int left = 1,right = x/2;
        while(left < right){
            int middle = left + (right -left + 1)/2;
            if (middle > x/middle){
                right = middle -1;
            }else {
                left = middle;
            }
        }
        return left;
    }

    /**
     * 爬楼梯
     * 递归实现
     * */
    public int climbStairs1(int n) {
        if (n <= 2){
            return n;
        }
        return climbStairs(n-1) + climbStairs(n-2);
    }

    /**
     * 爬楼梯
     * 动态规划实现
     * */
    public int climbStairs(int n) {
        if(n<=2){
            return n;
        }
        int i1 = 1;
        int i2 = 2;
        for(int i=3;i<=n;i++){
            int temp = i1 + i2;
            i1 = i2;
            i2 = temp;
        }
        return i2;
    }

    /**
     * 斐波那契数列
     * 递归实现
     * */
    public int fb(int n){
        if (n <= 2) return 1;
        return fb(n-1) + fb(n-2);
    }

    /**
     * 斐波那契数列
     * 动态规划实现
     * */
    public int fb2(int n){
        if (n <= 2) return 1;
        int i1 = 1;
        int i2 = 1;
        for(int i=3;i<=n;i++){
            int temp = i1 + i2;
            i1 = i2;
            i2 = temp;
        }
        return i2;
    }

    /**
     * 删除有序链表中的重复元素
     * */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode pre = head;
        ListNode tail = head.next;
        while (pre != null && tail!= null){
            if (pre.val == tail.val){
                pre.next = tail.next;
            }else {
                pre = tail;
            }
            tail = tail.next;
        }
        return head;
    }

    /**
     * 寻找两个正序数组的中位数
     * */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length1 = nums1.length;
        int length2 = nums2.length;
        if (length1 == 0 && length2 == 0){
            return Double.valueOf("0");
        }else {
            //合并两个数组
            int[] nums = mergeArray(nums1,nums2);
            //找到数组中位数
            return findMedianSortedArrays(nums);
        }
    }

    public double findMedianSortedArrays(int[] nums) {
        int len = nums.length;
        if (len == 1){
            return Double.parseDouble(String.valueOf(nums[0]));
        }else if (len % 2 == 0){
            int index = len / 2;
            int result = nums[index] + nums[index -1];
            Double middle = Double.parseDouble(String.valueOf(result)) / 2;
            return middle;
        }else {
            int index = len / 2;
            return Double.parseDouble(String.valueOf(nums[index]));
        }
    }

    public int[] mergeArray(int[] nums1, int[] nums2){
        int length1 = nums1.length;
        int length2 = nums2.length;
        if (length1 == 0){
            return nums2;
        }
        if (length2 == 0){
            return nums1;
        }
        List<Integer> result = new ArrayList<Integer>();
        for (int i = 0;i<length1;i++){
            result.add(nums1[i]);
        }
        for (int i = 0;i<length2;i++){
            result.add(nums2[i]);
        }
        Collections.sort(result);
        int[] nums = new int[length1+length2];
        for (int i =0;i<result.size();i++){
            nums[i] = Integer.parseInt(String.valueOf(result.get(i)));
        }
        return nums;
    }

    /**
     * 寻找最长回文子串
     * */
    public String longestPalindrome(String s) {
        int len = s.length();
        if (len == 0){
            return "";
        }
        if (len == 1){
            return s;
        }
        int max = 0;
        int begin = 0,end=0;
        for (int i =0;i<len;i++){
            for (int j = max+1;j<len;j++){
                int sub = j-i;
                if (sub <= max){
                    continue;
                }
                String temp = s.substring(i,j+1);
                if(palindrome(temp)){
                    max = sub;
                    begin = i;
                    end = j;
                }
            }
        }
        return s.substring(begin,end+1);
    }

    public Boolean palindrome(String s){
        if (s.equals(new StringBuilder(s).reverse().toString())){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        Map<String,Integer> data = new HashMap();
        System.out.println(s.findUnsortedSubarray(new int[]{2,6,4,8,10,9,15}));

        s.romanToInt("MCMXCIV");

        //输入：l1 = [1,2,4], l2 = [1,3,4]
        //输出：[1,1,2,3,4,4]
        ListNode node3 = new ListNode(4);
        ListNode node2 = new ListNode(2,node3);
        ListNode node1 = new ListNode(1,node2);

        ListNode node33 = new ListNode(4);
        ListNode node22 = new ListNode(3,node33);
        ListNode node11 = new ListNode(1,node22);

        ListNode result = s.mergeTwoLists(node1,node11);

        System.out.println("---"+s.searchInsert(new int[]{1,3,5,6},2));

        System.out.println("========="+s.lengthOfLastWord("   fly me   to   the moon  "));

        System.out.println("=========" + JSONObject.toJSON(s.plusOne(new int[]{9})));

        System.out.println("=========" + s.addBinary("1","10"));

        System.out.println("爬楼梯"+s.climbStairs(2));

        System.out.println("斐波拉契数列："+s.fb2(6));

        System.out.println("删除有序链表中的重复元素："+JSONObject.toJSON(s.deleteDuplicates(result)));

        System.out.println("寻找数组中的中位数："+s.findMedianSortedArrays(new int[]{0,7},new int[]{2,6}));

        System.out.println("最长回文子串："+s.longestPalindrome("abab"));
    }

    public static class ListNode{
        int val;
        ListNode next;
        ListNode(){}
        ListNode(int val){
            this.val = val;
        }
        ListNode(int val,ListNode next){
            this.val = val;
            this.next = next;
        }
    }
}
