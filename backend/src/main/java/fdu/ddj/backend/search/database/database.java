package fdu.ddj.backend.search.database;

import com.qianxinyao.analysis.jieba.keyword.Keyword;

import java.util.List;

public class database{

    public database(){
        /*
        to do, initialize database
        */
    }

    public query_result query_keywords(List<String> keywords){
        /*
        input:  String[]        keywords = ["kw_1", "kw_2", ...]
                                len(keywords) = q
        output: query_result    res, contains doc_ids and doc_weights
                                len(doc_ids) = len(doc_weights) = len(keywords) = q
                                for any i s.t. 0<=i<q, len(doc_ids[i]) = len(doc_weights)
         */

        /*
        to do, select keywords' related doc_ids and corresponding doc_weights
        i.e., assign doc_ids and doc_weights, an example is shown as follows
         */
        int[][] doc_ids = { {0,1,2},
                            {1,3},
                            {0,4,2},
                            {1},
                            {0,1,4} };
        double[][] doc_weights = {  {0.6,0.4,0.3},
                                    {0.2,0.4},
                                    {0.1,0.8,0.4},
                                    {0.5},
                                    {0.4,0.5,0.4}   };

        return new query_result(doc_ids, doc_weights);
    }

    public void write_index(List<Keyword> seg_texts, String type, long id){
        /*
        input:  List<Keyword>   seg_text:   [word1, word2, ...]
                String          type:       "Activities", "Articles", "Accounts"
                long            id:         doc(Activity or Article or Account) id
         */

        for (Keyword word: seg_texts){
            System.out.println(word.getName()+":\t"+word.getTfidfvalue()+"\t"+type+"\t"+id);
            /*
            to do, write into {type} database, keyword: word.getName(),
                                               doc_id:  id
                                               doc_weight: word.getTfidfvalue()
             */
        }
    }


}

