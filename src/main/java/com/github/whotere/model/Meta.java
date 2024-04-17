package com.github.whotere.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Meta(
        Integer currentPage,
        Integer nextPage,
        Integer previousPage,
        Integer totalPages,
        Integer perPage,
        Integer totalCount
) {}