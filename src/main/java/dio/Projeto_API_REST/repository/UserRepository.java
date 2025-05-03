package dio.Projeto_API_REST.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dio.Projeto_API_REST.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    
}
