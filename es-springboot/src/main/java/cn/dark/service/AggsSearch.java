package cn.dark.service;

import cn.dark.vo.ResponseBean;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author dark
 * @date 2021-02-12
 */
@Service
public class AggsSearch {

    @Autowired
    private SendSearchRequest sendSearchRequest;

    /**
     * 需要注意high level不支持filter_path参数，所以响应报文必须对hits和aggregatios进行处理
     *
     * POST /kibana_sample_data_flights/_search?filter_path=aggregations
     * {
     * 	"query": {
     * 		"term": {"OriginCountry": "CN"}
     *        },
     * 	"aggs":
     *    {
     * 		"date_price_histogram": {
     * 			"date_histogram": {
     * 				"field": "timestamp",
     * 				"interval": "month"
     *            },
     * 			"aggs": {
     * 				"avg_price": {"avg": {"field": "FlightDelayMin"}}
     *            }
     *        }
     *    }
     * }
     * @param indexName
     * @return
     */
    public ResponseBean aggsSearch(String indexName) throws IOException {

        DateHistogramAggregationBuilder aggregation = AggregationBuilders.dateHistogram("date_histogram")
                .field("timestamp")
                .fixedInterval(DateHistogramInterval.days(30))
                .subAggregation(AggregationBuilders.avg("avg_price").field("FlightDelayMin"));

        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.termQuery("OriginCountry", "CN"))
                .aggregation(aggregation);

        SearchRequest searchRequest = new SearchRequest(indexName);
        searchRequest.source(builder);

        return sendSearchRequest.sendAggsSearchRequest(searchRequest);
    }
}
