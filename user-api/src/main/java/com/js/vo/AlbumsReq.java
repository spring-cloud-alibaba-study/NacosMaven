package com.js.vo;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.checkerframework.checker.index.qual.Positive;
import org.jetbrains.annotations.NotNull;

/**
 * 在此处添加备注信息
 *
 * @author YourBatman
 * @since 0.0.1
 */
@Data
public class AlbumsReq {

    @NotNull
    @Positive
    private Long userId;
    @NotBlank
    private String title;

}



