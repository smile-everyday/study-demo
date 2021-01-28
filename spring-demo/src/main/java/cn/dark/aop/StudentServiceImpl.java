package cn.dark.aop;

import cn.dark.aop.annotation.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author lwj
 * @date 2021-01-28
 */
@Slf4j
@Component
public class StudentServiceImpl implements StudentService<String> {

    @Log
    @Override
    public void study(String s) {
        log.info("student start study...");
    }
}
