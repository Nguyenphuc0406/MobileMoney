/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Phuc ND
 */
public class Branch {
    private int id;
    private String branchName;
    private String branchCode;
    private int branchAmount;
            
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public int getBranchAmount() {
        return branchAmount;
    }

    public void setBranchAmount(int branchAmount) {
        this.branchAmount = branchAmount;
    }
    
    
}
