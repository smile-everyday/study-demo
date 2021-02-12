package cn.dark.controller;

import cn.dark.service.AggsSearch;
import cn.dark.service.NormalSearch;
import cn.dark.service.OperateDoc;
import cn.dark.service.OperateIndex;
import cn.dark.vo.ResponseBean;
import cn.dark.vo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @author dark
 * @date 2021-02-12
 */
@Api(value = "ES测试接口", tags = {"ES测试接口"})
@RestController
@RequestMapping("/es")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.POST, RequestMethod.PUT})
public class ESController {

    @Autowired
    private OperateIndex operatorIndex;
    @Autowired
    private OperateDoc operateDoc;
    @Autowired
    private NormalSearch normalSearch;
    @Autowired
    private AggsSearch aggsSearch;

    private final static String KIBANA_SAMPLE_DATA_FLIGHTS
            = "kibana_sample_data_flights";
    private final static String KIBANA_SAMPLE_DATA_LOGS
            = "kibana_sample_data_logs";

    @ApiOperation(value = "创建索引", notes = "创建索引")
    @PostMapping("/index/creation")
    public ResponseBean createIndex(@RequestParam String indexName) {
        try {
            if (operatorIndex.createIndex(indexName)) {
                return new ResponseBean(200, "创建成功", null);
            } else {
                return new ResponseBean(1002, "创建失败", null);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @ApiOperation(value = "判断索引是否存在", notes = "判断索引是否存在")
    @PostMapping("/index/existence")
    public ResponseBean indexExists(@RequestParam String indexName) throws IOException {
        boolean isExist = operatorIndex.isExist(indexName);
        return new ResponseBean(200, isExist ? "索引存在" : "索引不存在", isExist);
    }

    @ApiOperation(value = "删除索引", notes = "删除索引")
    @PostMapping("/index/delete")
    public ResponseBean deleteIndex(@RequestParam String indexName) throws IOException {
        boolean isDel = operatorIndex.deleteIndex(indexName);
        if (isDel) {
            return new ResponseBean(200, "删除成功", null);
        }
        return new ResponseBean(200, "删除失败", null);
    }

    @ApiOperation(value = "插入文档", notes = "插入文档")
    @PostMapping("/doc/insertion")
    public ResponseBean insertDoc(@RequestBody User user,
                                  @RequestParam String indexName,
                                  @RequestParam String docId) throws IOException {
        return operateDoc.insert(user, indexName, docId);
    }

    @ApiOperation(value = "获取文档", notes = "获取文档")
    @PostMapping("/doc/query")
    public ResponseBean queryDoc(@RequestParam String indexName,
                                 @RequestParam String docId) throws IOException {
        return operateDoc.queryDoc(indexName, docId);
    }

    @ApiOperation(value = "更新文档", notes = "更新文档")
    @PostMapping("/doc/update")
    public ResponseBean updateDoc(@RequestParam String indexName,
                                  @RequestParam String docId,
                                  @RequestParam String fieldName,
                                  @RequestParam String value) throws IOException {
        return operateDoc.updateDoc(indexName, docId, fieldName, value);
    }

    @ApiOperation(value = "删除文档", notes = "删除文档")
    @PostMapping("/doc/delete")
    public ResponseBean deleteDoc(@RequestParam String indexName,
                                  @RequestParam String docId) throws IOException {
        return operateDoc.deleteDoc(indexName, docId);
    }

    @ApiOperation(value = "search接口的基本用法", notes = "search接口的基本用法")
    @PostMapping("/search/example")
    public ResponseBean searchExample() throws IOException {
        return normalSearch.searchExample(KIBANA_SAMPLE_DATA_FLIGHTS);
    }

    @ApiOperation(value = "基于词项的查询", notes = "基于词项的查询")
    @PostMapping("/search/term")
    public ResponseBean termSearch(@RequestParam(defaultValue = "dayOfWeek") String fieldName,
                                   @RequestParam(defaultValue = "3") Integer value) throws IOException {
        return normalSearch.termSearch(KIBANA_SAMPLE_DATA_FLIGHTS, fieldName, value);
    }

    @ApiOperation(value = "基于全文的查询", notes = "基于全文的查询")
    @PostMapping("/search/match")
    public ResponseBean matchSearch() throws IOException {
        return normalSearch.matchSearch(KIBANA_SAMPLE_DATA_FLIGHTS, "AT", "DestCountry", "OriginCountry");
    }

    @ApiOperation(value = "基于全文的模糊查询", notes = "基于全文的模糊查询")
    @PostMapping("/search/fuzzy")
    public ResponseBean fuzzySearch() throws IOException {
        return normalSearch.fuzzySearch(KIBANA_SAMPLE_DATA_LOGS, "message", "firefix");
    }

    @ApiOperation(value = "组合查询", notes = "组合查询")
    @PostMapping("/search/combination")
    public ResponseBean combinationSearch() throws IOException {
        return normalSearch.boolSearch(KIBANA_SAMPLE_DATA_LOGS);
    }

    @ApiOperation(value = "聚合查询", notes = "聚合查询")
    @PostMapping("/search/aggsSearch")
    public ResponseBean aggsSearch() throws IOException {
        return aggsSearch.aggsSearch(KIBANA_SAMPLE_DATA_FLIGHTS);
    }

}

