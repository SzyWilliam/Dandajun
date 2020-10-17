package fdu.ddj.backend.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.security.Key;

@Entity
public class Keyword {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID;

    private String key;
    private Long DocID;
    private Double weight;

    public Keyword() {}

    public Keyword(String key, Long docID, Double weight){
        this.key = key;
        this.DocID = docID;
        this.weight = weight;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Long getDocID() {
        return DocID;
    }

    public void setID(Long ID) {
        this.ID = ID;
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

    public void setDocID(Long docID) {
        DocID = docID;
    }
}
