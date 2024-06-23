package com.lps.subscriptions.model.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubscriberDTO {
    @NotBlank
    private String name;
    @NotNull
    private List<SubscriptionDTO> subscriptionDTOS;
    @NotBlank
    private String email;
}
