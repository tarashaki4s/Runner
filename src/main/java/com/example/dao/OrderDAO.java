package com.example.dao;

import com.example.entity.Order;
import com.example.entity.statistic.IRevenueByMonth;
import com.example.entity.statistic.ISalesByMonth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderDAO extends JpaRepository<Order,Long> {
    @Query("SELECT o FROM Order o WHERE o.account.id=?1")
    List<Order> findByAccountId(Long id);

    @Query("SELECT o FROM Order o WHERE o.status=false")
    List<Order> findByStatus();

    @Query(value = "SELECT MONTH(CreateDate) AS month, COUNT(Quantity) AS sales\n" +
            "FROM OrderDetails JOIN Orders ON OrderDetails.OrderId = Orders.Id\n" +
            "WHERE YEAR(CreateDate) = YEAR(GETDATE()) \n" +
            "GROUP BY MONTH(CreateDate)", nativeQuery = true)
    List<ISalesByMonth> getSalesByMonth();

    @Query(value = "SELECT MONTH(CreateDate) AS Month, SUM(Quantity * Products.Price) as revenue\n" +
            "FROM OrderDetails JOIN Orders ON OrderDetails.OrderId = Orders.Id\n" +
            "JOIN Products On OrderDetails.ProductId = Products.ID\n" +
            "WHERE YEAR(CreateDate) = YEAR(GETDATE()) \n" +
            "GROUP BY MONTH(CreateDate)", nativeQuery = true)
    List<IRevenueByMonth> getRevenueByMonth();
}
