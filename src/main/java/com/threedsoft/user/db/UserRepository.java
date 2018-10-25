package com.threedsoft.user.db;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	@Query("select i from User i where i.userName=:userName")
	public User findByUserName(@Param("userName") String userName);

	@Query("select i from User i where i.busName=:busName and i.defLocnNbr=:locnNbr order by i.id desc")
	public List<User> findByBusNameAndLocnNbr(@Param("busName") String busName, @Param("locnNbr") Integer locnNbr, Pageable pageRequest);
}
