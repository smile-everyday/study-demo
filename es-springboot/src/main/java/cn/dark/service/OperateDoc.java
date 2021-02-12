package cn.dark.service;

import cn.dark.vo.ResponseBean;
import cn.dark.vo.User;
import com.alibaba.fastjson.JSONObject;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author dark
 * @date 2021-02-12
 */
@Service
public class OperateDoc {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    public ResponseBean insert(User user, String indexName, String docId) throws IOException {
        IndexRequest indexRequest = new IndexRequest(indexName);
        if (!"noid".equalsIgnoreCase(docId)) {
            indexRequest.id(docId);
        }

        String userJson = JSONObject.toJSONString(user);
        indexRequest.source(userJson, XContentType.JSON);

        IndexResponse response = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        if (response != null) {
            if (DocWriteResponse.Result.CREATED.equals(response.getResult())) {
                return new ResponseBean(200, "新增文档成功", response.getId());
            }

            if (DocWriteResponse.Result.UPDATED.equals(response.getResult())) {
                return new ResponseBean(200, "覆盖文档成功", null);
            }
        }
        return null;
    }

    public ResponseBean queryDoc(String indexName, String docId) throws IOException {
        GetRequest getRequest = new GetRequest(indexName, docId);
        GetResponse response = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
        System.out.println("Index name：" + response.getIndex());
        if (response.isExists()) {
            return new ResponseBean(200, "查询文档成功", response.getSourceAsString());
        }
        return new ResponseBean(200, "查询文档不存在", null);
    }

    public ResponseBean updateDoc(String indexName, String docId, String fieldName, String value) throws IOException {
        XContentBuilder builder = XContentFactory.jsonBuilder()
                .startObject()
                .field(fieldName, value)
                // .timeField(fieldName, value) 日期需要使用该方法
                .endObject();

        UpdateRequest updateRequest = new UpdateRequest(indexName, docId);
        updateRequest.doc(builder);
        updateRequest.docAsUpsert(true); // 不存在该文档则直接新增
        updateRequest.fetchSource(true); // 应答报文中包含文档内容

        UpdateResponse update = restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
        if (update.getGetResult().isExists()) {
            return new ResponseBean(200, "更新文档成功", update.getGetResult().sourceAsString());
        }
        return new ResponseBean(200, "更新文档失败", indexName + "/" + docId + "/" + fieldName + "/" + value);
    }

    public ResponseBean deleteDoc(String indexName, String docId) throws IOException {
        DeleteRequest deleteRequest = new DeleteRequest(indexName, docId);
        DeleteResponse response = restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
        if (DocWriteResponse.Result.NOT_FOUND.equals(response.getResult())) {
            return new ResponseBean(200, "删除不存在的文档", indexName + "/" + docId);
        }
        return new ResponseBean(200, "删除文档成功", indexName + "/" + docId);
    }

}
