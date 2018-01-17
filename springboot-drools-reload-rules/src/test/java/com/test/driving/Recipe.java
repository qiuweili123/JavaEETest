package com.test.driving;

import java.math.BigDecimal;

public class Recipe implements java.io.Serializable {

    static final long serialVersionUID = 1L;

    private Long applicantId;

    private Long medicineId;


    private BigDecimal useNum;

    public Recipe() {
    }

    public java.lang.Long getMedicineId() {
        return this.medicineId;
    }

    public void setMedicineId(java.lang.Long medicineId) {
        this.medicineId = medicineId;
    }

    public java.math.BigDecimal getUseNum() {
        return this.useNum;
    }

    public void setUseNum(java.math.BigDecimal useNum) {
        this.useNum = useNum;
    }

    public Long getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(Long applicantId) {
        this.applicantId = applicantId;
    }

    public Recipe(java.lang.Long medicineId, java.math.BigDecimal useNum) {
        this.medicineId = medicineId;
        this.useNum = useNum;
    }

}