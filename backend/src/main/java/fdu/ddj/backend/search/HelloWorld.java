package fdu.ddj.backend.search;



import fdu.ddj.backend.search.search_index.search_index;

import java.util.List;

public class HelloWorld {
    public static void main(String[] args) {

        // given keywords, return doc_ids by relation orders
        search_index search = new search_index();
        List<Integer> doc_ids = search.search_keywords("复旦宋子阳是帅哥!");
        System.out.println(doc_ids);
    }


}