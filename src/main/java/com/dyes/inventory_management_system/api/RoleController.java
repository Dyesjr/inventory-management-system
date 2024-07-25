package com.dyes.inventory_management_system.api;

import com.dyes.inventory_management_system.enums.Role;
import com.dyes.inventory_management_system.model.User;
import com.dyes.inventory_management_system.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final UserRepository userRepository;

    @Autowired
    public RoleController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PutMapping("/assign/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> assignRoleToUser(@PathVariable Long userId, @RequestBody Role role) {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        User user = userOptional.get();
        user.setRole(role);
        userRepository.save(user);

        return ResponseEntity.ok(user);
    }
}
