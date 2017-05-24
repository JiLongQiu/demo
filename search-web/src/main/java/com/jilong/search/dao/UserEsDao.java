package com.jilong.search.dao;

import com.google.common.collect.Lists;
import com.jilong.search.domain.user.User;
import com.jilong.search.domain.user.UserCondition;
import com.jilong.search.util.json.JsonUtil;
import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * @author jilong.qiu
 * @date 2017/4/28.
 */
@Component
public class UserEsDao {

    @Resource
    private Client client;

    public List<User> query(UserCondition cd) throws ExecutionException, InterruptedException {
        SearchRequestBuilder srb = client.prepareSearch("user").setTypes("user").setQuery(
                new MatchQueryBuilder("name", cd.getName())
                        .minimumShouldMatch("50%")
        );
        ActionFuture<SearchResponse> search = client.search(srb.request());
        SearchResponse sr = search.get();
        List<User> user = Lists.newArrayList();
        for (SearchHit hit : sr.getHits()) {
            Map<String, Object> source = hit.getSource();
            user.add(JsonUtil.readValue(source, User.class));
        }
        return user;
    }

    public User insert(User user) throws ExecutionException, InterruptedException {
        IndexRequestBuilder builder = client.prepareIndex("user", "user").setSource(JsonUtil.toString(user), XContentType.JSON);
        ActionFuture<IndexResponse> futureRes = client.index(builder.request());
        IndexResponse res = futureRes.get();
        return null;
    }

}
