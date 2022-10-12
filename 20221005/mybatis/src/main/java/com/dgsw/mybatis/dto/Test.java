package com.dgsw.mybatis.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Test {

    private Integer idx;

    @NotEmpty
    @Length(min = 4,message = "최소값이 4이상이어야합니다.")
    private String aa;

    @NotEmpty
    private String bb;

}
