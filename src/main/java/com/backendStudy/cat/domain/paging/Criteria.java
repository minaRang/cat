package com.backendStudy.cat.domain.paging;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Getter
@Setter
public class Criteria {
    private int page;
    private int recordsPerPage;
    private int pageSize;
    private String searchKeyword;
    private String searchType;

    public Criteria() {
        this.page=1;
        this.recordsPerPage=10;
        this.pageSize=10;
    }
    public int getStartPage(){
        return (page-1)*recordsPerPage;
    }
    public String makeQueryString(int pageNo){
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .queryParam("Page", pageNo)
                .queryParam("recordsPerPage", recordsPerPage)
                .queryParam("searchType", searchType)
                .queryParam("searchKeyword", searchKeyword)
                .build()
                .encode();
        return uriComponents.toUriString();
    }
}
