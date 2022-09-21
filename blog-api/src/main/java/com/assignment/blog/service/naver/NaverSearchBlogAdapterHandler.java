package com.assignment.blog.service.naver;

import com.assignment.blog.common.api.pattern.AbstractHandlerBase;
import com.assignment.blog.common.api.pattern.HandlerOrder;
import com.assignment.blog.dto.*;
import com.assignment.blog.dto.naver.NaverSearchBlogRequest;
import com.assignment.blog.dto.naver.NaverSearchBlogResponse;
import com.assignment.blog.dto.HandlerResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@HandlerOrder(order = 20)
public class NaverSearchBlogAdapterHandler extends AbstractHandlerBase<SearchBlogRequest, HandlerResponse> {
    private final NaverFeignClient naverFeignClient;

    @Override
    protected HandlerResponse resolve(SearchBlogRequest param, HandlerResponse result) {
        if(!result.isSearch()) {
            try {
                Integer page = param.getPage();
                int start = (page - 1) * param.getSize() + 1;  //page를 start로 변환
                String sort = param.sort();
                if(sort.equals(SearchBlogRequest.Sort.accuracy.toString()))
                    sort = "sim";
                else
                    sort = "date";

                SearchBlogResponse searchBlogResponse = getSearchBlogResponse(param, page, start, sort);

                result = HandlerResponse.builder()
                        .isSearch(true)
                        .isSuccess(true)
                        .searchBlogResponse(searchBlogResponse)
                        .build();
            } catch (Exception e) {
                log.error("Naver Blog Search Failed : " + e.getMessage());
                result.setSearch(false);
                result.setSuccess(false);
            }
        } else {
            result.setSuccess(true);
        }

        return result;
    }

    /**
     *네이버 open API 호출 후 Response 생성
     *
     * @param param
     * @param page
     * @param start
     * @param sort
     * @return SearchBlogResponse
     */
    private SearchBlogResponse getSearchBlogResponse(SearchBlogRequest param, Integer page, int start, String sort) {
        NaverSearchBlogResponse naverSearchBlogResponse = naverFeignClient.getBlogs(NaverSearchBlogRequest.builder()
                .query(param.query())
                .sort(sort)
                .display(param.getSize())
                .start(start)
                .build());

        SearchBlogResponse searchBlogResponse = SearchBlogResponse.builder()
                .meta(getMetaFromResponse(naverSearchBlogResponse, page))
                .documents(naverSearchBlogResponse.getItems())
                .build();
        return searchBlogResponse;
    }

    /**
     * 네이버 open API Response를 Meta객체로 변환
     *
     * @param response
     * @param page
     * @return Meta
     */
    private Meta getMetaFromResponse(NaverSearchBlogResponse response, Integer page) {
        int endPage = (int) Math.ceil(response.getTotal() / response.getDisplay());
        boolean isEnd = !(page < endPage);

        return Meta.builder()
                .totalCount(response.getTotal())
                .isEnd(isEnd)
                .build();
    }
}
