package com.din.pridesys.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.din.pridesys.model.PrideUser;

@Repository
public interface PrideUserRepo extends CrudRepository<PrideUser, Long>{
	PrideUser findByUsername(String username);
}
