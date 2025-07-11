package com.smo.price.infrastructure.dataproviders.database.repository;

import com.smo.price.infrastructure.dataproviders.database.entities.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IPriceRepository extends JpaRepository<PriceEntity, Long> {

    List<PriceEntity> findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfter(
            Integer brandId, Integer productId, LocalDateTime date1, LocalDateTime date2
    );

}
