package it.carmelolagamba.esmarket.service;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import it.carmelolagamba.esmarket.domain.storageManagement.Product;
import it.carmelolagamba.esmarket.persistence.DAOFactory;
import it.carmelolagamba.esmarket.persistence.storageManagement.dao.ProductDAO;
import it.carmelolagamba.esmarket.persistence.storageManagement.dao.ProductDAO.OrderBy;

@Service
public class ProductsService {
	private ProductDAO productDAO;

	public ProductsService() {
		DAOFactory daoFactory = DAOFactory.instance(DAOFactory.HIBERNATE);
		productDAO = daoFactory.getProductDAO();
	}

	public List<List<Product>> getFeaturedProducts() {
		List<Product> productsForSale = productDAO.findAllProductsForSale();
		List<List<Product>> featuredProducts = new LinkedList<List<Product>>();

		for (int i = 0; i < 4; i++) {
			if (i * 4 + 4 >= productsForSale.size())
				break;
			featuredProducts.add(productsForSale.subList(i * 4, i * 4 + 4));
		}

		return featuredProducts;
	}

	public List<Product> getLatestProducts() {
		List<Product> productsForSale = productDAO.findAllProductsForSale();
		if (productsForSale.size() > 9)
			return productsForSale.subList(0, 9);
		else
			return productsForSale;
	}

	public List<String> getAllBrands() {
		List<String> products = productDAO.getAllBrand();
		products.add("all");
		return products;
	}

	public HashMap<String, Integer> getProductsCategories() {
		List<Product> productsForSale = productDAO.findAllProductsForSale();
		HashMap<String, Integer> categories = new HashMap<String, Integer>();
		categories.put("all", productsForSale.size());
		for (Product product : productsForSale) {
			String key = product.getCategory();
			if (!categories.containsKey(key))
				categories.put(key, 1);
			else
				categories.put(key, categories.get(key) + 1);
		}
		return categories;
	}

	public Product getProductByID(Long ID) {
		return productDAO.getById(ID);
	}

	public List<Product> getProductsBySearch(Product example, float minPrice, float maxPrice, int orderOption) {
		List<Product> products = null;

		switch (orderOption) {
		case 0:
			products = productDAO.findPFSByExample(example, minPrice, maxPrice, OrderBy.name, true);
			break;
		case 1:
			products = productDAO.findPFSByExample(example, minPrice, maxPrice, OrderBy.name, false);
			break;
		case 2:
			products = productDAO.findPFSByExample(example, minPrice, maxPrice, OrderBy.unitPrice, true);
			break;
		case 3:
			products = productDAO.findPFSByExample(example, minPrice, maxPrice, OrderBy.unitPrice, false);
			break;
		default:
			break;
		}
		return products;
	}

	public Product saveProduct(Product product) {
		product.setProductDate(new Date(System.currentTimeMillis()));
		if (productDAO.getByName(product.getName()).size() > 0) {
			return productDAO.getByName(product.getName()).get(0);
		} else {
			productDAO.save(product);
		}

		return productDAO.getById(product.getID());
	}

	public void updateProduct(Product product) {
		productDAO.update(product);
	}

	public List<Product> getProductsByCategory(String category) {
		return productDAO.findProductsByCategory(category);
	}
}
