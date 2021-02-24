package com.huazhao.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * Created with Intellij IDEA
 * Description:
 * User : 花朝
 * Date : 2021-02-24
 * Time : 18:54
 */
public class PinYinUtil {
    public static String getPinYin(String name) {
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        StringBuffer sb = new StringBuffer();
        for(char x : name.toCharArray()){
            try {
                String[] pinYinArray = PinyinHelper.toHanyuPinyinStringArray(x,format);
                if(pinYinArray == null || pinYinArray.length == 0){
                    sb.append(x);
                    continue;
                }
                sb.append(pinYinArray[0]);
            } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                sb.append(x);
            }
        }
        return sb.toString();
    }
    public static String getPinYinFirst(String name) {
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        StringBuffer sb = new StringBuffer();
        for(char x : name.toCharArray()){
            try {
                String[] pinYinArray = PinyinHelper.toHanyuPinyinStringArray(x,format);
                if(pinYinArray == null || pinYinArray.length == 0){
                    sb.append(x);
                    continue;
                }
                sb.append(pinYinArray[0].charAt(0));
            } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                sb.append(x);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(getPinYin("我爱你"));
        System.out.println(getPinYinFirst("我爱你"));
    }


}
