package com.baum.canteenApp.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerJson {

    private String userId;

    private String firstName;

    private String lastName;
}

