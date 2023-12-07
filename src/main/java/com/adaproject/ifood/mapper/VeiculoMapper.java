package com.adaproject.ifood.mapper;

import com.adaproject.ifood.model.Entregador;
import com.adaproject.ifood.model.Veiculo;
import com.adaproject.ifood.model.dto.VeiculoDTO;
import com.adaproject.ifood.service.EntregadorService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = EntregadorService.class)
public interface VeiculoMapper {

    @Mapping(target = "entregador", source = "entregadorId", qualifiedByName = "idToEntregador")
    Veiculo toVeiculo(VeiculoDTO veiculoCreateDTO);

    @Mapping(target = "entregadorId", source = "entregador.id")
    VeiculoDTO toVeiculoDTO(Veiculo veiculo);

    @Named("idToEntregador")
    default Entregador idToEntregador(Long entregadorId) {
        throw new UnsupportedOperationException("Mapeamento não suportado diretamente. Use o método do serviço.");
    }
}
