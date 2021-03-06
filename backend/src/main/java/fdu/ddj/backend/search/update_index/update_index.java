package fdu.ddj.backend.search.update_index;


import com.qianxinyao.analysis.jieba.keyword.Keyword;
import fdu.ddj.backend.repository.KeywordRepository;
import fdu.ddj.backend.search.database.database;
import fdu.ddj.backend.search.segment.segment;

import java.util.HashMap;
import java.util.List;

public class update_index{

    database db;
    segment seg;

    // class initialization
    public update_index(KeywordRepository keywordRepository){
        this.seg = new segment();       // for text segmentation
        this.db = new database(keywordRepository);       // for database
    }

    public void add_article(String text, String type, long id) {

        // get tfidf of each word by jieba
        List<Keyword> seg_texts = seg.word_tfidf(text);

        // seg_text:    [word1, word2, ...]
        // word1.getName()
        // word.getTfidfvalue()
        db.write_index(seg_texts, type, id);
    }

}