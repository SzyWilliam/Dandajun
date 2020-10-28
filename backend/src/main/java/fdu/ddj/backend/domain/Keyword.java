package fdu.ddj.backend.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.security.Key;

@Entity
public class Keyword {
    public static String ACTIVITY = "ACT";
    public static String ACCOUNT = "ACC";
    public static String ARTICLE = "ART";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID;

    private String key;
    private String source;
    private Long docID;
    private Double weight;

    public Keyword() {}

    public Keyword(String key, Long docID, Double weight, String source){
        this.key = key;
        this.docID = docID;
        this.weight = weight;
        this.source = source;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Long getID() {
        return ID;
    }


    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Long getDocID() {
        return docID;
    }

    public void setDocID(Long docID) {
        this.docID = docID;
    }
}
