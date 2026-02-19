package com.scraptreasure.service.admin;

import com.scraptreasure.dto.ScrapCategoryRequestDto;
import com.scraptreasure.dto.ScrapCategoryResponseDto;
import com.scraptreasure.entity.ScrapCategory;
import com.scraptreasure.exception.BadRequestException;
import com.scraptreasure.exception.ResourceNotFoundException;
import com.scraptreasure.repository.ScrapCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminScrapCategoryService {

    private final ScrapCategoryRepository repository;

    public ScrapCategoryResponseDto createCategory(
            ScrapCategoryRequestDto dto) {

        repository.findByName(dto.getName())
                .ifPresent(c -> {
                    throw new BadRequestException(
                            "Scrap category already exists");
                });

        ScrapCategory category = ScrapCategory.builder()
                .name(dto.getName())
                .pricePerKg(dto.getPricePerKg())
                .active(true)
                .build();

        ScrapCategory saved = repository.save(category);

        return toDto(saved);
    }

    public List<ScrapCategoryResponseDto> getAllCategories() {
        return repository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public void toggleCategory(Long id, boolean active) {

        ScrapCategory category = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category not found"));

        category.setActive(active);
        repository.save(category);
    }

    private ScrapCategoryResponseDto toDto(ScrapCategory c) {
        return ScrapCategoryResponseDto.builder()
                .id(c.getId())
                .name(c.getName())
                .pricePerKg(c.getPricePerKg())
                .active(c.isActive())
                .build();
    }
}
