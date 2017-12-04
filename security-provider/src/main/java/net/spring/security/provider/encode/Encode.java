package net.spring.security.provider.encode;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

public class Encode {
    public static void main(String[] args) {
        Md5PasswordEncoder encoder = new Md5PasswordEncoder();
        encoder.setEncodeHashAsBase64(true);
        System.out.println(encoder.encodePassword("user", "user0"));
    }
}
