package com.example.ladiadminservice.uitl;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import javax.annotation.ManagedBean;

@Component
@Data
public class ContextUtil {

    private String userName;
    private Long userId;
}
