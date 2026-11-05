package com.sszm.findyourbabynamebackend.repositories;

import com.sszm.findyourbabynamebackend.models.BabyName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BabyNameRepository extends JpaRepository<BabyName, Long> {

    List<BabyName> findByGenderIgnoreCase(String gender);

    List<BabyName> findByNameStartingWithIgnoreCase(String prefix);

    List<BabyName> findByMeaningContainingIgnoreCase(String keyword);
}
