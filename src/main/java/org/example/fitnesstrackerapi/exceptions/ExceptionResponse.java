package org.example.fitnesstrackerapi.exceptions;

import lombok.Builder;

import java.time.ZonedDateTime;

@Builder
public record ExceptionResponse(
        int status,
        String reason,
        ZonedDateTime timestamp
) {
}