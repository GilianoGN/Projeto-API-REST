package dio.Projeto_API_REST.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dio.Projeto_API_REST.model.Feature;

@Repository
public interface FeatureRepository extends JpaRepository<Feature, Long>{
    
}
