package com.github.whotere.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Appearance(
        String backgroundColor,
        String imageColor,
        String cornerColor,
        Boolean blackTheme,
        String logoImage,
        String sideImage,
        String miniLogoImage
) {}
