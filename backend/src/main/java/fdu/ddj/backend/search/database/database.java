package fdu.ddj.backend.search.database;

import com.qianxinyao.analysis.jieba.keyword.Keyword;
import fdu.ddj.backend.repository.ActivityRepository;
import fdu.ddj.backend.repository.ArticleRepository;
import fdu.ddj.backend.repository.KeywordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


public class database{


    private KeywordRepository keywordRepository;

    public database(KeywordRepository keywordRepository){
        /*
        to do, initialize database
        */
        this.keywordRepository = keywordRepository;
    }

    public List<fdu.ddj.backend.domain.Keyword> query_keywords(List<String> keywords){
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

        List<fdu.ddj.backend.domain.Keyword> result = new ArrayList<>();
        for (String keyword: keywords){
            List<fdu.ddj.backend.domain.Keyword> records =
                keywordRepository.findByKey(keyword);
            result.addAll(records);
        }

        return result;

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
            fdu.ddj.backend.domain.Keyword keyword = new fdu.ddj.backend.domain.Keyword(
                    word.getName(),
                    (Long) id,
                    word.getTfidfvalue(),
                    type
            );
            keywordRepository.save(keyword);
        }
    }


}

