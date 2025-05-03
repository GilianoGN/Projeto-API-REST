package dio.Projeto_API_REST.service;

import dio.Projeto_API_REST.model.User;

public interface UserService {
    User findById(Long id);
    
    User create(User userTocreate);
}
