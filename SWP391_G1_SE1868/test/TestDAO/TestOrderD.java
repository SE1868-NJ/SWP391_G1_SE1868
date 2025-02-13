/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TestDAO;

import entity.OrderDetail;
import dbcontext.OrderDetailDAO;
import java.util.List;

/**
 *
 * @author DUCDUY2003
 */
public class TestOrderD {

    public static void main(String[] args) {
        OrderDetailDAO dao = new OrderDetailDAO();
        List<OrderDetail> List = dao.getAllOrderD();
        for (OrderDetail o : List) {
            System.out.println(o);

        }
    }
}
