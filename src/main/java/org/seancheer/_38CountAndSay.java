package org.seancheer;

import java.util.HashMap;
import java.util.Map;

/**
 * 38. Count and Say
 * The count-and-say sequence is the sequence of integers with the first five terms as following:
 *
 * 1.     1
 * 2.     11
 * 3.     21
 * 4.     1211
 * 5.     111221
 * 1 is read off as "one 1" or 11.
 * 11 is read off as "two 1s" or 21.
 * 21 is read off as "one 2, then one 1" or 1211.
 *
 * 题意有点难以理解，可以参考这里的解释：https://blog.csdn.net/xygy8860/article/details/46821417
 */
public class _38CountAndSay {
    public static void main(String[] args){
        _38CountAndSay obj = new _38CountAndSay();
        String res = obj.countAndSay(1);
        System.out.println("res: "+ res);

        res = obj.countAndSay(2);
        System.out.println("res: "+ res);

        res = obj.countAndSay(4);
        System.out.println("res: "+ res);

        res = obj.countAndSay(5);
        System.out.println("res: "+ res);

    }

    private static Map<Integer, String> seqMap = new HashMap<>();
    /**
     * @param n
     * @return
     */
    public String countAndSay(int n) {
        if (1 == n){
            seqMap.put(1, "1");
            return "1";
        }

        if (2 == n){
            seqMap.put(2, "11");
            return "11";
        }

        String res = seqMap.get(n);
        if (null != res){
            return res;
        }

        int i = n;
        for(i = n;i >= 1;--i){
            if (seqMap.get(i) != null){
                break;
            }
        }

        for(i = i + 1;i <= n;++i){
            StringBuilder sb = new StringBuilder();
            //get previous
            String tmp = seqMap.get(i - 1);
            int counter = 1;
            char prev = tmp.charAt(0);
            for (int j = 1; j < tmp.length(); ++j) {
                if (tmp.charAt(j) == prev) {
                    ++counter;
                } else {
                    sb.append(counter).append(prev);
                    prev = tmp.charAt(j);
                    counter = 1;
                }
            }
            sb.append(counter).append(prev);
            seqMap.put(i, sb.toString());
        }

        return seqMap.get(n);
    }
}
