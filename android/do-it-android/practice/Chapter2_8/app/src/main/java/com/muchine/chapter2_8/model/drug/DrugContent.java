package com.muchine.chapter2_8.model.drug;

/**
 * Created by sejoonlim on 9/14/16.
 */
public class DrugContent {

    private String drugCode;

    private String classCode;

    private String className;

    private String detail;

    public DrugContent(String drugCode, String classCode, String className, String detail) {
        this.drugCode = drugCode;
        this.classCode = classCode;
        this.className = className;
        this.detail = detail;
    }

    public String getDrugCode() {
        return drugCode;
    }

    public String getClassCode() {
        return classCode;
    }

    public String getClassName() {
        return className;
    }

    public String getDetail() {
        return detail;
    }
}
