package tn.post.client.enumerate;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public enum Permission {

    POST_CREATE,
    POST_READ,
    POST_UPDATE,
    POST_DELETE,

    FORMATION_CREATE,
    FORMATION_READ,
    FORMATION_UPDATE,
    FORMATION_DELETE,

    USER_CREATE,
    USER_READ,
    USER_UPDATE,
    USER_DELETE;

     public static Set<Permission> ListAdminPermission(){
            return Arrays.stream(Permission.values()).collect(Collectors.toSet());
     }

    public static Set<Permission> ListFormateurPermission(){
         Set<Permission> set = new HashSet<>();
                 set.add(FORMATION_READ);
        return  set;
    }

    public static Set<Permission> ListCandiatPermission(){
        Set<Permission> set = new HashSet<>();
        set.add(FORMATION_READ);
        set.add(USER_CREATE);
        set.add(USER_UPDATE);
        set.add(USER_READ);
        set.add(USER_DELETE);
        return set;
    }


 @Getter
    private String permission;


}
