/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Branch;

/**
 *
 * @author Phuc ND
 */
public interface BranchDao {
    Branch getAllBranch(String branchCode);
    
    void updateBranch(Branch branch);
}
