package com.ll.basic1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

// @Controller 의 의미
// 개발자가 스프링부트에게 말한다.
// 아래 있는 HomeController 는 컨트롤러이다.
@Controller
//점원이다 한명이 계속 일한다 = 객체가 하나만 생성되고 계속 일한다
public class HomeController {
    private int count;

    HomeController(){
        this.count = -1;
    }
    // @GetMapping("/home/main") 의 의미
    // 개발자가 스프링부트에게 말한다.
    // 만약에 /home/main 이런 요청이 오면 아래 메서드를 실행해줘
    @GetMapping("/home/main")
    // @ResponseBody 의 의미
    // 아래 메서드를 실행한 후 그 리턴값을 응답으로 삼아줘
    @ResponseBody
    public String showMain() {
        return "안녕하세요.";
    }

    @GetMapping("/home/main2")
    @ResponseBody
    public String showMain2() {
        return "반갑습니다.";
    }

    @GetMapping("/home/main3")
    @ResponseBody
    public String showMain3() {
        return "즐거웠습니다.";
    }

    @GetMapping("/home/increase")
    //클라이언트와 서버는 서로 String으로 통신한다. 다른것들 안댐
    @ResponseBody
    public int showIncrease() { //리턴되는 int는 String화 되어서 고객한테 돌아감
        count++;
        return count;
    }
    @GetMapping("/home/plus")
    // @RequestParam 의 의미
    // 개발자가 스프링부트에게 말한다.
    // int a 는 쿼리스트링에서 a 파라미터의 값을 얻은 후 정수화 한 값이어야 한다.
    // @RequestParam 는 생략가능
    //(defaultValue = "0") 기본값설정, 값을 반드시 String으로 하자
    @ResponseBody
    public int showPlus(@RequestParam(defaultValue = "0")int a, @RequestParam int b) {
        return a+b;
    }

}