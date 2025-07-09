package com.example.adapter.output.repository;


import com.example.adapter.output.aspect.Tenant;
import com.example.adapter.output.repository.mapper.ProductOutputMapper;
import com.example.adapter.output.repository.utils.Constants;
import com.example.domain.entities.Address;
import com.example.domain.ports.output.DeleteProductOutputPort;
import com.example.domain.ports.output.FindProductByFiltersOutputPort;
import com.example.domain.ports.output.FindProductByIdOutputPort;
import com.example.domain.ports.output.SaveProductOutputPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@AllArgsConstructor
public class AddressRepositoryImpl implements SaveProductOutputPort,
                                              FindProductByIdOutputPort,
                                              FindProductByFiltersOutputPort {

    private final ProductOutputMapper mapper;

    private final ProductEntityRepository repository;
    @Override
    public Address save(Address product) {

        log.info(Constants.LOG_KEY_MESSAGE + Constants.LOG_KEY_METHOD + Constants.LOG_KEY_ENTITY,
                "Início da persistência de um produto ", Constants.LOG_METHOD_SAVE, product);

        var entity = mapper.toProductEntity(product);

        entity = repository.save(entity);

        log.info(Constants.LOG_KEY_MESSAGE + Constants.LOG_KEY_METHOD + Constants.LOG_KEY_ENTITY,
                "Fim da persistência de um produto ", Constants.LOG_METHOD_SAVE, entity);

        return mapper.toDomain(entity);
    }

    @Override
    public Optional<Address> findById(String productId) {
        log.info(Constants.LOG_KEY_MESSAGE + Constants.LOG_KEY_METHOD + Constants.LOG_KEY_ENTITY_ID,
                "Início da busca de um produto por id ", Constants.LOG_METHOD_FIND_BY_ID, productId);

        var entity = repository.findById(productId);

        log.info(Constants.LOG_KEY_MESSAGE + Constants.LOG_KEY_METHOD + Constants.LOG_KEY_ENTITY,
                "Fim da busca de um produto por id ", Constants.LOG_METHOD_FIND_BY_ID, entity);

        return entity.map(mapper::toDomain);
    }

    @Override
    public List<Address> findByFilter(String query) {

        log.info(Constants.LOG_KEY_MESSAGE + Constants.LOG_KEY_METHOD + Constants.LOG_KEY_ENTITY_ID,
                "Início da busca da lista de produtos por filtros ", Constants.LOG_METHOD_FIND_BY_FILTER, query);

        var entityList = repository.findByFilter(query);

        System.out.println("LISTA: " + entityList);

        log.info(Constants.LOG_KEY_MESSAGE + Constants.LOG_KEY_METHOD + Constants.LOG_KEY_ENTITY,
                "Fim da busca da lista de produtos por filtros ", Constants.LOG_METHOD_FIND_BY_FILTER, entityList);

        return entityList.stream().map(mapper::toDomain).toList();
    }

    @Override
    public void deleteById(String productId) {
        log.info("Início da exclusão do produto com id: " + productId);

        repository.deleteById(productId);

        log.info("Fim da exclusão do produto com id: " + productId);
    }

}


