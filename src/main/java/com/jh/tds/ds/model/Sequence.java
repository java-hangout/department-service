package com.jh.tds.ds.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "sequences")
public class Sequence {

    @Id
    private String id;        // This will be used to uniquely identify the sequence
    private int seqValue;     // Stores the current sequence value (e.g., 1, 2, 3, ...)
}
