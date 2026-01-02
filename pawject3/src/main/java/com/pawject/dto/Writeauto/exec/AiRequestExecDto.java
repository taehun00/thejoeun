package com.pawject.dto.Writeauto.exec;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AiRequestExecDto {
    private String topic;     // 주제
    private String keyword;   // 키워드
    private int length;       // 글 길이
}
