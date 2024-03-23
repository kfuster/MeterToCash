package fr.ekwateur.metertocash.mapper;

import fr.ekwateur.metertocash.dto.RequestDTO;
import fr.ekwateur.metertocash.model.Client;
import fr.ekwateur.metertocash.model.Individual;
import fr.ekwateur.metertocash.model.Professional;

public class ClientMapper {
    public static Client dtoToModel(RequestDTO requestDTO) {
        return switch (requestDTO.clientType()) {
            case PRO -> new Professional(requestDTO.reference(), ConsumptionMapper.dtosToModels(requestDTO.consumptions()), 12345678912324L, "client", requestDTO.revenue());
            case IND -> new Individual(requestDTO.reference(), ConsumptionMapper.dtosToModels(requestDTO.consumptions()), "title", "lastName", "firstName");
        };
    }
}
