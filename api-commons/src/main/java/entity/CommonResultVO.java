package entity;
/**
 * @author  xtt
 * 封装返回结果的两种
 */

import enums.HttpStatus;

public class CommonResultVO {
    public static CommonResult success(Object data){
        CommonResult resultVO = new CommonResult();
        resultVO.setCode(HttpStatus.OK.getCode());
        resultVO.setMsg(HttpStatus.OK.getMessage());
        resultVO.setData(data);
        return resultVO;
    }

    public static CommonResult error(String error){
        CommonResult resultVO = new CommonResult();
        resultVO.setCode(HttpStatus.ERROR.getCode());
        resultVO.setMsg(error);
        resultVO.setData(null);
        return resultVO;
    }
}
