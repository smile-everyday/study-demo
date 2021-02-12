package cn.dark.service;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.IndicesOptions;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author dark
 * @date 2021-02-12
 */
@Service
public class OperateIndex {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    public boolean createIndex(String indexName) throws IOException {
        XContentBuilder builder = XContentFactory.jsonBuilder()
                .startObject()
                    .field("properties").startObject()
                        .field("firstName").startObject()
                            .field("type", "keyword")
                        .endObject()
                        .field("secondName").startObject()
                            .field("type", "keyword")
                        .endObject()
                        .field("age").startObject()
                            .field("type", "integer")
                        .endObject()
                        .field("content").startObject()
                            .field("type", "text")
                        .endObject()
                    .endObject()
                .endObject();
        CreateIndexRequest indexRequest = new CreateIndexRequest(indexName);
        indexRequest.mapping(builder);
        CreateIndexResponse indexResponse = restHighLevelClient.indices().create(indexRequest, RequestOptions.DEFAULT);
        return indexResponse.isAcknowledged();
    }

    public boolean isExist(String indexName) throws IOException {
        GetIndexRequest getIndexRequest = new GetIndexRequest(indexName);
        getIndexRequest.humanReadable(true);
        return restHighLevelClient.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
    }

    public boolean deleteIndex(String indexName) throws IOException {
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(indexName);
        // 索引不存在也不会抛出异常
        deleteIndexRequest.indicesOptions(IndicesOptions.LENIENT_EXPAND_OPEN);
        AcknowledgedResponse response = restHighLevelClient.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
        return response.isAcknowledged();
    }
}
