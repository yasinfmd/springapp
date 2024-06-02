package com.myworks.mywork.response;

import lombok.Data;

@Data
public class BasePaginationResponse extends BaseResponse {
    private int page;
    private int size;
    private long totalCount;

    public static <T> BasePaginationResponse success(T data, int page, int size, long totalCount) {
        BasePaginationResponse basePaginationResponse = new BasePaginationResponse();
        basePaginationResponse.setSuccess(true);
        basePaginationResponse.setData(data);
        basePaginationResponse.setPage(page);
        basePaginationResponse.setSize(size);
        basePaginationResponse.setTotalCount(totalCount);
        return basePaginationResponse;
    }
}
