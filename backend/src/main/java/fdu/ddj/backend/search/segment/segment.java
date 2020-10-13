package segment;

import com.huaban.analysis.jieba.JiebaSegmenter;
import com.qianxinyao.analysis.jieba.keyword.*;
import java.util.*;

public class segment{
    JiebaSegmenter segmenter;
    List<String> keywords;

    // class initialization
    public segment(){
        this.segmenter = new JiebaSegmenter();
    }

    // text segment by jieba (search mode)
    public List<String> segment_search(String input_text) {
        System.out.println(input_text);
        keywords = segmenter.sentenceProcess(input_text);
        return keywords;
    }

    // text segment by jieba (index mode)
    public List<String> segment_index(String input_text) {
        System.out.println(input_text);
        keywords = segmenter.sentenceProcess(input_text);
        return keywords;
    }

    public HashMap<String, Double> keyword_idf(String input_text) {
        TFIDFAnalyzer tfidfAnalyzer = new TFIDFAnalyzer();

        // initialize idf map
        HashMap<String, Double> res = new HashMap<>();
        for(String word: this.keywords)
            res.putIfAbsent(word, 0.0);

        // calculate idf
        List<Keyword> list = tfidfAnalyzer.analyze(input_text,100);
        for(Keyword word:list) {
            res.put(word.getName(), word.getTfidfvalue());
            // print for show
            // System.out.println(word.getName()+":"+word.getTfidfvalue());
        }
        return res;
    }

    public List<Keyword> word_tfidf(String input_text) {
        TFIDFAnalyzer tfidfAnalyzer = new TFIDFAnalyzer();
        return tfidfAnalyzer.analyze(input_text,100);
    }


}