package Mr.He.spring.common;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

/**
 * TODO 请求入参基本DTO
 *
 * @author Mr.He
 * @date 2019/9/21 17:47
 * e-mail wishforyou.xia@gmail.com
 * qq 294046317
 * pc-name 29404
 */
@Getter
@Setter
public abstract class BaseDTO {
    private String userId;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
