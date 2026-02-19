package com.scraptreasure.service.collector;

import com.scraptreasure.dto.CollectorRequestDto;
import com.scraptreasure.entity.ScrapRequest;
import com.scraptreasure.entity.User;
import com.scraptreasure.enums.RequestStatus;
import com.scraptreasure.exception.BadRequestException;
import com.scraptreasure.exception.ResourceNotFoundException;
import com.scraptreasure.repository.ScrapRequestRepository;
import com.scraptreasure.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CollectorRequestService {

    private final ScrapRequestRepository scrapRequestRepository;
    private final UserRepository userRepository;

    public ScrapRequest acceptRequest(Long requestId, String collectorEmail) {

        User collector = userRepository.findByEmail(collectorEmail)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Collector not found"));

       
        if (!collector.isEnabled()) {
            throw new BadRequestException("Collector is not verified by admin");
        }

        ScrapRequest request = scrapRequestRepository
                .findByIdAndStatus(requestId, RequestStatus.REQUESTED)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Request not available"));

        request.setCollector(collector);
        request.setStatus(RequestStatus.ACCEPTED);

        return scrapRequestRepository.save(request);
    }

    public List<CollectorRequestDto> getAvailableRequests() {

    List<ScrapRequest> requests =
            Optional.ofNullable(
                scrapRequestRepository.findByStatus(RequestStatus.REQUESTED)
            ).orElse(List.of());

    return requests.stream()
            .map(r -> CollectorRequestDto.builder()
                    .requestId(r.getId())
                    .address(r.getAddress())
                    .status(r.getStatus())
                    .build())
            .toList();
}

}
