package com.login.login_test.service;

import com.login.login_test.model.response.CommonResponse;
import com.login.login_test.model.response.CommonResult;
import com.login.login_test.model.response.ListResult;
import com.login.login_test.model.response.SingleResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponseService {

    //단일건 결과를 처리하는 메소드
    public <T> SingleResult<T> getSingleResult(T data) {
        SingleResult<T> result = new SingleResult<>();
        result.setData(data);
        setSuccessResult(result);
        return result;
    }
    //다중건 결과를 처리하는 메소드
    public <T> ListResult<T> getListResult(List<T> list) {
        ListResult<T> result = new ListResult<>();
        result.setList(list);
        setSuccessResult(result);
        return result;
    }

    //성공 결과만 처리하는 메소드
    public CommonResult getSuccessResult() {
        CommonResult result = new CommonResult();
        setSuccessResult(result);
        return result;
    }
    //실패 결과만 처리하는 메소드
    public CommonResult getFailResult(int code, String msg) {
        CommonResult result = new CommonResult();
        setFailResult(result, code, msg);
        return result;
    }

    //결과 모델에 api 요청 성공데이터를 세팅해주는 메소드
    private void setSuccessResult(CommonResult result) {
        result.setSuccess(true);
        result.setCode(CommonResponse.SUCCESS.getCode());
        result.setMsg(CommonResponse.SUCCESS.getMsg());
    }
    //결과 모델에 api 요청 실패데이터를 세팅해주는 메소드
    private void setFailResult(CommonResult result, int code, String msg) {
        result.setSuccess(false);
        result.setCode(code);
        result.setMsg(msg);
    }


}
