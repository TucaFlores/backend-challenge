package com.myhotel.template.repositories;

import com.myhotel.template.entities.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {

    @Query(value = """
            SELECT
                SUBSTRING(email FROM POSITION('@' IN email) + 1) AS domain,
                COUNT(*) AS count
            FROM
                guests g
            GROUP BY
                domain
            ORDER BY
                count DESC;
        """, nativeQuery = true)
    List<Object[]> countGuestsByEmailDomain();
}
