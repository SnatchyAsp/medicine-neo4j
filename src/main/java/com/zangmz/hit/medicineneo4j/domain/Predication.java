package com.zangmz.hit.medicineneo4j.domain;

public class Predication {
    private Integer predicationId;

    private Integer sentenceId;

    private String pmid;

    private String predicate;

    private String subjectCui;

    private String subjectName;

    private String subjectSemtype;

    private Boolean subjectNovelty;

    private String objectCui;

    private String objectName;

    private String objectSemtype;

    private Boolean objectNovelty;

    public Integer getPredicationId() {
        return predicationId;
    }

    public void setPredicationId(Integer predicationId) {
        this.predicationId = predicationId;
    }

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

    public String getPredicate() {
        return predicate;
    }

    public void setPredicate(String predicate) {
        this.predicate = predicate;
    }

    public String getSubjectCui() {
        return subjectCui;
    }

    public void setSubjectCui(String subjectCui) {
        this.subjectCui = subjectCui;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectSemtype() {
        return subjectSemtype;
    }

    public void setSubjectSemtype(String subjectSemtype) {
        this.subjectSemtype = subjectSemtype;
    }

    public Boolean getSubjectNovelty() {
        return subjectNovelty;
    }

    public void setSubjectNovelty(Boolean subjectNovelty) {
        this.subjectNovelty = subjectNovelty;
    }

    public String getObjectCui() {
        return objectCui;
    }

    public void setObjectCui(String objectCui) {
        this.objectCui = objectCui;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getObjectSemtype() {
        return objectSemtype;
    }

    public void setObjectSemtype(String objectSemtype) {
        this.objectSemtype = objectSemtype;
    }

    public Boolean getObjectNovelty() {
        return objectNovelty;
    }

    public void setObjectNovelty(Boolean objectNovelty) {
        this.objectNovelty = objectNovelty;
    }

    @Override
    public String toString() {
        return "Predication{" +
                "predicationId=" + predicationId +
                ", sentenceId=" + sentenceId +
                ", pmid='" + pmid + '\'' +
                ", predicate='" + predicate + '\'' +
                ", subjectCui='" + subjectCui + '\'' +
                ", subjectName='" + subjectName + '\'' +
                ", subjectSemtype='" + subjectSemtype + '\'' +
                ", subjectNovelty=" + subjectNovelty +
                ", objectCui='" + objectCui + '\'' +
                ", objectName='" + objectName + '\'' +
                ", objectSemtype='" + objectSemtype + '\'' +
                ", objectNovelty=" + objectNovelty +
                '}';
    }
}