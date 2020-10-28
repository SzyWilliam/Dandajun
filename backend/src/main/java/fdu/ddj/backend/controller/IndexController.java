package fdu.ddj.backend.controller;

import fdu.ddj.backend.search.search_index.search_index;
import fdu.ddj.backend.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private IndexService indexService;

    @Autowired
    public IndexController(IndexService indexService){
        this.indexService = indexService;
    }

    @RequestMapping("/index")
    public ResponseEntity<?> index(String parameter){

        return indexService.query(parameter);

    }

    @RequestMapping("/update")
    public ResponseEntity<?> update(String text, Long docID, String type){
        return indexService.update(text, docID, type);
    }
}
