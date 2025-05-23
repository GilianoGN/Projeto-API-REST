package dio.Projeto_API_REST.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import dio.Projeto_API_REST.controller.dto.FeatureDto;
import dio.Projeto_API_REST.service.FeatureService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;


@CrossOrigin
@RestController
@RequestMapping("/feature")
@Tag(name = "Feature Controller", description = "RESTful API for managing feature.")
public record FeatureController(FeatureService featureService) {

    @GetMapping
    @Operation(summary = "Get all feature", description = "Retrieve a list of all registeres feature")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Operation successful")})
    public ResponseEntity<List<FeatureDto>> findAll(){
        var features = featureService.findAll();
        var featureDto = features.stream().map(FeatureDto::new).collect(Collectors.toList());
        return ResponseEntity.ok(featureDto);
    }

    @PostMapping("")
    @Operation(summary = "Create a new feature", description = "Create a new feature and return the created feature's data")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Feature created sucessfully"),
        @ApiResponse(responseCode = "422", description = "Invalid feature data provided")
    })
    public ResponseEntity<FeatureDto> create(@RequestBody FeatureDto featureDto) {
        var feature = featureService.create(featureDto.toModel());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(feature.getFeature_Id())
            .toUri();
        return ResponseEntity.created(location).body(new FeatureDto(feature));
    }  

    @PutMapping("/{id}")
    @Operation(summary = "Update a feature", description = "Update the data of an existing feature based on its ID")
    @ApiResponses(value = { 
            @ApiResponse(responseCode = "200", description = "Feature updated successfully"),
            @ApiResponse(responseCode = "404", description = "Feature not found"),
            @ApiResponse(responseCode = "422", description = "Invalid feature data provided")
    })
    public ResponseEntity<FeatureDto> update(@PathVariable Long id, @RequestBody FeatureDto featureDto) {
        var feature = featureService.update(id, featureDto.toModel());
        return ResponseEntity.ok(new FeatureDto(feature));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a feature", description = "Delete an existing feature based on its ID")
    @ApiResponses(value = { 
            @ApiResponse(responseCode = "204", description = "Feature deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Feature not found")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        featureService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
