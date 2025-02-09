/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TestDAO;

import entity.Orders;
import dbcontext.OrderDAO;
import java.util.List;

/**
 *
 * @author DUCDUY2003
 */
public class TestOrder {

    public static void main(String[] args) {
        OrderDAO dao = new OrderDAO();
        List<Orders> List = dao.getAll();
        for (Orders o : List) {
            System.out.println(o);

        }
    }
}
