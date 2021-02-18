package cn.dark.stack;

import java.util.Stack;

/**
 * 使用递归函数逆序栈
 *
 * @author lwj
 * @date 2021-02-02
 */
public class ResverseStack {

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack);
        resverse(stack);
        System.out.println(stack);
    }

    public static void resverse(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }

        int i = getAndRemoveLastElement(stack);
        resverse(stack);
        stack.push(i);
    }

    public static int getAndRemoveLastElement(Stack<Integer> stack) {
        Integer result = stack.pop();
        if (stack.isEmpty()) {
            return result;
        }

        int last = getAndRemoveLastElement(stack);
        stack.push(result);
        return last;
    }

}
