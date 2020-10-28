package fdu.ddj.backend.service;

import fdu.ddj.backend.repository.KeywordRepository;
import fdu.ddj.backend.search.search_index.search_index;
import fdu.ddj.backend.search.update_index.update_index;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class IndexService {


    private KeywordRepository keywordRepository;

    @Autowired
    public IndexService(KeywordRepository keywordRepository){
        this.keywordRepository = keywordRepository;
    }

    public ResponseEntity<?> query(String parameter){
        search_index search = new search_index(keywordRepository);
        List<String> doc_ids = search.search_keywords(parameter);
        HashMap<String, Object> ret = new HashMap<>();

        ret.put("search key", parameter);
        ret.put("Doc Ids", doc_ids);
        return ResponseEntity.ok(ret);
    }

    public ResponseEntity<?> update(String text, Long docID, String type){
        update_index update_index = new update_index(keywordRepository);
        update_index.add_article(text, type, docID);
        return ResponseEntity.ok("Updated");
    }
}
