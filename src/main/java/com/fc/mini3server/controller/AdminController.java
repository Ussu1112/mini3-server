package com.fc.mini3server.controller;

import com.fc.mini3server._core.utils.ApiUtils;
import com.fc.mini3server.domain.User;
import com.fc.mini3server.dto.AdminRequestDTO;
import com.fc.mini3server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.fc.mini3server.dto.AdminResponseDTO.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    @GetMapping("/")
    public ResponseEntity<?> findAll(Pageable pageable) {
        final List<User> userList = userService.findAll(pageable).getContent();
        return ResponseEntity.ok(ApiUtils.success(AdminUserListDTO.listOf(userList)));
    }

    @PostMapping("/users/{id}/auth")
    public ResponseEntity<?> editAuth(@PathVariable Long id, @RequestBody AdminRequestDTO.editAuthDTO requestDTO){
        userService.updateUserAuth(id, requestDTO);
        return ResponseEntity.ok(ApiUtils.success(null));
    }
}