package com.commerce.services.user.dao;


import com.commerce.services.user.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
