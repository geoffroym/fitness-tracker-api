package org.example.fitnesstrackerapi.exception;

import lombok.Builder;

import java.time.ZonedDateTime;

@Builder
public record ApiExceptionResponse(
        int status,
        String reason,
        ZonedDateTime timestamp
) {
}
