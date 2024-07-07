package com.inghub.stock.contract.error;

import java.util.Arrays;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiError {
    private String type;
    private String title;
    private List<InvalidParams> invalidParams;

    public ApiError(String type, String title, List<InvalidParams> invalidParams) {
        super();
        this.type = type;
        this.title = title;
        this.invalidParams = invalidParams;
    }

    public ApiError(String type, String title, InvalidParams invalidParam) {
        super();
        this.type = type;
        this.title = title;
        invalidParams = Arrays.asList(invalidParam);
    }
}
