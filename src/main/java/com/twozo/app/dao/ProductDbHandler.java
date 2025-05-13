package com.twozo.app.dao;

import com.twozo.app.model.Product;
import com.twozo.app.model.PurchaseItem;
import com.twozo.app.model.SaleItem;
import com.twozo.app.utility.ProductQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

@Repository
public class ProductDbHandler implements ProductDbManager {

    private final DataSource dataSource;
    private final static Logger logger = LoggerFactory.getLogger(ProductDbHandler.class);

    public ProductDbHandler(final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Collection<Product> getAll() {
        final String query = ProductQuery.SELECT_ALL_PRODUCTS;
        try(final Connection connection = dataSource.getConnection()){
            final PreparedStatement stmt = connection.prepareStatement(query);
            
            try(final ResultSet rs = stmt.executeQuery()){
                final Collection<Product> inventory = new ArrayList<>();
                while(rs.next()){
                    inventory.add(new Product(rs.getInt(1)
                    ,rs.getString(2)
                    ,rs.getDouble(3),
                    rs.getDouble(4),
                    rs.getInt(5),
                    rs.getDouble(6),
                    rs.getDouble(7),
                    rs.getInt(8)));
                }
                logger.info("Got all the product from inventory");
                return inventory;
            }
        }   
        catch(SQLException e){
            logger.error("Failed to get all product",e);
        }
        return null;
    }

    @Override
    public Product get(final int id){
        final String query = ProductQuery.SELECT_PRODUCT_BY_ID;
        try(final Connection connection = dataSource.getConnection()){
            final PreparedStatement stmt = connection.prepareStatement(query);
            
            stmt.setInt(1,id);

            try(final ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    return (new Product(rs.getInt(1)
                    ,rs.getString(2)
                    ,rs.getDouble(3),
                    rs.getDouble(4),
                    rs.getInt(5),
                    rs.getDouble(6),
                    rs.getDouble(7),
                    rs.getInt(8)));
                }
                logger.info("Got the product by id");
            }
        }   
        catch(SQLException e){
            logger.error("Failed to get the product ",e);
        }
        return null;
    }

    @Override
    public void store(final Product product) {
        final String query = ProductQuery.INSERT_PRODUCT;
        try (final Connection connection = dataSource.getConnection()){
            final PreparedStatement stmt = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

            stmt.setString(1, product.getName());
            stmt.setDouble(2, product.getPurchasePrice());
            stmt.setDouble(3, product.getMrp());
            stmt.setDouble(4, product.getTaxPercentage());
            stmt.setDouble(5, product.getDiscountPercentage());
            stmt.setDouble(6, product.getSellingPrice());
            stmt.setInt(7, product.getStockQuantity());

            final int rows = stmt.executeUpdate();
            if (rows > 0) {
                logger.info("Product inserted successfully");
            }

        } catch (SQLException e) {
            logger.error("Failed to store the product",e);
        }
    }

    @Override
    public boolean remove(final int id){
        final String query = ProductQuery.DELETE_PRODUCT;

        try(final Connection connection = dataSource.getConnection()) {
            final PreparedStatement stmt = connection.prepareStatement(query);

            stmt.setInt(1, id);

            final int row = stmt.executeUpdate();
            if (row > 0){
                logger.info("Product removed successfully");
                return true;
            }
        } catch (SQLException e) {
            logger.error("Failed to remove the product",e);
        }
        return false;
    }

    @Override
    public boolean updateStock(final Collection<PurchaseItem> cart){
        final String query = ProductQuery.UPDATE_STOCK;
        try(final Connection connection = dataSource.getConnection()) {
            try (final PreparedStatement stmt = connection.prepareStatement(query)) {
                for (PurchaseItem item : cart){
                    stmt.setInt(1, item.getQuantity());
                    stmt.setInt(2, item.getProductId());
                    stmt.addBatch();
                }
                stmt.executeBatch();
                logger.info("Stock updation via batch processing is completed");
            }
        } catch (SQLException e) {
            logger.error("Failed to update the stock",e);
            return false;
        }
        return true;
    }

    @Override
    public boolean removeStock(final Collection<SaleItem> cart) {
        final String query = ProductQuery.REMOVE_STOCK;
        try (final Connection connection = dataSource.getConnection()) {
            try (final PreparedStatement stmt = connection.prepareStatement(query)) {
                for (SaleItem item : cart) {
                    stmt.setInt(1, item.getQuantity());
                    stmt.setInt(2, item.getProductId());
                    stmt.addBatch();
                }
                stmt.executeBatch();
                logger.info("Stock removal via batch processing is completed");
            }
        } catch (SQLException e) {
            logger.error("Failed to remove the stock",e);
            return false;
        }
        return true;
    }
}
