package com.scraptreasure.service.collector;

import com.scraptreasure.dto.CollectScrapDto;
import com.scraptreasure.entity.*;
import com.scraptreasure.enums.RequestStatus;
import com.scraptreasure.exception.BadRequestException;
import com.scraptreasure.exception.ResourceNotFoundException;
import com.scraptreasure.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CollectorPickupService {

    private final ScrapRequestRepository scrapRequestRepository;
    private final ScrapCollectionDetailsRepository detailsRepository;
    private final ScrapCategoryRepository categoryRepository;

    public ScrapCollectionDetails collectScrap(
            Long requestId,
            CollectScrapDto dto
    ) {

        ScrapRequest request = scrapRequestRepository.findById(requestId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Request not found"));

        if (request.getStatus() != RequestStatus.ACCEPTED) {
            throw new BadRequestException(
                    "Request is not in ACCEPTED state");
        }

        ScrapCategory category = categoryRepository
                .findById(dto.getScrapCategoryId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Scrap category not found"));

        if (!category.isActive()) {
            throw new BadRequestException(
                    "Scrap category is inactive");
        }

        double calculatedPrice =
                dto.getWeightKg() * category.getPricePerKg();

        request.setStatus(RequestStatus.COLLECTED);

        ScrapCollectionDetails details =
                ScrapCollectionDetails.builder()
                        .scrapRequest(request)
                        .weightKg(dto.getWeightKg())
                        .price(calculatedPrice)
                        .build();

        scrapRequestRepository.save(request);
        return detailsRepository.save(details);
    }
}
