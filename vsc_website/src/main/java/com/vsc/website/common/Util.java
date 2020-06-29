package com.vsc.website.common;

import com.vsc.website.entity.LoginTokens;
import org.apache.commons.lang.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

public class Util {

    static Properties properties = null;
    static Properties propertiesEN = null;
    static Properties propertiesTC = null;
    static String encryptPwd = "SDFDSF234L23KSDUWLJFLSDFJ";
    static DecimalFormat format = new DecimalFormat("0.00");


    /**
     * 确认是本人操作
     */
//    public static void assertOperator(Integer userId, Integer expectedUserId) {
//        if (!userId.equals(expectedUserId)) {
//            throw new ApiException(MessageCode.CODE_NO_PRIVILEGE);
//        }
//    }

    /**
     * URI参数编码
     */
    public static String encodeUriParam(String param) {
        try {
            if (param == null) {
                return null;
            }

//			return new String(param.getBytes("ISO-8859-1"), "UTF-8");
            return new String(param.getBytes("UTF-8"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 格式化数字，保留两位小数
     */
    public static String formatNumber(Number number) {
        if (number == null) {
            return "";
        }
        return format.format(number.doubleValue());
    }

    /**
     * 格式化数字，保留两位小数
     */
    public static String formatNumber(Double number) {
        return format.format(number);
    }

    public static <T> T getFromMap(Map map, Object key, Class<T> classT) {
        T result = (T) map.get(key);

        if (result == null) {
            try {
                result = classT.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            map.put(key, result);
        }

        return result;
    }

    public static String getConfig(String key) throws IOException {
        Properties configProperties = new Properties();
        configProperties.load(new InputStreamReader(Util.class.getResourceAsStream("/application.properties"), "UTF-8"));
        String message = configProperties.getProperty(key);
        return message;
    }

    public static void loadMessage() throws UnsupportedEncodingException, IOException {
        properties = new Properties();
        properties.load(new InputStreamReader(Util.class.getResourceAsStream("/message.properties"), "UTF-8"));
    }

    public static void loadMessageEN() throws UnsupportedEncodingException, IOException {
        propertiesEN = new Properties();
        propertiesEN.load(new InputStreamReader(Util.class.getResourceAsStream("/message_EN.properties"), "UTF-8"));
    }

    public static void loadMessageTC() throws UnsupportedEncodingException, IOException {
        propertiesTC = new Properties();
        propertiesTC.load(new InputStreamReader(Util.class.getResourceAsStream("/message_JP.properties"), "UTF-8"));
    }
    public static BigDecimal nullToZero(BigDecimal otherPayAmount) {
        return otherPayAmount == null ? BigDecimal.ZERO : otherPayAmount;
    }

    public static String getMessage(String key,Integer language, String... argv) {
        if (properties == null) {
            try {
                loadMessage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (propertiesEN == null) {
            try {
                loadMessageEN();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (propertiesTC == null) {
            try {
                loadMessageTC();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String message;
        if (language == null || language.equals(Constant.ENGLISH)) {
            message = propertiesEN.getProperty(key);
        } else if (language.equals(Constant.TRADITIONAL_CHINESE)) {
            message = propertiesTC.getProperty(key);
        } else {
            message = properties.getProperty(key);
        }

        if (argv != null) {
            for (String arg : argv) {
                message = message.replaceFirst("\\{[0-9]*\\}", arg);
            }
        }

        return message;
    }


    /**
     * 计算首条记录位置
     */
    public static int calFirst(int page, int size) {
        int first = (page - 1) * size;

        return first < 0 ? 0 : first;
    }


    /**
     * 四舍五入
     *
     * @param value
     * @return
     */
    public static Double round(Number value) {
        if (value == null) {
            return null;
        }

        return new BigDecimal(value.doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 四舍五入
     *
     * @param value
     * @return
     */
    public static BigDecimal roundToBigDecimal(Number value) {
        if (value == null) {
            return null;
        }

        return new BigDecimal(value.doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 四舍五入
     *
     * @param value
     * @return
     */
    public static Integer roundToInteger(Number value) {
        if (value == null) {
            return null;
        }

        return new BigDecimal(value.doubleValue()).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
    }

    /**
     * 验证集合
     */
    public static void validCollection(Validator validator, Collection collection, Errors errors) {
        for (Object object : collection) {
            ValidationUtils.invokeValidator(validator, object, errors);
        }
    }

    /**
     * 生成随机数
     */
    public static Integer getRandom(int size) {
        Double b = Math.random();
        b = b * size;
        return b.intValue();
    }

    /**
     * 生成随机数
     */
    public static List<Integer> getRandomList(int size, int num) {
        Map<Integer, Integer> map = new HashMap<>();
        if (size <= num) {
            while (map.size() < size) {
                int i = ((Double) (Math.random() * size)).intValue();
                map.put(i, i);
            }
        } else {
            while (map.size() < num) {
                int i = ((Double) (Math.random() * size)).intValue();
                map.put(i, i);
            }
        }
        return new ArrayList<>(map.keySet());
    }

    /**
     * 取得登录token
     */
    public static LoginTokens getLoginToken(HttpServletRequest request) {
        LoginTokens loginTokens = (LoginTokens) request.getAttribute("loginToken");
        return loginTokens;
    }

    public static void main(String[] argv) {
    }

    /**
     * 模糊查询特殊字符处理
     * @param str
     * @return
     */
    public static String replaceStrParam(String str){
        str= StringUtils.trimToNull(str);
        if (!StringUtils.isNotBlank(str)){
            return str;
        }
        str=str.replace("[","[[]");
        str=str.replace("%","[%]");
        str=str.replace("_","[_]");
        str=str.replace("/", "[/]");
        return str;
    }
}
