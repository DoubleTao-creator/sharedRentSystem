package utils;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

/**
 * @author xtt
 * @date 2021/1/20
 * validation参数校验工具类
 */
public class ValidDataUtil {
    /**
     * 检验BindingResult是否有错误信息
     * @param result
     * @return 返回错误信息
     */
    public static String validData(BindingResult result){
        if (result.hasErrors()){
            StringBuffer sb=new StringBuffer();
            for(ObjectError error: result.getAllErrors()){
                sb.append(error.getDefaultMessage()+" ");
            }
            return String.valueOf(sb);
        }else{
            return null;
        }
    }
}
