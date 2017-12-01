package com.shinez.springboot;

import com.shinez.springboot.common.WNSearch;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static com.shinez.springboot.common.WNConstants.*;
import static com.shinez.springboot.common.WNUtils.getCheckReqXSS;
import static com.shinez.springboot.common.WNUtils.getCurrentDate;
import static com.shinez.springboot.common.WNUtils.parseInt;

@RestController
@SpringBootApplication
public class SpringbootApplication {
	@RequestMapping(value = "/search.do", produces = "application/json; charset=utf8")
	public String search(HttpServletRequest request) {
		WNSearch wnsearch = null;
		//검색페이지 공통 파라미터 정보
		Map<String, Object> map = new HashMap<String, Object>();

		//실시간 검색어 화면 출력 여부 체크
		boolean isRealTimeKeyword = false;
		//오타 후 추천 검색어 화면 출력 여부 체크
		boolean useSuggestedQuery = true;
		String suggestQuery = "";

		//디버깅 보기 설정
		boolean isDebug = false;

		int TOTALVIEWCOUNT = 3;    //통합검색시 출력건수

		String START_DATE = "1970.01.01";	// 기본 시작일

		// 결과 시작 넘버
		int startCount = parseInt(getCheckReqXSS(request, "startCount", "0"), 0);	//시작 번호
		String query = getCheckReqXSS(request, "query", "");						//검색어
		String collection = getCheckReqXSS(request, "collection", "ALL");			//컬렉션이름
		String rt = getCheckReqXSS(request, "rt", "");								//결과내 재검색 체크필드
		String rt2 = getCheckReqXSS(request, "rt2", "");							//결과내 재검색 체크필드
		String reQuery = getCheckReqXSS(request, "reQuery", "");					//결과내 재검색 체크필드
		String realQuery = getCheckReqXSS(request, "realQuery", "");				//결과내 검색어
		String sort = getCheckReqXSS(request, "sort", "RANK");						//정렬필드
		String range = getCheckReqXSS(request, "range", "A");						//기간관련필드
		String startDate = getCheckReqXSS(request, "startDate", START_DATE);		                //시작날짜
		String endDate = getCheckReqXSS(request, "endDate", getCurrentDate());		            //끝날짜
		String fileExt = getCheckReqXSS(request, "fileExt", "");					//파일형식
		String searchField = getCheckReqXSS(request, "searchField", "ALL");;		//검색필드
		int resultCount = parseInt(getCheckReqXSS(request, "resultCount", "10"), 10);
		String strOperation  = "" ;												//operation 조건 필드
		String exquery = "" ;													//exquery 조건 필드
		String resultJson = "";                                                                             //검색결과 저장필드
		int totalCount = 0;
		int viewResultCount = 10;
		String[] searchFields = null;
		String[] collections = null;

		try {

			if(collection.equals("ALL")) { //통합검색인 경우
				collections = COLLECTIONS;
			} else {                        //개별검색인 경우
				collections = new String[] { collection };
			}

			if (reQuery.equals("1")) {
				realQuery = query + " " + realQuery;
			} else if (!reQuery.equals("2")) {
				realQuery = query;
			}

			if(!fileExt.equals("")){
				String[] fileExtArray = fileExt.split(",");
				for(String file : fileExtArray){
					exquery += "<ATTACHEXT:contains:"+file+">|";
				}
			}

			wnsearch = new WNSearch(isDebug,false, collections, searchFields,2);

			if ( collection.equals("ALL") ||  collection.equals("") ) {
				viewResultCount = TOTALVIEWCOUNT;
			}else{
				viewResultCount = resultCount;
			}

			for (int i = 0; i < collections.length; i++) {

				//출력건수
				wnsearch.setCollectionInfoValue(collections[i], PAGE_INFO, startCount+","+viewResultCount);

				//검색어가 없으면 DATE_RANGE 로 전체 데이터 출력
				if (!query.equals("") ) {
					if(sort.equals("RANK")) {
						wnsearch.setCollectionInfoValue(collections[i], SORT_FIELD, "RANK/DESC,DATE/DESC");
					}else{
						wnsearch.setCollectionInfoValue(collections[i], SORT_FIELD, "DATE/DESC,RANK/DESC");
					}
				} else {
					wnsearch.setCollectionInfoValue(collections[i], DATE_RANGE, START_DATE.replaceAll("[.]","/") + ",2030/12/31,-");
					wnsearch.setCollectionInfoValue(collections[i], SORT_FIELD, "RANK/DESC,DATE/DESC");
				}

				//searchField 값이 있으면 설정, 없으면 기본검색필드
				if (!searchField.equals("") && searchField.indexOf("ALL") == -1 ) {
					wnsearch.setCollectionInfoValue(collections[i], SEARCH_FIELD, searchField);
				}

				//operation 설정
				if (!strOperation.equals("")) {
					wnsearch.setCollectionInfoValue(collections[i], FILTER_OPERATION, strOperation);
				}

				//exquery 설정
				if (!exquery.equals("")) {
					wnsearch.setCollectionInfoValue(collections[i], EXQUERY_FIELD, exquery);
				}

				//기간 설정 , 날짜가 모두 있을때
				if (!startDate.equals("")  && !endDate.equals("") ) {
					wnsearch.setCollectionInfoValue(collections[i], DATE_RANGE, startDate.replaceAll("[.]","/") + "," + endDate.replaceAll("[.]","/") + ",-");
				}
			};

			wnsearch.search(realQuery, isRealTimeKeyword, CONNECTION_CLOSE, useSuggestedQuery);

			resultJson = wnsearch.getResultJson();

			// 디버그 메시지 출력
			String debugMsg = wnsearch.printDebug() != null ? wnsearch.printDebug().trim() : "";
			if ( !debugMsg.trim().equals("")) {
				//modelMap.addAttribute("error", debugMsg);
				//forword = "error";
			}

			if(useSuggestedQuery) {
				suggestQuery = wnsearch.suggestedQuery;
			}

		}
		catch (Exception e) {
//            logger.error(e.getMessage(), e);
		}
		finally {
			//서치서버 종료
			if(wnsearch != null) {
				wnsearch.closeServer();
			}
		}

		return resultJson;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}
}
