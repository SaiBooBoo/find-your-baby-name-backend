package com.sszm.findyourbabynamebackend.services;

import com.sszm.findyourbabynamebackend.dtos.BabyNameResponse;
import com.sszm.findyourbabynamebackend.models.BabyName;
import com.sszm.findyourbabynamebackend.repositories.BabyNameRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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
                .toList();
    }

    public BabyNameResponse getBabyNameById(Long id) {
        return convertToResponse(findBabyNameOrThrow(id));
    }

    public List<BabyNameResponse> getBabyNamesByGender(String gender) {
        return babyNameRepo.findByGenderIgnoreCase(gender).stream()
                .map(this::convertToResponse)
                .toList();
    }

    public List<BabyNameResponse> getBabyNamesByPrefix(String prefix) {
        return babyNameRepo.findByNameStartingWithIgnoreCase(prefix).stream()
                .map(this::convertToResponse)
                .toList();
    }

    public List<BabyNameResponse> getBabyNamesByMeaning(String keyword) {
        return babyNameRepo.findByMeaningContainingIgnoreCase(keyword).stream()
                .map(this::convertToResponse)
                .toList();
    }

    public BabyNameResponse createBabyName(BabyName babyName) {
        babyName.setId(null);
        BabyName entity = babyNameRepo.save(babyName);
        return convertToResponse(entity);
    }

    public List<BabyNameResponse> createBabyNameInBatch(List<BabyName> babies) {
        babies.forEach(baby -> baby.setId(null));
        List<BabyName> savedBabies = babyNameRepo.saveAll(babies);
        return savedBabies.stream()
                .map(this::convertToResponse)
                .toList();
    }

    public BabyNameResponse updateBabyName(Long id, BabyName babyName) {
        BabyName existing = findBabyNameOrThrow(id);
        existing.setName(babyName.getName());
        existing.setGender(babyName.getGender());
        existing.setMeaning(babyName.getMeaning());
        return convertToResponse(babyNameRepo.save(existing));
    }

    public void deleteBabyName(Long id) {
        babyNameRepo.delete(findBabyNameOrThrow(id));
    }

    private BabyName findBabyNameOrThrow(Long id) {
        return babyNameRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Baby name not found with id: " + id));
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
