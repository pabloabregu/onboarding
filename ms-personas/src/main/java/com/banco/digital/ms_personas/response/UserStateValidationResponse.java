package com.banco.digital.ms_personas.response;

import com.banco.digital.ms_personas.enums.State;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserStateValidationResponse {
    private State state;
}
