package fdu.ddj.backend.controller;

import fdu.ddj.backend.search.search_index.search_index;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class IndexController {

//    @GetMapping("/index")
//    public ResponseEntity<?> indexPage() {
//        return ResponseEntity.ok("hello world");
//    }
    @RequestMapping("/index")
    public ResponseEntity<?> index(String parameter){
        search_index search = new search_index();
        List<Integer> doc_ids = search.search_keywords(parameter);
        HashMap<String, Object> ret = new HashMap<>();

        ret.put("search key", parameter);
        ret.put("Doc Ids", doc_ids);
        return ResponseEntity.ok(ret);

    }
}
