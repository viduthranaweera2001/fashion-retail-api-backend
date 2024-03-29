package com.itpm.fashionretailapi.repository;

import com.itpm.fashionretailapi.model.PaymentCard;
import com.itpm.fashionretailapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentCardRepository extends JpaRepository<PaymentCard,Long> {
    PaymentCard findByUser(User user);
}
