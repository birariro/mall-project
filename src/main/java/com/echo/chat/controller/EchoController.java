package com.echo.chat.controller;

import com.echo.chat.common.response.result.LinksResult;
import com.echo.chat.controller.dto.request.EchoRequest;
import com.echo.chat.controller.dto.response.EchoDto;
import com.echo.chat.domain.Member;
import com.echo.chat.service.EchoService;
import com.echo.chat.service.MemberService;
import com.echo.chat.common.response.ResponseService;
import com.echo.chat.common.response.result.SingleResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/echo")
public class EchoController {
    private final EchoService echoService;
    private final ResponseService responseService;
    private final MemberService memberService;

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping
    public ResponseEntity fetchAllEcho(Pageable pageable){
        Page<EchoDto> articles = echoService.fetchAll(pageable).map(EchoDto::new);

        List<LinksResult> links = new ArrayList<>();
        links.add(new LinksResult("detail","/echo/{id}"));

        SingleResult<Page<EchoDto>> responseBody = responseService.getResult(articles, links);

        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity fetchEcho(@PathVariable Long id){

        EchoDto article = new EchoDto(echoService.fetch(id));
        SingleResult<EchoDto> responseBody = responseService.getResult(article);

        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('USER','ADMIN')")//해당 api 접근 권한
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "jwt Token" , required = true, dataType = "string", paramType = "header")
    }) //swagger 에 jwt 추가 하도록 강제
    public ResponseEntity saveEcho(@Valid @RequestBody EchoRequest echoRequest){

        Member authMember = memberService.getAuthMember();
        echoService.post(authMember, echoRequest.getContext());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(responseService.getSuccessResult());
    }
}
