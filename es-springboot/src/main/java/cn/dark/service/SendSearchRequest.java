package cn.dark.service;

import cn.dark.vo.ResponseBean;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.histogram.Histogram;
import org.elasticsearch.search.aggregations.metrics.ParsedAvg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @author dark
 * @date 2021-02-12
 */
@Service
public class SendSearchRequest {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    public ResponseBean sendNormalSearchRequest(SearchRequest searchRequest) throws IOException {
        JSONArray jsonArray = new JSONArray();
        sendAndProcessHits(searchRequest, jsonArray);
        return new ResponseBean(200, "查询文档成功", jsonArray);
    }

    public ResponseBean sendAggsSearchRequest(SearchRequest searchRequest) throws IOException {
        JSONArray jsonArray = new JSONArray();
        SearchResponse searchResponse = sendAndProcessHits(searchRequest, jsonArray);
        Aggregations aggregations = searchResponse.getAggregations();
        for (Aggregation aggregation : aggregations) {
            jsonArray.add(aggregation);
            List<? extends Histogram.Bucket> buckets = ((Histogram) aggregation).getBuckets();
            for (Histogram.Bucket bucket : buckets) {
                System.out.println("-----------------------");
                System.out.println(bucket.getKeyAsString());
                System.out.println(bucket.getDocCount());

                ParsedAvg avg_price = (ParsedAvg) bucket.getAggregations().getAsMap().get("avg_price");
                System.out.println(avg_price.getValueAsString());
            }
        }
        return new ResponseBean(200, "聚合查询成功", jsonArray);
    }

    private SearchResponse sendAndProcessHits(SearchRequest searchRequest, JSONArray jsonArray) throws IOException {
        SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println("search接口响应报文：" + JSON.toJSONString(search));
        SearchHits hits = search.getHits();
        for (SearchHit hit : hits) {
            String source = hit.getSourceAsString();
            JSONObject jsonObject = JSONObject.parseObject(source);
            jsonArray.add(jsonObject);
        }
        return search;
    }
}
