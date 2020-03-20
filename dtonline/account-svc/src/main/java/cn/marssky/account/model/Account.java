package cn.marssky.account.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {

    private String id;
    private String name;
    private String email;
    private String phoneNumber;
    private String photoUrl;
    private Instant memberSince;
}
