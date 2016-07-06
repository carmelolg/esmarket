package it.carmelolagamba.esmarket.persistence.storageManagement.dao;

import java.util.List;

import it.carmelolagamba.esmarket.domain.storageManagement.Product;
import it.carmelolagamba.esmarket.persistence.GenericDAO;

public interface ProductDAO extends GenericDAO<Product> {
	public static enum OrderBy {
		name, category, brand, unitPrice, quantity, productDate
	}

	public List<String> getAllBrand();

	public List<Product> findProductsByCategory(String category);

	public List<Product> findProductsByPrice(int minPrice, int maxPrice);

	public List<Product> findProductsByBrand(String brand);

	public List<Product> findProductsByDiscount(float discount);

	public List<Product> findAllProductsForSale();

	public List<Product> findPFSByExample(Product example, Float minPrice, Float maxPrice, OrderBy orderByField, boolean asc);

	public List<Product> getByName(String name);
}
