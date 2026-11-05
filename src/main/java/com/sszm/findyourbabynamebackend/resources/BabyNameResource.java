package com.sszm.findyourbabynamebackend.resources;

import com.sszm.findyourbabynamebackend.dtos.BabyNameResponse;
import com.sszm.findyourbabynamebackend.services.BabyNameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/baby-names")
public class BabyNameResource {

    private final BabyNameService babyNameService;

    public BabyNameResource (BabyNameService babyNameService) {
        this.babyNameService = babyNameService;
    }

    @GetMapping
    public ResponseEntity<List<BabyNameResponse>> getAllNames() {
        return ResponseEntity.ok(babyNameService.getAllBabyNames());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BabyNameResponse> getNameById(@PathVariable Long id) {
        return ResponseEntity.ok(babyNameService.getBabyNameById(id));
    }


}
