package com.getir.readingisgood.repository;

import com.getir.readingisgood.entity.Order;
import com.getir.readingisgood.model.StatisticsDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByOrderDateTimeBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);
    Page<Order> findAllByCustomerUsername(@NotBlank String username, Pageable pageable);

    @Query("select new com.getir.readingisgood.model.StatisticsDto(to_char(o.orderDateTime, 'Month') as month, count(o.customer.username) as orderCount) " +
            "from Order o " +
            "where o.customer.username = ?1 " +
            "group by month")
    List<StatisticsDto> getOrdersCountByMonth(String username);


    @Query("select new com.getir.readingisgood.model.StatisticsDto(to_char(o.orderDateTime, 'Month') as month," +
            "sum(od.price * od.quantity) as purchasedOrdersAmount, sum(od.quantity) as purchasedBooksCount)" +
            "from Order o " +
            "inner join OrderDetail od on o.id = od.order.id " +
            "where o.customer.username = ?1" +
            "  and o.status = 'COMPLETED' " +
            "group by month")
    List<StatisticsDto> getPurchasedBooksCountByMonth(String username);

}
