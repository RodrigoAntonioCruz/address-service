package com.example.adapter.input.controller;

import com.example.adapter.input.controller.dto.ProductRequest;
import com.example.adapter.input.controller.dto.ProductResponse;
import com.example.adapter.input.controller.mapper.ProductInputMapper;
import com.example.adapter.input.controller.utils.Constants;
import com.example.adapter.input.controller.utils.PageUtils;
import com.example.domain.ports.input.DeleteProductInputPort;
import com.example.domain.ports.input.FindProductByFiltersInputPort;
import com.example.domain.ports.input.FindProductByIdInputPort;
import com.example.domain.ports.input.SaveProductInputPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@Tag(name = "Product")
@RequestMapping("/products")
public class ProductController {

    private final ProductInputMapper mapper;

    private final SaveProductInputPort saveProductInputPort;

    private final FindProductByIdInputPort findProductByIdInputPort;

    private final FindProductByFiltersInputPort findProductByFiltersInputPort;

    private final DeleteProductInputPort deleteProductInputPort;

    @PostMapping
    @Operation(description = "Cria um novo produto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Registro criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Inconsistência nos dados informados"),
            @ApiResponse(responseCode = "401", description = "Acesso não autorizado"),
            @ApiResponse(responseCode = "409", description = "Duplicidade nos dados informados"),
            @ApiResponse(responseCode = "500", description = "Sistema indisponível no momento")})
    public ResponseEntity<ProductResponse> create(@RequestBody ProductRequest request) {

        log.info(Constants.LOG_KEY_MESSAGE + Constants.LOG_KEY_METHOD + Constants.LOG_KEY_ENTITY,
                "Início da criação de um produto ", Constants.LOG_METHOD_CREATE, request);

        var product = mapper.toProduct(request);

        log.info(Constants.LOG_KEY_MESSAGE + Constants.LOG_KEY_METHOD + Constants.LOG_KEY_ENTITY,
                "Fim da criação de um produto ", Constants.LOG_METHOD_CREATE, request);

        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toProductResponse(saveProductInputPort.save(product)));
    }

    @PutMapping("/{id}")
    @Operation(description = "Atualiza um usuário existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Inconsistência nos dados informados"),
            @ApiResponse(responseCode = "401", description = "Acesso não autorizado"),
            @ApiResponse(responseCode = "404", description = "Registro não encontrado"),
            @ApiResponse(responseCode = "409", description = "Duplicidade nos dados informados"),
            @ApiResponse(responseCode = "500", description = "Sistema indisponível no momento")})
    public ResponseEntity<ProductResponse> update(@PathVariable String id, @RequestBody ProductRequest request) {

        log.info(Constants.LOG_KEY_MESSAGE + Constants.LOG_KEY_METHOD + Constants.LOG_KEY_ENTITY_ID + Constants.LOG_KEY_ENTITY,
                "Início da atualização de um produto ", Constants.LOG_METHOD_UPDATE, id, request);

        var product = mapper.toProduct(request);

        log.info(Constants.LOG_KEY_MESSAGE + Constants.LOG_KEY_METHOD + Constants.LOG_KEY_ENTITY_ID + Constants.LOG_KEY_ENTITY,
                "Fim da atualização de um produto ", Constants.LOG_METHOD_UPDATE, product.getId(), product);

        return ResponseEntity.ok(mapper.toProductResponse(saveProductInputPort.save(id, product)));
    }

    @GetMapping("/{id}")
    @Operation(description = "Busca um usuário por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitação realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Inconsistência nos dados informados"),
            @ApiResponse(responseCode = "401", description = "Acesso não autorizado"),
            @ApiResponse(responseCode = "404", description = "Registro não encontrado"),
            @ApiResponse(responseCode = "500", description = "Sistema indisponível no momento")})
    public ResponseEntity<ProductResponse> findById(@PathVariable String id) {

        log.info(Constants.LOG_KEY_MESSAGE + Constants.LOG_KEY_METHOD + Constants.LOG_KEY_ENTITY_ID,
                "Início da busca de um produto por id ", Constants.LOG_METHOD_FIND_BY_ID, id);

        var product = mapper.toProductResponse(findProductByIdInputPort.findById(id));

        log.info(Constants.LOG_KEY_MESSAGE + Constants.LOG_KEY_METHOD + Constants.LOG_KEY_ENTITY,
                "Fim da busca de um produto por id ", Constants.LOG_METHOD_FIND_BY_ID, product);

        return ResponseEntity.ok(product);
    }

    @GetMapping
    @Operation(description = "Busca paginada de produtos por filtros")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitação realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Inconsistência nos dados informados."),
            @ApiResponse(responseCode = "401", description = "Acesso não autorizado"),
            @ApiResponse(responseCode = "500", description = "Sistema indisponível no momento")})
    public ResponseEntity<Page<ProductResponse>> findByFilter(@Valid
                                                              @RequestParam(value = "query", defaultValue = "") String query,
                                                              @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                              @RequestParam(value = "linesPerPage", defaultValue = "100") Integer linesPerPage,
                                                              @RequestParam(value = "direction", defaultValue = "ASC") String direction,
                                                              @RequestParam(value = "orderBy", defaultValue = "id") String orderBy) {

        var list = findProductByFiltersInputPort.findByFilter(query);

        log.info(Constants.LOG_KEY_MESSAGE + Constants.LOG_KEY_METHOD + Constants.LOG_KEY_ENTITY_ID,
                "Início da busca da paginada de produtos por filtros ", Constants.LOG_METHOD_FIND_BY_FILTER, list);

        var pageable = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);

        var pages = PageUtils.toPage(list, pageable, list.size(), mapper::toProductResponse, orderBy, ProductResponse.class);

        log.info(Constants.LOG_KEY_MESSAGE + Constants.LOG_KEY_METHOD + Constants.LOG_KEY_ENTITY,
                "Fim da busca da paginada de produtos por filtros ", Constants.LOG_METHOD_FIND_BY_FILTER, pages.getContent());

        return ResponseEntity.ok().body(pages);
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Remove um usuário existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Solicitação sem conteúdo realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Inconsistência nos dados informados."),
            @ApiResponse(responseCode = "401", description = "Acesso não autorizado"),
            @ApiResponse(responseCode = "404", description = "Registro não encontrado"),
            @ApiResponse(responseCode = "500", description = "Sistema indisponível no momento")})
    public ResponseEntity<Void> deleteById(@PathVariable String id) {

        log.info(Constants.LOG_KEY_MESSAGE + Constants.LOG_KEY_METHOD + Constants.LOG_KEY_ENTITY_ID,
                "Início da exclusão de um produto ", Constants.LOG_METHOD_DELETE_BY_ID, id);

        deleteProductInputPort.deleteById(id);

        log.info(Constants.LOG_KEY_MESSAGE + Constants.LOG_KEY_METHOD + Constants.LOG_KEY_ENTITY_ID,
                "Fim da exclusão de um produto ", Constants.LOG_METHOD_DELETE_BY_ID, id);

        return ResponseEntity.noContent().build();
    }
}
