package com.lps.subscriptions.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;


@SpringJUnitConfig(classes = JwtTokenUtil.class)
/*Esto es una combinación de @ExtendWith(SpringExtension.class) y @ContextConfiguration que se usa para cargar un
contexto de aplicación de Spring de una manera ligera. Aquí se especifica solo la clase MyComponent para el contexto.*/
@TestPropertySource(locations = "file:src/test/resources/application-test.properties")
public class JwtTokenTest {


    @Autowired
    JwtTokenUtil jwtTokenUtil;


    @Test
    public void createTokenSucceed(){
        UUID id=UUID.randomUUID();
        String token= jwtTokenUtil.generateToken(id);

       assertEquals(jwtTokenUtil.extractEntityId(token),id);

    }


    @Test
    public void  extractClaimFail(){
        assertThrows(RuntimeException.class, () -> {
            jwtTokenUtil.extractEntityId("test token");
        });

    }


}
