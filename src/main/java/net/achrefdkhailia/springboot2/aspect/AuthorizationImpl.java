package net.achrefdkhailia.springboot2.aspect;

import org.springframework.stereotype.Component;

@Component
public class AuthorizationImpl {
    public boolean authorize(String token) {
        // implemnt jwt or any any token based authorization logic
        return true;
    }
}
