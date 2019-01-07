package com.zangmz.hit.medicineneo4j.domain;

import java.util.Date;

public class PredicationAux {
    private Integer predicationAuxId;

    private Integer predicationId;

    private String subjectText;

    private Integer subjectDist;

    private Integer subjectMaxdist;

    private Integer subjectStartIndex;

    private Integer subjectEndIndex;

    private Integer subjectScore;

    private String indicatorType;

    private Integer predicateStartIndex;

    private Integer predicateEndIndex;

    private String objectText;

    private Integer objectDist;

    private Integer objectMaxdist;

    private Integer objectStartIndex;

    private Integer objectEndIndex;

    private Integer objectScore;

    private Date currTimestamp;

    public Integer getPredicationAuxId() {
        return predicationAuxId;
    }

    public void setPredicationAuxId(Integer predicationAuxId) {
        this.predicationAuxId = predicationAuxId;
    }

    public Integer getPredicationId() {
        return predicationId;
    }

    public void setPredicationId(Integer predicationId) {
        this.predicationId = predicationId;
    }

    public String getSubjectText() {
        return subjectText;
    }

    public void setSubjectText(String subjectText) {
        this.subjectText = subjectText;
    }

    public Integer getSubjectDist() {
        return subjectDist;
    }

    public void setSubjectDist(Integer subjectDist) {
        this.subjectDist = subjectDist;
    }

    public Integer getSubjectMaxdist() {
        return subjectMaxdist;
    }

    public void setSubjectMaxdist(Integer subjectMaxdist) {
        this.subjectMaxdist = subjectMaxdist;
    }

    public Integer getSubjectStartIndex() {
        return subjectStartIndex;
    }

    public void setSubjectStartIndex(Integer subjectStartIndex) {
        this.subjectStartIndex = subjectStartIndex;
    }

    public Integer getSubjectEndIndex() {
        return subjectEndIndex;
    }

    public void setSubjectEndIndex(Integer subjectEndIndex) {
        this.subjectEndIndex = subjectEndIndex;
    }

    public Integer getSubjectScore() {
        return subjectScore;
    }

    public void setSubjectScore(Integer subjectScore) {
        this.subjectScore = subjectScore;
    }

    public String getIndicatorType() {
        return indicatorType;
    }

    public void setIndicatorType(String indicatorType) {
        this.indicatorType = indicatorType;
    }

    public Integer getPredicateStartIndex() {
        return predicateStartIndex;
    }

    public void setPredicateStartIndex(Integer predicateStartIndex) {
        this.predicateStartIndex = predicateStartIndex;
    }

    public Integer getPredicateEndIndex() {
        return predicateEndIndex;
    }

    public void setPredicateEndIndex(Integer predicateEndIndex) {
        this.predicateEndIndex = predicateEndIndex;
    }

    public String getObjectText() {
        return objectText;
    }

    public void setObjectText(String objectText) {
        this.objectText = objectText;
    }

    public Integer getObjectDist() {
        return objectDist;
    }

    public void setObjectDist(Integer objectDist) {
        this.objectDist = objectDist;
    }

    public Integer getObjectMaxdist() {
        return objectMaxdist;
    }

    public void setObjectMaxdist(Integer objectMaxdist) {
        this.objectMaxdist = objectMaxdist;
    }

    public Integer getObjectStartIndex() {
        return objectStartIndex;
    }

    public void setObjectStartIndex(Integer objectStartIndex) {
        this.objectStartIndex = objectStartIndex;
    }

    public Integer getObjectEndIndex() {
        return objectEndIndex;
    }

    public void setObjectEndIndex(Integer objectEndIndex) {
        this.objectEndIndex = objectEndIndex;
    }

    public Integer getObjectScore() {
        return objectScore;
    }

    public void setObjectScore(Integer objectScore) {
        this.objectScore = objectScore;
    }

    public Date getCurrTimestamp() {
        return currTimestamp;
    }

    public void setCurrTimestamp(Date currTimestamp) {
        this.currTimestamp = currTimestamp;
    }
}