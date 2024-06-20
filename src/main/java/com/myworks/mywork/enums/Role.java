package com.myworks.mywork.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.myworks.mywork.enums.Permission.*;

@RequiredArgsConstructor
public enum Role {
    USER(Set.of(TODO_LIST)),
    ADMIN(
            Set.of(
                    TODO_CREATE,
                    TODO_LIST,
                    TODO_DELETE,
                    TODO_UPDATE
            )
    ),
    MANAGER(
            Set.of(
                    TODO_CREATE,
                    TODO_LIST,
                    TODO_UPDATE
            )
    );

    @Getter
    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }

}
