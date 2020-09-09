package cn.dark.backtracing;

/**
 * 正则匹配
 *
 * @author lwj
 * @date 2020-01-17
 */
public class Pattern {

    private static char[] pattern;
    private static int plen;
    private static boolean matched = false;

    public static void main(String[] args) {
        System.out.println(match("f?a*ae*", "f1a,sfdaee"));
    }

    private static boolean match(String patternStr, String text) {
        pattern = patternStr.toCharArray();
        plen = patternStr.length();
        match(0, 0 , text.toCharArray(), text.length());
        return matched;
    }

    private static void match(int ti, int pi, char[] text, int tlen) {
        if (matched) {
            return;
        }

        if (plen == pi) {
            if (tlen == ti) {
                matched = true;
            }
            return;
        }

        if (pattern[pi] == text[ti]) {
            // 纯字符匹配
            match(ti + 1, pi + 1, text, tlen);
        } else if (pattern[pi] == '*') {
            // * 匹配任意个字符，注意这里是<=，因为tlen == ti时才会设置matched=true
            for (int i = ti; i <= tlen; i++) {
                match(i, pi + 1, text, tlen);
            }
        } else if (pattern[pi] == '?') {
            // ？ 匹配至多一个字符
            match(ti, pi + 1, text, tlen);
            match(ti + 1, pi + 1, text, tlen);
        }
    }

}
