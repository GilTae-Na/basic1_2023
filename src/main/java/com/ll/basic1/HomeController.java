package com.ll.basic1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// @Controller 의 의미
// 개발자가 스프링부트에게 말한다.
// 아래 있는 HomeController 는 컨트롤러이다.
@Controller
//점원이다 한명이 계속 일한다 = 객체가 하나만 생성되고 계속 일한다
public class HomeController {
    private int count;
    private int id;
    List<Person> list = new ArrayList<>();

    HomeController(){
        this.count = -1;
        this.id = 1;
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

    // @RequestParam 의 의미
    // 개발자가 스프링부트에게 말한다.
    // int a 는 쿼리스트링에서 a 파라미터의 값을 얻은 후 정수화 한 값이어야 한다.
    // @RequestParam 는 생략가능
    //(defaultValue = "0") 기본값설정, 값을 반드시 String으로 하자
    //a는 URL에서 생략가능 => /home/plus?b=10 => 가능
    //b는 URL에서 생략 불가능 => /home/plus?a=10 => 불가능(defaultValue 설정이 되어있지 않아서)
    @GetMapping("/home/plus")
    @ResponseBody
    public int showPlus(@RequestParam(defaultValue = "0")int a, @RequestParam int b) {
        return a+b;
    }



    @GetMapping("/home/returnBoolean")
    @ResponseBody
    public boolean showReturnBoolean() {
        return true;
    }

    @GetMapping("/home/returnDouble")
    @ResponseBody
    public double showReturnDouble() {
        return Math.PI;
    }

    @GetMapping("/home/returnIntArray")
    @ResponseBody
    public int[] showReturnIntArray() {
        int[] arr = new int[]{10, 20, 30};

        return arr;
    }

    @GetMapping("/home/returnIntList")
    @ResponseBody
    public List<Integer> showReturnIntList() {
        List<Integer> list = new ArrayList<>() {{
            add(10);
            add(20);
            add(30);
        }};

        return list;
    }

    @GetMapping("/home/returnMap")
    @ResponseBody
    public Map<String, Object> showReturnMap() {
        Map<String, Object> map = new LinkedHashMap<>() {{
            put("id", 1);
            put("speed", 100);
            put("name", "카니발");
            put("relatedIds", new ArrayList<>() {{
                add(2);
                add(3);
                add(4);
            }});
        }};

        return map;
    }

    @GetMapping("/home/returnCar")
    @ResponseBody
    public Car showReturnCar() {
        Car car = new Car(1, 100, "카니발", new ArrayList<>() {{
            add(2);
            add(3);
            add(4);
        }});

        return car;
    }

    @GetMapping("/home/returnCarV2")
    @ResponseBody
    public CarV2 showReturnCarV2() {
        CarV2 car = new CarV2(1, 100, "카니발", new ArrayList<>() {{
            add(2);
            add(3);
            add(4);
        }});

        car.setName(car.getName() + "V2");

        return car;
    }

    @GetMapping("/home/returnCarMapList")
    @ResponseBody
    public List<Map<String, Object>> showReturnCarMapList() {
        Map<String, Object> carMap1 = new LinkedHashMap<>() {{
            put("id", 1);
            put("speed", 100);
            put("name", "카니발");
            put("relatedIds", new ArrayList<>() {{
                add(2);
                add(3);
                add(4);
            }});
        }};

        Map<String, Object> carMap2 = new LinkedHashMap<>() {{
            put("id", 2);
            put("speed", 200);
            put("name", "포르쉐");
            put("relatedIds", new ArrayList<>() {{
                add(5);
                add(6);
                add(7);
            }});
        }};

        List<Map<String, Object>> list = new ArrayList<>();

        list.add(carMap1);
        list.add(carMap2);

        return list;
    }

    @GetMapping("/home/returnCarList")
    @ResponseBody
    public List<CarV2> showReturnCarList() {
        CarV2 car1 = new CarV2(1, 100, "카니발", new ArrayList<>() {{
            add(2);
            add(3);
            add(4);
        }});

        CarV2 car2 = new CarV2(2, 200, "포르쉐", new ArrayList<>() {{
            add(5);
            add(6);
            add(7);
        }});

        List<CarV2> list = new ArrayList<>();

        list.add(car1);
        list.add(car2);

        return list;
    }

    @GetMapping("/home/addPerson")
    //사람등록
    @ResponseBody
    public String addPerson(@RequestParam(defaultValue = "") String name, @RequestParam(defaultValue = "") int age) {
        list.add(new Person(id, name, age));
        return (id++) + "번 사람을 등록했습니다.";
    }

    @GetMapping("/home/removePerson")
    //사람 보여주기
    @ResponseBody
    public List<Person> removePerson(@RequestParam(defaultValue = "0") int id) {
        list.remove(id-1);
        return list;
    }

    @GetMapping("/home/people")
    //사람 보여주기
    @ResponseBody
    public List<Person> showPerson() {
        return list;
    }
}
class Car {
    private final int id;
    private final int speed;
    private final String name;
    private final List<Integer> relatedIds;

    public Car(int id, int speed, String name, List<Integer> relatedIds) {
        this.id = id;
        this.speed = speed;
        this.name = name;
        this.relatedIds = relatedIds;
    }

    public int getId() {
        return id;
    }

    public int getSpeed() {
        return speed;
    }

    public String getName() {
        return name;
    }

    public List<Integer> getRelatedIds() {
        return relatedIds;
    }
}
//롬복 사용
@AllArgsConstructor
@Getter
class CarV2 {
    private final int id;
    private final int speed;
    @Setter
    private String name;
    private final List<Integer> relatedIds;
}

@AllArgsConstructor
@Getter
@ToString
//출력형식 String으로 만들어줌
class Person{
    private int id;
    private String name;
    private int age;

    public int getId(int id) {
        return this.id;
    }
}