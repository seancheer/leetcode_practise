package org.seancheer.probability;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Given the API rand7() that generates a uniform random integer in the range [1, 7], write a function rand10() that generates a uniform random integer in the range [1, 10]. You can only call the API rand7(), and you shouldn't call any other API. Please do not use a language's built-in random API.
 * <p>
 * Each test case will have one internal argument n, the number of times that your implemented function rand10() will be called while testing. Note that this is not an argument passed to rand10().
 * <p>
 * Follow up:
 * <p>
 * What is the expected value for the number of calls to rand7() function?
 * Could you minimize the number of calls to rand7()?
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 1
 * Output: [2]
 * Example 2:
 * <p>
 * Input: n = 2
 * Output: [2,8]
 * Example 3:
 * <p>
 * Input: n = 3
 * Output: [3,8,10]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 105
 *
 * @author: seancheer
 * @date: 2021/9/2
 **/
public class _470ImplementRand10UsingRand7 {
    private static final Random random = new Random();

    public static void main(String[] args) {
        var obj = new _470ImplementRand10UsingRand7();
        int n = 1;
        var res = obj.callRand10(n);
        System.out.println("res:" + res);

        n = 2;
        res = obj.callRand10(n);
        System.out.println("res:" + res);

        n = 3;
        res = obj.callRand10(n);
        System.out.println("res:" + res);
    }

    /**
     * 解题思路：具体做法的如下，该做法可以保证再[0,39]范围内，所有数字出现的概率都是1/49，保证了[0,39]的概率都是一样的，
     * 那么直接对10取余，概率也是一样的，这样子就达到了目的
     * @return
     */
    public int rand10() {
       int result = 40;
       while(result >= 40){
           result = 7 * (rand7() - 1) + (rand7() - 1);
       }
       return result % 10 + 1;
    }


    /**
     * 测试代码，不需要关注
     * @param sum
     * @return
     */
    public List<Integer> callRand10(int sum) {
        int i = 0;
        List<Integer> res = new ArrayList<>();
        while (i++ < sum) {
            res.add(rand10());
        }
        return res;
    }

    private static int rand7() {
        return 1 + random.nextInt(7);
    }
}
