package utils;
/**
 * @author xtt
 * @date 2021/1/18
 * @info MD5加密工具类
 */
import java.security.MessageDigest;
public class MD5Utils {

    private static String SALT="xtt";

    /**
     * MD5加密
     * @param password 待加密密码
     * @return 加密后密码
     */
    public static String encode(String password) {
        password = password + SALT;
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        char[] charArray = password.toCharArray();
        byte[] byteArray = new byte[charArray.length];
        for (int i = 0; i < charArray.length; i++){
            byteArray[i] = (byte) charArray[i];
        }
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    /**
     * 判断密码是否匹配
     * @param password 前端传的未加密的密码
     * @param encodedPassword 数据库中已加密的密码
     * @return true相同 false 不同
     */
    public Boolean matches(String password,String encodedPassword){
        if(encode(password).equals(encodedPassword)) {
            return true;
        }
        else {
            return false;
        }
    }
}