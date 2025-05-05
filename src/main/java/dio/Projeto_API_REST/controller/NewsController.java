package dio.Projeto_API_REST.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import dio.Projeto_API_REST.controller.dto.NewsDto;
import dio.Projeto_API_REST.service.NewsService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;


@CrossOrigin
@RestController
@RequestMapping("/news")
@Tag(name = "News Controller", description = "RESTful API for managing news.")
public record NewsController(NewsService newsService) {

    @GetMapping
    @Operation(summary = "Get all news", description = "Retrieve a list of all registeres news")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Operation successful")})
    public ResponseEntity<List<NewsDto>> findAll(){
        var newss = newsService.findAll();
        var newsDto = newss.stream().map(NewsDto::new).collect(Collectors.toList());
        return ResponseEntity.ok(newsDto);
    }

    @PostMapping("")
    @Operation(summary = "Create a new news", description = "Create a new news and return the created news's data")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "News created sucessfully"),
        @ApiResponse(responseCode = "422", description = "Invalid news data provided")
    })
    public ResponseEntity<NewsDto> create(@RequestBody NewsDto newsDto) {
        var news = newsService.create(newsDto.toModel());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(news.getNews_Id())
            .toUri();
        return ResponseEntity.created(location).body(new NewsDto(news));
    }  

    @PutMapping("/{id}")
    @Operation(summary = "Update a news", description = "Update the data of an existing news based on its ID")
    @ApiResponses(value = { 
            @ApiResponse(responseCode = "200", description = "News updated successfully"),
            @ApiResponse(responseCode = "404", description = "News not found"),
            @ApiResponse(responseCode = "422", description = "Invalid news data provided")
    })
    public ResponseEntity<NewsDto> update(@PathVariable Long id, @RequestBody NewsDto newsDto) {
        var news = newsService.update(id, newsDto.toModel());
        return ResponseEntity.ok(new NewsDto(news));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a news", description = "Delete an existing news based on its ID")
    @ApiResponses(value = { 
            @ApiResponse(responseCode = "204", description = "News deleted successfully"),
            @ApiResponse(responseCode = "404", description = "News not found")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        newsService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
