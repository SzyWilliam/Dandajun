package fdu.ddj.backend.search.search_index;


import fdu.ddj.backend.domain.Keyword;
import fdu.ddj.backend.repository.KeywordRepository;
import fdu.ddj.backend.search.database.database;
import fdu.ddj.backend.search.segment.segment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;


public class search_index{
    database db;
    segment seg;
    int max_doc = 50;

    Logger logger = LoggerFactory.getLogger(search_index.class);

    // class initialization
    public search_index(KeywordRepository keywordRepository){
        this.seg = new segment();       // for text segmentation
        this.db = new database(keywordRepository);       // for database
    }

    // given keywords, return doc_ids by relation orders
    public List<String> search_keywords(String input_text){

        // keywords' list after text segmentation
        List<String> keywords = this.seg.segment_search(input_text);
        // read records from database
        database db = this.db;
        List<Keyword> res = db.query_keywords(keywords);

        // get idf of each keyword by jieba
        HashMap<String, Double> idf_map = seg.keyword_idf(input_text);

        // group by doc_ids, store scores of each doc
        HashMap<String, Double> doc_scores = new HashMap<>();
        for (Keyword each: res) {
            doc_scores.putIfAbsent(each.getSource()+each.getDocID(), 0.0);
            Double tmp = doc_scores.get(each.getSource()+each.getDocID())+each.getWeight()*idf_map.get(each.getKey());
            doc_scores.put(each.getSource()+each.getDocID(), tmp);
        }

//        for(Map.Entry<Long, Double> entry: doc_scores.entrySet()){
//            System.out.println(entry.getKey() + " " +entry.getValue());
//        }


        // sort doc_ids according to their scores
        Set<Map.Entry<String,Double>> score_set = doc_scores.entrySet();
        List<Map.Entry<String, Double>> score_list = new ArrayList<>(score_set);
        score_list.sort((entry1, entry2) -> entry1.getValue()<entry2.getValue()?1:-1);

        // return doc_ids sorted by scores
        int cnt = 0;
        List<String> sorted_docs = new LinkedList<>();
        for(Map.Entry<String,Double> entry:score_list) {
            if (cnt>this.max_doc || entry.getValue()<=0.0)
                break;
            sorted_docs.add(entry.getKey());
            cnt++;
            // print to show
            System.out.print(entry.getKey());
            System.out.print("\t");
            System.out.print(entry.getValue());
            System.out.print("\n");
        }

        return sorted_docs;
    }
    
}