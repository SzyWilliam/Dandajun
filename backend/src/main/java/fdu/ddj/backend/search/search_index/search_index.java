package search_index;

import database.database;
import segment.segment;
import database.query_result;
import java.util.*;

public class search_index{
    database db;
    segment seg;
    int max_doc = 50;

    // class initialization
    public search_index(){
        this.seg = new segment();       // for text segmentation
        this.db = new database();       // for database
    }

    // given keywords, return doc_ids by relation orders
    public List<Integer> search_keywords(String input_text){

        // keywords' list after text segmentation
        List<String> keywords = this.seg.segment_search(input_text);

        // print to show
        System.out.println(keywords);

        // read records from database
        database db = new database();
        query_result res = db.query_keywords(keywords);
        int[][] doc_ids = res.doc_ids;
        double[][] doc_weights = res.doc_weights;

        // get idf of each keyword by jieba
        HashMap<String, Double> idf_map = seg.keyword_idf(input_text);
        Double[] idf = new Double[keywords.size()];
        for (int i=0; i<keywords.size(); i++)
            if (idf_map.get(keywords.get(i))!=null) {
                idf[i] = idf_map.get(keywords.get(i));
                // print to show
                System.out.println(keywords.get(i)+": "+idf[i]);
            }

        // group by doc_ids, store scores of each doc
        HashMap<Integer, Double> doc_scores = new HashMap<>();
        for (int i=0; i<doc_ids.length; i++) {
            for (int j=0; j<doc_ids[i].length; j++) {
                doc_scores.putIfAbsent(doc_ids[i][j], 0.0);
                Double tmp = doc_scores.get(doc_ids[i][j])+doc_weights[i][j]*idf[i];
                doc_scores.put(doc_ids[i][j], tmp);
            }
        }

        // sort doc_ids according to their scores
        Set<Map.Entry<Integer,Double>> score_set = doc_scores.entrySet();
        List<Map.Entry<Integer, Double>> score_list = new ArrayList<>(score_set);
        score_list.sort((entry1, entry2) -> entry1.getValue()<entry2.getValue()?1:-1);

        // return doc_ids sorted by scores
        int cnt = 0;
        List<Integer> sorted_docs = new LinkedList<>();
        for(Map.Entry<Integer,Double> entry:score_list) {
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