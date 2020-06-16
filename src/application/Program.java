package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entites.Department;
import model.entites.Seller;

public class Program {

	public static void main(String[] args) {
		
		
		SellerDao o = DaoFactory.createSellerDao();
		
		
		System.out.println("=== TEST 1: sellerFindById ===");
		Seller s = o.findById(3);
		System.out.println(s);
		
		System.out.println("\n== TEST 2: findByDepartment ==");
		Department d = new Department(2, "");
		
		List<Seller> l = o.findByDepartment(d);
		l.forEach(System.out::println);

	}

}
