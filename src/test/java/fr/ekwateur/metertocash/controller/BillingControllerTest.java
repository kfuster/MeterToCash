package fr.ekwateur.metertocash.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.ekwateur.metertocash.dto.ClientTypeDTO;
import fr.ekwateur.metertocash.dto.ConsumptionDTO;
import fr.ekwateur.metertocash.dto.RequestDTO;
import fr.ekwateur.metertocash.model.EnergyType;
import fr.ekwateur.metertocash.service.BillingService;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
class BillingControllerTest {
    @InjectMocks
    private BillingController billingController;
    @Mock
    private BillingService billingService;
    private MockMvc mockMvc;

    private final ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(billingController).build();
    }

    @Test
    void getBilling_mustReturnBillingData() throws Exception {
        // Given
        ConsumptionDTO consumptionDTO = new ConsumptionDTO(EnergyType.ELEC, 500);
        RequestDTO requestDTO = new RequestDTO("EKW78412578", ClientTypeDTO.IND, List.of(consumptionDTO), null);
        when(billingService.calculateBillingAmount(any())).thenReturn(500.0);

        // When
        mockMvc.perform(post("/billing")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(requestDTO)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.clientReference").value("EKW78412578"))
            .andExpect(jsonPath("$.billingAmount").value("500.0"));

        // Then
        verify(billingService, times(1)).calculateBillingAmount(any());
    }

    @Test
    void getBilling_mustThrowError_whenReferenceNotValid() throws Exception {
        // Given
        ConsumptionDTO consumptionDTO = new ConsumptionDTO(EnergyType.ELEC, 500);
        RequestDTO requestDTO = new RequestDTO("EKA78412578", ClientTypeDTO.IND, List.of(consumptionDTO), null);

        // When Then
        mockMvc.perform(post("/billing")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(requestDTO)))
            .andExpect(status().is4xxClientError());
    }

    @Test
    void getBilling_mustThrowError_whenRevenueNull_AndProfessionalClient() throws Exception {
        // Given
        ConsumptionDTO consumptionDTO = new ConsumptionDTO(EnergyType.ELEC, 500);
        RequestDTO requestDTO = new RequestDTO("EKW78412578", ClientTypeDTO.PRO, List.of(consumptionDTO), null);

        // When Then
        mockMvc.perform(post("/billing")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(requestDTO)))
            .andExpect(status().is4xxClientError())
            .andExpect(jsonPath("$").value("A professional client must have a revenue value indicated"));

    }

}
