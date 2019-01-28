package com.top.sample.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * 各种类型的正则匹配校验工具
 *
 * @author wangzhikang
 */
public class RegexMatchUtils {

    /**
     * 手机正则表达式
     */
    private static final Pattern PHONE_PATTERN = Pattern.compile("0?(13|14|15|16|17|18|19)[0-9]{9}");

    /**
     * 邮箱正则表达式
     */
    private static final Pattern EMAIL_PATTERN = Pattern.compile("\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}");

    /**
     * 有区号固定电话正则
     */
    private static final Pattern WITH_HEAD_FIXED_PHONE_PATTERN = Pattern.compile("^[0][1-9]{2,3}-[0-9]{5,10}$");

    /**
     * 无区号固定电话正则
     */
    private static final Pattern NO_HEAD_FIXED_PHONE_PATTERN = Pattern.compile("^[1-9][0-9]{5,8}$");

    /**
     * 身份证号码正则
     */
    private static final Pattern IDEATIFY_CARD_PATTERN = Pattern.compile("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0-2]\\d)|3[0-1])\\d{3}([\\d|x])$",
            Pattern.CASE_INSENSITIVE);

    /**
     * 省码表
     */
    private static final String[] PROVINCE_CODE = {"11", "12", "13", "14", "15", "21", "22",
            "23", "31", "32", "33", "34", "35", "36", "37", "41", "42", "43",
            "44", "45", "46", "50", "51", "52", "53", "54", "61", "62", "63",
            "64", "65", "71", "81", "82", "91"};

    /**
     * 身份证前十七位加权因子
     */
    private static final int[] POWER = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};

    /**
     * 最后一位校验位从1到10对应的校验位
     */
    private static final String[] REF_NUMBER = {"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};

    /**
     * 参与加权校验的位数
     */
    private static final int CHECK_COUNT = 17;

    public static boolean checkPhone(String phone) {
        return PHONE_PATTERN.matcher(phone).matches() ||
                NO_HEAD_FIXED_PHONE_PATTERN.matcher(phone).matches() ||
                WITH_HEAD_FIXED_PHONE_PATTERN.matcher(phone).matches();
    }

    public static boolean checkEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean checkIdentifyNo(String idCard) {
        boolean antecedent = IDEATIFY_CARD_PATTERN.matcher(idCard).matches();
        if (!antecedent) {
            return false;
        }
        return isValidProvince(idCard.substring(0, 2)) && isValidDate(idCard.substring(6, 14)) && checkLastNum(idCard);
    }

    /**
     * 判断身份证信息省份信息是否合法
     *
     * @param provinceCode
     * @return
     */
    private static boolean isValidProvince(String provinceCode) {
        for (String code : PROVINCE_CODE) {
            if (Objects.equals(provinceCode, code)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断身份证中间八位日期是否有效
     *
     * @param date
     * @return
     */
    private static boolean isValidDate(String date) {
        if (Objects.isNull(date)) {
            return false;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        if (date.trim().length() != dateFormat.toPattern().length()) {
            return false;
        }
        //日期格式严格匹配
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(date);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    /**
     * 校验身份证最后一位校验位
     *
     * @param identifyCard
     * @return
     */
    private static boolean checkLastNum(String identifyCard) {
        int result = 0;
        char[] idCardArray = identifyCard.toCharArray();
        for (int i = 0; i < CHECK_COUNT; i++) {
            result += POWER[i] * Integer.parseInt(idCardArray[i] + "");
        }
        String checkCode = REF_NUMBER[(result % 11)];
        String lastNum = identifyCard.charAt(CHECK_COUNT) + "";
        return checkCode.equalsIgnoreCase(lastNum);
    }
}
