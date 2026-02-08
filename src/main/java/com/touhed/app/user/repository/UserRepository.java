package com.touhed.app.user.repository;

import com.touhed.app.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail( String email );

    @Modifying
    @Query( """
    UPDATE User u
    SET u.refreshToken = ?2
    WHERE u.id = ?1
    """ )
    void updateRefreshTokenById( Long id, String refreshToken );

    Collection<Object> findByIdIn(List<Long> driversId);
}
