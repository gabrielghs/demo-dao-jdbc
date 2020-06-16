package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entites.Seller;

public class Program {

	public static void main(String[] args) {
		
		
		SellerDao o = DaoFactory.createSellerDao();
		
		System.out.println("=== TEST 1: sellerFindById ===");
		Seller s = o.findById(3);
		System.out.println(s);
		
		

	}

}
