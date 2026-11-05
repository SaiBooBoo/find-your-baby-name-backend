package com.sszm.findyourbabynamebackend.resources;

import com.sszm.findyourbabynamebackend.dtos.BabyNameResponse;
import com.sszm.findyourbabynamebackend.models.BabyName;
import com.sszm.findyourbabynamebackend.services.BabyNameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/baby-names")
public class BabyNameResource {

    private final BabyNameService babyNameService;

    public BabyNameResource(BabyNameService babyNameService) {
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

    @GetMapping("/gender/{gender}")
    public ResponseEntity<List<BabyNameResponse>> getNamesByGender(@PathVariable String gender) {
        return ResponseEntity.ok(babyNameService.getBabyNamesByGender(gender));
    }

    @GetMapping("/search/prefix")
    public ResponseEntity<List<BabyNameResponse>> getNamesByPrefix(@RequestParam String prefix) {
        return ResponseEntity.ok(babyNameService.getBabyNamesByPrefix(prefix));
    }

    @GetMapping("/search/meaning")
    public ResponseEntity<List<BabyNameResponse>> getNamesByMeaning(@RequestParam String keyword) {
        return ResponseEntity.ok(babyNameService.getBabyNamesByMeaning(keyword));
    }

    @PostMapping
    public ResponseEntity<BabyNameResponse> createBabyName(@RequestBody BabyName babyName) {
        BabyNameResponse created = babyNameService.createBabyName(babyName);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.id())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    @PostMapping("/batch")
    public ResponseEntity<List<BabyNameResponse>> createBabyNamesInBatch(
            @RequestBody List<BabyName> babyNames) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(babyNameService.createBabyNameInBatch(babyNames));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BabyNameResponse> updateBabyName(
            @PathVariable Long id,
            @RequestBody BabyName babyName) {
        return ResponseEntity.ok(babyNameService.updateBabyName(id, babyName));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBabyName(@PathVariable Long id) {
        babyNameService.deleteBabyName(id);
        return ResponseEntity.ok("Baby name deleted successfully.");
    }
}
