package com.warta.task.Quotes.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuoteDto {
    private long id;

    @NotBlank(message = "First name is required")
    @Size(min = 1, max = 255)
    private String authorFirstName;

    @NotBlank(message = "Last name is required")
    @Size(min = 1, max = 255)
    private String authorLastName;

    @NotBlank(message = "Content is required")
    @Size(min = 1, max = 255)
    private String content;
}
