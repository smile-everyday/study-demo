package cn.dark.service;

import cn.dark.vo.ResponseBean;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author dark
 * @date 2021-02-12
 */
@Service
public class NormalSearch {

    @Autowired
    private SendSearchRequest sendSearchRequest;

    /**
     * get kibana_sample_data_flights/_search
     * {
     * 	"from":0,
     * 	"size":5,
     * 	"query":{
     * 		"match_all":{}
     *        },
     * 	"_source":["Origin*","*Weather"],
     * 	"sort":[{"DistanceKilometers":"asc"},{"FlightNum":"desc"}]
     * }
     * @param indexName
     * @return
     */
    public ResponseBean searchExample(String indexName) throws IOException {
        SearchRequest searchRequest = new SearchRequest(indexName);
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.from(0);
        builder.size(5);
        builder.query(QueryBuilders.matchAllQuery());
        builder.fetchSource(new String[] {"Origin*", "*Weather"}, null);
        builder.sort(SortBuilders.fieldSort("DistanceKilometers").order(SortOrder.ASC))
                .sort(SortBuilders.fieldSort("FlightNum").order(SortOrder.ASC));
        searchRequest.source(builder);
        return sendSearchRequest.sendNormalSearchRequest(searchRequest);
    }

    /**
     * GET kibana_sample_data_flights/_search
     * {
     * 	"query":{
     * 		"term":{
     * 			"dayOfWeek":3
     *     }
     *   }
     * @param indexName
     * @param fieldName
     * @param value
     * @return
     */
    public ResponseBean termSearch(String indexName, String fieldName, Integer value) throws IOException {
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.termQuery(fieldName, value));

        SearchRequest searchRequest = new SearchRequest(indexName);
        searchRequest.source(builder);
        return sendSearchRequest.sendNormalSearchRequest(searchRequest);
    }

    /**
     * POST /kibana_sample_data_flights/_search
     * {
     * "query": {
     * "multi_match": {
     * "query":"AT",
     * "fields":["DestCountry", "OriginCountry"]
     * }
     * }
     *
     * @param indexName
     * @param text
     * @param fieldsNames
     * @return
     */
    public ResponseBean matchSearch(String indexName, String text, String... fieldsNames) throws IOException {
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.multiMatchQuery(text, fieldsNames));

        SearchRequest searchRequest = new SearchRequest(indexName);
        searchRequest.source(builder);
        return sendSearchRequest.sendNormalSearchRequest(searchRequest);
    }

    /**
     * GET kibana_sample_data_logs/_search
     * {
     *     "query": {
     *         "fuzzy": {
     *             "message": {
     *                 "value": "firefix",
     *                 "fuzziness": "1"
     *             }
     *         }
     *     }
     * }
     * @param indexName
     * @param fieldsName
     * @param text
     * @return
     */
    public ResponseBean fuzzySearch(String indexName, String fieldsName, String text) throws IOException {
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.fuzzyQuery(fieldsName, text).fuzziness(Fuzziness.ONE));

        SearchRequest searchRequest = new SearchRequest(new String[]{indexName}, builder);
        return sendSearchRequest.sendNormalSearchRequest(searchRequest);
    }

    /**
     * POST /kibana_sample_data_logs/_search
     * {
     * 	"query": {
     * 		"bool": {
     * 			"must":[
     *                                {"match": { "message": "firefox"} }
     * 			],
     * 			"should":[
     *                {"term": { "geo. src": "CN"}},
     *                {"term": { "geo. dest": "CN"}}
     * 			]
     * 		}
     * 	}
     * }
     * @param indexName
     * @return
     */
    public ResponseBean boolSearch(String indexName) throws IOException {
        BoolQueryBuilder query = QueryBuilders.boolQuery();
        query.must(QueryBuilders.matchQuery("message", "firefox"))
                .should(QueryBuilders.termQuery("geo.src", "CN"))
                .should(QueryBuilders.termQuery("geo.dest", "CN"));

        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(query);

        SearchRequest searchRequest = new SearchRequest(new String[]{indexName}, builder);
        return sendSearchRequest.sendNormalSearchRequest(searchRequest);
    }
}
