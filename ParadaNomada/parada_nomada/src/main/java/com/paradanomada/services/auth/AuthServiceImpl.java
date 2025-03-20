package com.paradanomada.services.auth;

import com.paradanomada.dto.SignupRequest;
import com.paradanomada.dto.UserDto;
import com.paradanomada.entity.User;
import com.paradanomada.enums.UserRole;
import com.paradanomada.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    private final UserRepository userRepository;

    // Constructor manual
    public AuthServiceImpl(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @PostConstruct
    public void createAdminAccount() {
        User adminAccount = userRepository.findByUserRole(UserRole.ADMIN);
        if(adminAccount == null) {
            User newAdminAccount = new User();
            newAdminAccount.setName("Admin");
            newAdminAccount.setEmail("admin@test.com");
            newAdminAccount.setPassword(new BCryptPasswordEncoder().encode("admin"));
            newAdminAccount.setUserRole(UserRole.ADMIN);
            userRepository.save(newAdminAccount);
            System.out.println("cuenta de administrador creada exitosamente");
        }
    }

    @Override
    public UserDto createCustomer(SignupRequest signupRequest) {
        // Verificar si ya existe un usuario con ese correo electrónico
        if (hasCustomerWithEmail(signupRequest.getEmail())) {
            throw new RuntimeException("El correo ya existe");  // O maneja este error según tus necesidades
        }
        // Crear una nueva instancia de User
        User user = new User();
        user.setName(signupRequest.getName());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
        user.setUserRole(UserRole.CUSTOMER);

        // Guardar el usuario en la base de datos
        User createdUser = userRepository.save(user);



        // Crear un objeto UserDto y asignar los valores
        UserDto userDto = new UserDto();
        userDto.setId(createdUser.getId());

        return userDto;  // Devolver el objeto UserDto con los datos completos
    }


    @Override
    public boolean hasCustomerWithEmail(String email) {
        return userRepository.findFirstByEmail(email).isPresent();
    }
}
