package cn.marssky.dtfweb.entities;

import cn.marssky.common.api.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class QuestionResult<T> {
    private int code;
    private String message;
    private T data;
}
