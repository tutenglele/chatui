package cn.myweb.chat.ui.util;

public class AutoSizeTool {
    public static double getWidth(String msg) {
        int len = msg.length();
        double width = 0;
        for (int i = 0; i < len; i++) {
            if(isChinese(msg.charAt(i))) {
                width += 16;
            } else {
                width += 16;
            }
        }
        width += 22;
        if(width > 450) {
            return 450;
        }
        return width < 50 ? 50 : width;
    }
    public static double getHeight(String msg) {
        int len = msg.length();
        double height = 0;
        for (int i = 0; i < len; i++) {
            if(isChinese(msg.charAt(i))) {
                height += 16;
            } else {
                height += 16;
            }
        }
        height += 22;
        double remainder = height % 450;
        int line = (int) (height / 450);
        if(remainder != 0) {
            line++;
        }
        double autoHeight = line * 24 + 10;
        return autoHeight < 30 ? 30 : autoHeight;
    }
    private static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        return ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS;
    }
}
