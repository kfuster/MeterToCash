package fr.ekwateur.metertocash.mapper;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import fr.ekwateur.metertocash.dto.ClientTypeDTO;
import fr.ekwateur.metertocash.dto.RequestDTO;
import fr.ekwateur.metertocash.model.Client;
import fr.ekwateur.metertocash.model.Individual;
import fr.ekwateur.metertocash.model.Professional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ClientMapperTest {
    @Test
    void dtoToModel_mustConvertRequestDtosToProfessionalClient() {
        // Given
        RequestDTO requestDTO = new RequestDTO("EKW78412578", ClientTypeDTO.PRO, emptyList(), 40000.0);
        Professional expectedProfessional = new Professional("EKW78412578", emptyList(), 12345678912324L, "client", 40000.0);

        // When
        Client result = ClientMapper.dtoToModel(requestDTO);

        // Then
        assertNotNull(result);
        assertEquals(expectedProfessional, result);
    }

    @Test
    void dtoToModel_mustConvertRequestDtosToIndividualClient() {
        // Given
        RequestDTO requestDTO = new RequestDTO("EKW78412578", ClientTypeDTO.IND, emptyList(), 40000.0);
        Individual individual = new Individual("EKW78412578", emptyList(), "title", "lastName", "firstName");

        // When
        Client result = ClientMapper.dtoToModel(requestDTO);

        // Then
        assertNotNull(result);
        assertEquals(individual, result);
    }

}
