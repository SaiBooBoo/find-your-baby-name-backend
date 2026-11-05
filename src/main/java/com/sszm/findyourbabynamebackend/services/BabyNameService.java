package com.sszm.findyourbabynamebackend.services;

import com.sszm.findyourbabynamebackend.dtos.BabyNameResponse;
import com.sszm.findyourbabynamebackend.models.BabyName;
import com.sszm.findyourbabynamebackend.repositories.BabyNameRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BabyNameService {

    private final BabyNameRepository babyNameRepo;

    public BabyNameService(BabyNameRepository babyNameRepository) {
        this.babyNameRepo =  babyNameRepository;
    }

    public List<BabyNameResponse> getAllBabyNames() {
        return babyNameRepo.findAll()
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    public BabyNameResponse getBabyNameById(Long id) {
        BabyName entity = babyNameRepo.findById(id).orElseThrow(() -> new RuntimeException("Baby name not found with id: " + id));
        return convertToResponse(entity);
    }

    private BabyNameResponse convertToResponse(BabyName entity) {
        return new BabyNameResponse(
                entity.getId(),
                entity.getName(),
                entity.getGender(),
                entity.getMeaning()
        );
    }

}
