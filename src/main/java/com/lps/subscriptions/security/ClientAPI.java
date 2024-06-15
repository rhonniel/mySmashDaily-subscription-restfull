package com.lps.subscriptions.security;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class ClientAPI {
    private String clientId;
    private String clientSecret;
}
