package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entites.Department;
import model.entites.Seller;

public class SellerDaoJDBC implements SellerDao{

	private Connection con = null;
	
	public SellerDaoJDBC(Connection con) {
		this.con = con;
	}
	
	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = con.prepareStatement(
					"SELECT seller.*,department.Name as DepName " 
					+ "FROM seller INNER JOIN department " 
					+ "ON seller.DepartmentId = department.Id " 
					+ "WHERE seller.Id = ?");
			
			st.setInt(1, id);
			
			rs = st.executeQuery();
			
			if(rs.next()) {
				// CRIA UMA FUNCAO PARA ADTRIBUIR O DEPARTAMENTO
				Department dep = instatiateDepartment(rs);
				
				// CRIA UMA FUNCAO PARA ADTRIBUIR O VENDEDOR
				Seller obj = instatiateSeller(rs, dep);
				
				return obj;
			}
			
			return null;
			
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
		
	}

	private Seller instatiateSeller(ResultSet rs, Department dep) throws SQLException {
		Seller obj = new Seller();
		obj.setId(rs.getInt("Id"));
		obj.setName(rs.getString("Name"));
		obj.setEmail(rs.getString("Email"));
		obj.setBirthDate(rs.getDate("BirthDate"));
		obj.setBaseSalary(rs.getDouble("BaseSalary"));
		obj.setDepartment(dep);
		return obj;
	}

	private Department instatiateDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setName(rs.getString("DepName"));
		return dep;
	}

	@Override
	public List<Seller> findAll() {
		List<Seller> listaSeller = new ArrayList<>();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			
			rs = st.executeQuery(
					"SELECT seller.*,department.Name as DepName " 
					+ "FROM coursejdbc.seller INNER JOIN coursejdbc.department "
					+ "ON seller.DepartmentId = department.Id");
			if(rs.next()) {
				while(rs.next()) {
					Department dep = instatiateDepartment(rs);
					
					Seller obj = instatiateSeller(rs, dep);
					
					listaSeller.add(obj);
				}
				return listaSeller;
				
			}
			return null;
			
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		
		
	}

}
