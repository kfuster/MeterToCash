package fr.ekwateur.metertocash.controller;

import static fr.ekwateur.metertocash.dto.ClientTypeDTO.PRO;

import fr.ekwateur.metertocash.dto.RequestDTO;
import fr.ekwateur.metertocash.dto.ResponseDTO;
import fr.ekwateur.metertocash.mapper.ClientMapper;
import fr.ekwateur.metertocash.service.BillingService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/billing")
public class BillingController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BillingController.class);

    private final BillingService billingService;

    public BillingController(BillingService billingService) {
        this.billingService = billingService;
    }

    @PostMapping
    public ResponseEntity<?> getBilling(@Valid @RequestBody RequestDTO requestDTO) {
        if (PRO.equals(requestDTO.clientType()) && requestDTO.revenue() == null) {
            return ResponseEntity.badRequest().body("A professional client must have a revenue value indicated");
        }
        LOGGER.info("Starting billing calculation: {}", requestDTO);
        double amount = billingService.calculateBillingAmount(ClientMapper.dtoToModel(requestDTO));
        LOGGER.info("Ended billing calculation, amount: {}", amount);
        return ResponseEntity.ok(new ResponseDTO(requestDTO.reference(), amount));
    }

}
