package dio.Projeto_API_REST.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dio.Projeto_API_REST.model.News;

@Repository
public interface NewsRepository extends JpaRepository<News, Long>{
    
}
