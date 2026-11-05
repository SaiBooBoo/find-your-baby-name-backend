package com.sszm.findyourbabynamebackend.dtos;

public record BabyNameResponse(
        Long id,
        String name,
        String gender,
        String meaning
) {}
