package cn.dark.mvc;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lwj
 * @date 2020-12-15
 */
@RequestMapping("/test/")
@RestController
public class MyController {

    @RequestMapping
    public Test test(@RequestBody @MyAnno Test test) {
        return new Test();
    }

}
