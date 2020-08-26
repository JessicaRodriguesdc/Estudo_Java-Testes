package com.library.libraryapi.api.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BooKDTO {

    private Long id;
    private String title;
    private String author;
    private String isbn;


}
