package fdu.ddj.backend.search.database;

// return doc_ids and doc_weights
public class query_result {
    public int[][] doc_ids;
    public double[][] doc_weights;
    public query_result(int[][] doc_ids, double[][] doc_weights) {
        super();
        this.doc_ids = doc_ids;
        this.doc_weights = doc_weights;
    }
}
