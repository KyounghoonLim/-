package com.daki.common.util;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@NoArgsConstructor
@Table(name = "refresh_token")
@Entity
public class RefreshToken {

    @Id
    private String tokenKey;
    private String tokenValue;

    public RefreshToken updateValue(String token) {
        this.tokenValue = token;
        return this;
    }

    @Builder
    public RefreshToken(String tokenKey, String tokenValue) {
        this.tokenKey = tokenKey;
        this.tokenValue = tokenValue;
    }
}
