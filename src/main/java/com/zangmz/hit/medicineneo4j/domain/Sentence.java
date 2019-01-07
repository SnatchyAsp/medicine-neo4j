package com.zangmz.hit.medicineneo4j.domain;

public class Sentence {
    private Integer sentenceId;

    private String pmid;

    private String type;

    private Integer number;

    private String sectionHeader;

    private String sentence;

    private String normalizedSectionHeader;

    public Integer getSentenceId() {
        return sentenceId;
    }

    public void setSentenceId(Integer sentenceId) {
        this.sentenceId = sentenceId;
    }

    public String getPmid() {
        return pmid;
    }

    public void setPmid(String pmid) {
        this.pmid = pmid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getSectionHeader() {
        return sectionHeader;
    }

    public void setSectionHeader(String sectionHeader) {
        this.sectionHeader = sectionHeader;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public String getNormalizedSectionHeader() {
        return normalizedSectionHeader;
    }

    public void setNormalizedSectionHeader(String normalizedSectionHeader) {
        this.normalizedSectionHeader = normalizedSectionHeader;
    }
}