package com.twozo.app.dao;

import com.twozo.app.model.Product;
import com.twozo.app.model.Report;
import com.twozo.app.utility.ReportQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

@Repository
public class ReportDbHandler implements ReportDbManager {
    private final DataSource dataSource;
    private final static Logger logger = LoggerFactory.getLogger(ReportDbHandler.class);

    public ReportDbHandler(final DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public Report get() {
        final Report report = new Report(null, null, 0, 0, null, 0, 0);
        Connection connection = null;
        try {
            // Begin transaction
            connection =dataSource.getConnection();
            connection.setAutoCommit(false);

            // 1. Max Sold Product
            try (final PreparedStatement stmt = connection.prepareStatement(ReportQuery.MAX_SALED_PRODUCT);
                 final ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    report.setMaxSold(rs.getString(1));
                }
            }

            // 2. Min Sold Product
            try (final PreparedStatement stmt = connection.prepareStatement(ReportQuery.MIN_SALED_PRODUCT);
                 final ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    report.setMinSold( rs.getString(1));
                }
            }

            // 3. Total Sales in Last 7 Days
            try (final PreparedStatement stmt = connection.prepareStatement(ReportQuery.TOTAL_SALES_IN_DATE);
                 final ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    report.setWeekSale(rs.getDouble(1));
                }
            }

            // 4. Total Purchase in Last 7 Days
            try (final PreparedStatement stmt = connection.prepareStatement(ReportQuery.TOTAL_PURCHASE_IN_DATE);
                 final ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    report.setWeekPurchase(rs.getDouble(1));
                }
            }

            // 5. Stock Check
            try (final PreparedStatement stmt = connection.prepareStatement(ReportQuery.STOCK_CHECK);
                 final ResultSet rs = stmt.executeQuery()) {

                final HashMap<Integer, Product> lowStockMap = new HashMap<>();

                while (rs.next()) {
                    final int id = rs.getInt(1);

                    Product product = new Product(rs.getInt(1)
                    ,rs.getString(2)
                    ,rs.getDouble(3),
                    rs.getDouble(4),
                    rs.getInt(5),
                    rs.getDouble(6),
                    rs.getDouble(7),
                    rs.getInt(8));

                    lowStockMap.put(id, product);
                }

                report.setLowStockItems(lowStockMap);
            }

            // 6. Total Sales
            try (final PreparedStatement stmt = connection.prepareStatement(ReportQuery.TOTAL_SALES);
                 final ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    report.setTotalSale(rs.getDouble(1));
                }
            }

            // 7. Total Purchase
            try (final PreparedStatement stmt = connection.prepareStatement(ReportQuery.TOTAL_PURCHASE);
                 final ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    report.setTotalPurchase(rs.getDouble(1));
                }
            }

            // Commit if all succeed
            connection.commit();
            logger.info("Report Generated Successfully");
            return report;

        } catch (SQLException e) {
            // Rollback if any query fails
            try {
                connection.rollback();
                logger.error("Transaction rolled back due to error: " + e.getMessage());
            } catch (SQLException rollbackEx) {
                logger.error("Rollback failed: " + rollbackEx.getMessage());
            }
        } finally {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (Exception e) {
                logger.error("Failed to reset auto-commit: " + e.getMessage());
            }
        }
        return null;
    }
}
