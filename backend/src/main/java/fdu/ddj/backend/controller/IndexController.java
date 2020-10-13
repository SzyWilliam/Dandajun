package fdu.ddj.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

//    @GetMapping("/index")
//    public ResponseEntity<?> indexPage() {
//        return ResponseEntity.ok("hello world");
//    }
    @RequestMapping("/index")
    public String index(){
        return "Hello world";
    }
}
