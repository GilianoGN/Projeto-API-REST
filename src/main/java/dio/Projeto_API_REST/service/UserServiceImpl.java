package dio.Projeto_API_REST.service;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import dio.Projeto_API_REST.model.User;
import dio.Projeto_API_REST.repository.UserRepository;
//import dio.Projeto_API_REST.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public User create(User userTocreate) {
        if (userRepository.existsByAccountNumber(userTocreate.getAccount().getNumber())){
            throw new IllegalArgumentException("This Account number already exists.");
        }
        return userRepository.save(userTocreate);
    }
}
