package com.rosa.rosaRest.dtos;

import com.rosa.rosaRest.Enum.StatusEnum;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.antlr.v4.runtime.misc.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ProductRecordDto(  @NotBlank String name,
                                 @NotBlank StatusEnum status,
                                 String destination,
                                 @Min(1) @Max(20)Integer profitRate,
                                 @Min(0) @Max(48)Integer term,
                                 Double administrationRate,
                                 @FutureOrPresent LocalDate dueDate,
                                 @NotBlank Boolean dailyLiquidity) {

}
