package com.login.login_test.controller.v1;


import com.login.login_test.domain.User;
import com.login.login_test.model.response.CommonResult;
import com.login.login_test.model.response.ListResult;
import com.login.login_test.model.response.SingleResult;
import com.login.login_test.repository.UserJpaRepository;
import com.login.login_test.service.ResponseService;
import com.login.login_test.service.UserService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"2. USER"})
@RequiredArgsConstructor
@RestController //결과값을 json으로 출력
@RequestMapping(value = "/v1")
public class UserController {
    private final ResponseService responseService;    //결과를 처리할 서비스
    private final UserService userService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header") })
    @ApiOperation(value = "회원 리스트 조회", notes = "모든 회원을 조회한다.")
    @GetMapping("/users")
    public ListResult<User> findAllUser() {
        return responseService.getListResult(userService.findAll());
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header") })
    @ApiOperation(value = "회원 단건 조회", notes = "회원번호로 회원 1명을 조회한다.")
    @GetMapping("/user/{msrl}")
    public SingleResult<User> findUserById(@ApiParam(value = "회원 ID", required = true) @PathVariable long msrl)
        throws Exception {
        //결과가 단건일 경우 getBasicResult 이용하여 결과를 출력한다.
        return responseService.getSingleResult(userService.findUserById(msrl));
    }
/*
    @ApiOperation(value = "회원 입력", notes = "회원을 등록한다.")
    @PostMapping(path = "/user")
    public SingleResult<User> save(@ApiParam(value = "회원아이디", required = true) @RequestParam String uid,
                     @ApiParam(value = "회원이름", required = true) @RequestParam String name) {
        return responseService.getSingleResult(userService.save(uid, name));
    }
*/
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header") })
    @ApiOperation(value = "회원 수정", notes = "회원정보를 수정한다")
    @PutMapping(value = "/user")
    public SingleResult<User> modify(@ApiParam(value = "회원번호", required = true) @RequestParam long msrl,
                                     @ApiParam(value = "회원이름", required = true) @RequestParam String name) {
        return responseService.getSingleResult(userService.modify(msrl, name));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header") })
    @ApiOperation(value = "회원 삭제", notes = "userId로 회원정보를 삭제한다")
    @DeleteMapping(value = "/user/{msrl}")
    public CommonResult delete(@ApiParam(value = "회원번호", required = true) @PathVariable long msrl) {
        userService.delete(msrl);
    // 성공 결과 정보만 필요한경우 getSuccessResult()를 이용하여 결과를 출력한다.
        return responseService.getSuccessResult();
    }


    /*
    * @ApiOperation(value = "회원 조회", notes = "모든 회원을 조회한다.")
    @GetMapping("/user")
    public List<User> findAllUser() {
        return userJpaRepository.findAll();
    }
    * */


    /*
    *@Api(tags = {“1. User”})
    * UserController를 대표하는 최상단 타이틀 영역에 표시될 값을 세팅.
    * @ApiOperation(value = “회원 조회”, notes = “모든 회원을 조회한다”)
    * 각각의 resource에 제목과 설명을 표시하기 위해 세팅.
    * @ApiParam(value = “회원 이름”, required = true) @RequestParam ~~~
    * 파라미터에 대한 설명을 보여주기 위해 세팅.
    * */
}
