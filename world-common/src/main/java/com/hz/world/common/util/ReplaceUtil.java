package com.hz.world.common.util;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReplaceUtil {

    private static final String regExScript = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式
    private static final String regExStyle = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
    private static final String regExHtml = "<[^>]+>"; // 定义HTML标签的正则表达式
    //private static final String regExSpace = "\\s*|\t|\r|\n";// 定义空格回车换行符

    // 1)过滤 ‘ 2)过滤ORACLE 注解 --  /**/ 3)过滤关键字过滤 update ,delete
    private static final String regSql = "(?:')|(?:--)|(/\\*(?:.|[\\n\\r])*?\\*/)|"
            + "(\\b(select|update|and|or|delete|insert|trancate|char|into|substr|ascii|declare|exec|count|master|into|drop|execute|create|table|database)\\b)";   // 定义sql注入表达式

    /**
     * 需要替换的内容
     *
     * @param content
     * @return
     */
    public static String replaceHtmlTag(String content) {
        Pattern pScript = Pattern.compile(regExScript, Pattern.CASE_INSENSITIVE);
        Matcher mScript = pScript.matcher(content);
        content = mScript.replaceAll(""); // 过滤script标签

        Pattern pStyle = Pattern.compile(regExStyle, Pattern.CASE_INSENSITIVE);
        Matcher mStyle = pStyle.matcher(content);
        content = mStyle.replaceAll(""); // 过滤style标签

        Pattern pHtml = Pattern.compile(regExHtml, Pattern.CASE_INSENSITIVE);
        Matcher mHtml = pHtml.matcher(content);
        content = mHtml.replaceAll(""); // 过滤html标签

        Pattern pSql = Pattern.compile(regSql, Pattern.CASE_INSENSITIVE);
        Matcher mSql = pSql.matcher(content); // 过虑sql标记
        content = mSql.replaceAll("");

        //Pattern pSpace = Pattern.compile(regExSpace, Pattern.CASE_INSENSITIVE);
        //Matcher mSpace = pSpace.matcher(content);
        //content = mSpace.replaceAll(""); // 过滤空格回车标签
        return content.trim(); // 返回文本字符串

    }

    /**
     * 替换掉非法字符
     *
     * @param content
     * @return
     */
    public static final String replaceIllegalWord(String content) {
        char c;
        String hexStr;
        StringBuilder targetStr = new StringBuilder();

        if (StringUtils.isEmpty(content)) {
            return "";
        }

        content = content.replaceAll("[/|&|#|$|%|\\||'|\"|{}|\\\\]+|客服|巡管|管理|运营|系统管理|场控|官方|管理员", "");

        for (int i = 0, length = content.length(); i < length; i++) {
            c = content.charAt(i);

            if ('\\' == c || '%' == c || '<' == c || '>' == c || '\n' == c) {
                continue;
            }

            hexStr = Integer.toHexString(c).toLowerCase();
            if (hexStr.equals("202e") // 倒序字符
                    || (hexStr.compareTo("e772") >= 0 && hexStr.compareTo("e7fe") <= 0)) { // 输入法v4、v5、v...
                // 输入的空格
                continue;
            }
            targetStr.append(c);
        }
        return targetStr.toString();
    }
}