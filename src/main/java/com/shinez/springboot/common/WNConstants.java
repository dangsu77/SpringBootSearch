package com.shinez.springboot.common;

/**
 * 
 * <pre>
 * <p> Title: WNCollection </p>
 * <p> Description: 검색환경 설정 상수</p>
 * </pre>
 *
 * @author WISEnut
 * @created: 2014. 5. 27.
 * @modified:
 * @version
 *
 */
public class WNConstants {
	public final static int CONNECTION_TIMEOUT = 20000;
	public final static String CHARSET = "UTF-8";
	public final static int REALTIME_COUNT=100;
	public final static int PAGE_SCALE = 10; //view page list count

	public final static int CONNECTION_KEEP = 0; //recevive mode
	public final static int CONNECTION_REUSE = 2;
	public final static int CONNECTION_CLOSE = 3;

	public final static int ASC = 0; //order
	public final static int DESC = 1; //order

	public final static int USE_KMA_OFFOFF = 0; //synonym, morpheme
	public final static int USE_KMA_ONON = 1;
	public final static int USE_KMA_ONOFF = 2;

	public final static int USE_RESULT_STRING = 0; //result data type	
	public final static int USE_RESULT_XML = 1;
	public final static int USE_RESULT_JSON = 2;
	public final static int USE_RESULT_DUPLICATE_STRING = 3; //uid result data type	
	public final static int USE_RESULT_DUPLICATE_XML = 4;
	public final static int USE_RESULT_DUPLICATE_JSON = 5;

	public final static int IS_CASE_ON = 1; //case on, off
	public final static int IS_CASE_OFF = 0;

	public final static int HI_SUM_OFFOFF = 0; //summarizing, highlighting
	public final static int HI_SUM_OFFON = 1;
	public final static int HI_SUM_ONOFF = 2;
	public final static int HI_SUM_ONON = 3;

	public final static int COMMON_OR_WHEN_NORESULT_OFF = 0;
	public final static int COMMON_OR_WHEN_NORESULT_ON = 1;

	public final static int INDEX_NAME = 0;
	public final static int COLLECTION_NAME = 1;
	public final static int PAGE_INFO = 2;
	public final static int ANALYZER = 3;
	public final static int SORT_FIELD = 4;
	public final static int RANKING_OPTION = 5;
	public final static int SEARCH_FIELD = 6;
	public final static int RESULT_FIELD = 7;
	public final static int DATE_RANGE = 8;
	public final static int RANK_RANGE = 9;
	public final static int EXQUERY_FIELD = 10;
	public final static int COLLECTION_QUERY =11;
	public final static int BOOST_QUERY =12;
	public final static int FILTER_OPERATION = 13;
	public final static int GROUP_BY = 14;
	public final static int GROUP_SORT_FIELD = 15;
	public final static int CATEGORY_BOOST = 16;
	public final static int CATEGORY_GROUPBY = 17;
	public final static int CATEGORY_QUERY = 18;
	public final static int PROPERTY_GROUP = 19;
	public final static int PREFIX_FIELD = 20;
	public final static int FAST_ACCESS = 21;
	public final static int MULTI_GROUP_BY = 22;
	public final static int AUTH_QUERY = 23;
	public final static int DEDUP_SORT_FIELD = 24;
	public final static int COLLECTION_KOR = 25;	
	public final static int HIGHLIGHT_FIELD = 26;	

	public final static int MERGE_COLLECTION_NAME = 0;
	public final static int MERGE_MAPPING_COLLECTION_NAME = 1;
	public final static int MERGE_PAGE_INFO = 2;
	public final static int MERGE_RESULT_FIELD = 3;
	public final static int MERGE_MAPPING_RESULT_FIELD = 4;
	public final static int MERGE_MULTI_GROUP_BY_FIELD = 5;
	public final static int MERGE_MAPPING_MULTI_GROUP_BY_FIELD = 6;
	public final static int MERGE_CATEGORY_GROUPBY_FIELD = 7;
	public final static int MERGE_MAPPING_CATEGORY_GROUPBY_FIELD = 8;

	//가상 통합 컬렉션을 사용하지 않을 경우 아래와 같이MERGE_COLLECTIONS에 정의한다.
	static String[] MERGE_COLLECTIONS = new String[]{""};

	//가상 통합 컬렉션을 사용할 경우 아래와 같이MERGE_COLLECTIONS에 정의한다.
	//public String[] MERGE_COLLECTIONS = new String[]{"merge_sample_bbs"}; 
	/*
public class WNCollection {

	public String[][] MERGE_COLLECTION_INFO = null;

	WNCollection(){

		//가상 통합 컬렉션을 사용할 경우, mapping되는 collection들의 정보를 정의한다.
		MERGE_COLLECTION_INFO = new String[][]
		{
			{
				"merge_sample_bbs", // set merge collection name
				"sample_bbs/sample_edu", // set collection name, delimiter: /
				"0,3",  // set merge collection pageinfo (start,count)
				"DOCID,TITLE,WRITER,CONTENT",// set merge document field
				"DOCID,TITLE,WRITER,CONTENT/DOCID,TITLE,WRITER,CONTENT", // set document field, delimiter: /
				"", // set merge collection multi-group-by field
				"", // set merge collection multi-group-by field, delimiter: /
				"", // set merge collection category-group-by field
				""  // set collection multi-group-by field, delimiter: /
			}					
		};
	 */

	public final static String SEARCH_IP="127.0.0.1";
	public final static int SEARCH_PORT=7000;
	public final static String MANAGER_IP="127.0.0.1";
	public final static int MANAGER_PORT=7800;

	public final static String[] COLLECTIONS = new String[]{"kosf_notice","kosf_idea","kosf_homepage","kosf_buz"};
	public final static String[] COLLECTIONS_NAME = new String[]{"kosf_notice","kosf_idea","kosf_homepage","kosf_buz"};

	public class WNCollection {
		public String[][] MERGE_COLLECTION_INFO = null;
		public String[][] COLLECTION_INFO = null;

		public WNCollection(){
			COLLECTION_INFO = new String[][]
					{
					{
						"kosf_notice", // set index name
						"kosf_notice", // set collection name
						"0,3",  // set pageinfo (start,count)
						"1,0,0,0,0", // set query analyzer (useKMA,isCase,useOriginal,useSynonym, duplcated detection)
						"RANK/DESC,DATE/DESC",  // set sort field (field,order) multi sort '/'
						"basic,prol,0",  // set sort field (field,order) multi sort '/'
						"TITLE/100,CONTENT/40,ATTACHCON/20,ATTACHNAME",// set search field
						"DOCID,Date,TITLE,CONTENT/200,ATTACHNAME,ATTACHCON,ATTACHEXT,PATH,JOIN_MST_CD,JOIN_MST_NM,BUZ_FROM_DD,BUZ_TO_DD,JOIN_FROM_DD,JOIN_TO_DD,LINK_URL,ALIAS",// set document field
						"", // set date range
						"", // set rank range
						"<ALIAS:contains:notice>", // set prefix query, example: <fieldname:contains:value1>|<fieldname:contains:value2>/1,  (fieldname:contains:value) and ' ', or '|', not '!' / operator (AND:1, OR:0)
						"", // set collection query (<fieldname:contains:value^weight | value^weight>/option...) and ' ', or '|'
						"", // set boost query (<fieldname:contains:value> | <field3:contains:value>...) and ' ', or '|'
						"", // set filter operation (<fieldname:operator:value>)
						"", // set groupby field(field, count)
						"", // set sort field group(field/order,field/order,...)
						"", // set categoryBoost(fieldname,matchType,boostID,boostKeyword)
						"", // set categoryGroupBy (fieldname:value)
						"", // set categoryQuery (fieldname:value)
						"", // set property group (fieldname,min,max, groupcount)
						"ALIAS", // use check prefix query filed
						"", // set use check fast access field
						"", // set multigroupby field
						"", // set auth query (Auth Target Field, Auth Collection, Auth Reference Field, Authority Query)
						"", // set Duplicate Detection Criterion Field, RANK/DESC,DATE/DESC
						"notice", // collection display name
						"TITLE,CONTENT" //highlight field
					}
					,
					{
						"kosf_idea", // set index name
						"kosf_idea", // set collection name
						"0,3",  // set pageinfo (start,count)
						"1,0,0,0,0", // set query analyzer (useKMA,isCase,useOriginal,useSynonym, duplcated detection)
						"RANK/DESC,DATE/DESC",  // set sort field (field,order) multi sort '/'
						"basic,prol,0",  // set sort field (field,order) multi sort '/'
						"TITLE/100,CONTENT/40,ATTACHCON/20,ATTACHNAME",// set search field
						"DOCID,Date,TITLE,CONTENT/200,ATTACHNAME,ATTACHCON,ATTACHEXT,PATH,BOARD_TYP_NM,IDEA_MST_CD,IDEA_MST_NM,TAG_NMS,CHOICE_NO,GOOD_LVL,LINK_URL,TERMS,ALIAS",// set document field
						"", // set date range
						"", // set rank range
						"<ALIAS:contains:idea>", // set prefix query, example: <fieldname:contains:value1>|<fieldname:contains:value2>/1,  (fieldname:contains:value) and ' ', or '|', not '!' / operator (AND:1, OR:0)
						"", // set collection query (<fieldname:contains:value^weight | value^weight>/option...) and ' ', or '|'
						"", // set boost query (<fieldname:contains:value> | <field3:contains:value>...) and ' ', or '|'
						"", // set filter operation (<fieldname:operator:value>)
						"", // set groupby field(field, count)
						"", // set sort field group(field/order,field/order,...)
						"", // set categoryBoost(fieldname,matchType,boostID,boostKeyword)
						"", // set categoryGroupBy (fieldname:value)
						"", // set categoryQuery (fieldname:value)
						"", // set property group (fieldname,min,max, groupcount)
						"ALIAS", // use check prefix query filed
						"", // set use check fast access field
						"", // set multigroupby field
						"", // set auth query (Auth Target Field, Auth Collection, Auth Reference Field, Authority Query)
						"", // set Duplicate Detection Criterion Field, RANK/DESC,DATE/DESC
						"idea", // collection display name
						"TITLE,CONTENT,ATTACHCON" //highlight field
					}
					,
					{
						"kosf_homepage", // set index name
						"kosf_homepage", // set collection name
						"0,3",  // set pageinfo (start,count)
						"1,0,0,0,0", // set query analyzer (useKMA,isCase,useOriginal,useSynonym, duplcated detection)
						"RANK/DESC,DATE/DESC",  // set sort field (field,order) multi sort '/'
						"basic,prol,0",  // set sort field (field,order) multi sort '/'
						"TITLE/100,CONTENT/40,ATTACHCON/20,ATTACHNAME",// set search field
						"DOCID,Date,TITLE,CONTENT/200,ATTACHNAME,ATTACHCON,ATTACHEXT,PATH,TYP_NM,LINK_URL,ALIAS",// set document field
						"", // set date range
						"", // set rank range
						"<ALIAS:contains:homepage>", // set prefix query, example: <fieldname:contains:value1>|<fieldname:contains:value2>/1,  (fieldname:contains:value) and ' ', or '|', not '!' / operator (AND:1, OR:0)
						"", // set collection query (<fieldname:contains:value^weight | value^weight>/option...) and ' ', or '|'
						"", // set boost query (<fieldname:contains:value> | <field3:contains:value>...) and ' ', or '|'
						"", // set filter operation (<fieldname:operator:value>)
						"", // set groupby field(field, count)
						"", // set sort field group(field/order,field/order,...)
						"", // set categoryBoost(fieldname,matchType,boostID,boostKeyword)
						"", // set categoryGroupBy (fieldname:value)
						"", // set categoryQuery (fieldname:value)
						"", // set property group (fieldname,min,max, groupcount)
						"ALIAS", // use check prefix query filed
						"", // set use check fast access field
						"", // set multigroupby field
						"", // set auth query (Auth Target Field, Auth Collection, Auth Reference Field, Authority Query)
						"", // set Duplicate Detection Criterion Field, RANK/DESC,DATE/DESC
						"homepage", // collection display name
						"" //highlight field
					}
					,
					{
						"kosf_buz", // set index name
						"kosf_buz", // set collection name
						"0,3",  // set pageinfo (start,count)
						"1,0,0,0,0", // set query analyzer (useKMA,isCase,useOriginal,useSynonym, duplcated detection)
						"RANK/DESC,DATE/DESC",  // set sort field (field,order) multi sort '/'
						"basic,prol,0",  // set sort field (field,order) multi sort '/'
						"TITLE/100,CONTENT/40,ATTACHCON/20,ATTACHNAME",// set search field
						"DOCID,Date,TITLE,CONTENT/200,ATTACHNAME,ATTACHCON,ATTACHEXT,PATH,LINK_URL,DCOMP_LICEN_NO,DCOMP_TP_CD,ALIAS",// set document field
						"", // set date range
						"", // set rank range
						"<ALIAS:contains:buz>", // set prefix query, example: <fieldname:contains:value1>|<fieldname:contains:value2>/1,  (fieldname:contains:value) and ' ', or '|', not '!' / operator (AND:1, OR:0)
						"", // set collection query (<fieldname:contains:value^weight | value^weight>/option...) and ' ', or '|'
						"", // set boost query (<fieldname:contains:value> | <field3:contains:value>...) and ' ', or '|'
						"", // set filter operation (<fieldname:operator:value>)
						"", // set groupby field(field, count)
						"", // set sort field group(field/order,field/order,...)
						"", // set categoryBoost(fieldname,matchType,boostID,boostKeyword)
						"", // set categoryGroupBy (fieldname:value)
						"", // set categoryQuery (fieldname:value)
						"", // set property group (fieldname,min,max, groupcount)
						"ALIAS", // use check prefix query filed
						"", // set use check fast access field
						"", // set multigroupby field
						"", // set auth query (Auth Target Field, Auth Collection, Auth Reference Field, Authority Query)
						"", // set Duplicate Detection Criterion Field, RANK/DESC,DATE/DESC
						"buz", // collection display name
						"" //highlight field
					}

					};
		}
	}
}
