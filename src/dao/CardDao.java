/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Card;

/**
 *
 * @author Phuc ND
 */
public interface CardDao {

    Card getAll(String serialNumber);

    void deleteCard(String serialNumber);
}
