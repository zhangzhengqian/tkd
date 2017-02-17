package com.lc.zy.common.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.CollectionUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.SortClause;
import org.apache.solr.client.solrj.beans.DocumentObjectBinder;
import org.apache.solr.client.solrj.impl.BinaryRequestWriter;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocumentList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 全文检索 solrj 的封装
 * 
 * @author liangc
 */
public class Searcher {

    private static final Logger logger = LoggerFactory.getLogger(Searcher.class);

    /**
     * 插入或更新索引
     * 
     * @param beans
     * @throws Exception
     */
    public static String setDocs(SearchBean... beans) throws Exception {
        List<SearchBean> docs = new ArrayList<SearchBean>();
        String restUrl = null;
        for (SearchBean bean : beans) {
            docs.add(bean);
            if (restUrl == null) {
                restUrl = bean.getRestUrl();
            }
        }
        int count = docs.size(), pageNo = 0, pageSize = 30;
        if (count > pageSize) {
            int totalPage = count / pageSize;
            if (count % pageSize != 0)
                totalPage += 1;
            StringBuffer sb = new StringBuffer();
            for (; pageNo < totalPage; pageNo++) {
                int idx = pageNo * pageSize;
                List<SearchBean> sbl = null;
                try {
                    sbl = docs.subList(idx, idx + pageSize);
                } catch (IndexOutOfBoundsException ie) {
                    sbl = docs.subList(idx, count);
                }
                HttpSolrServer server = new HttpSolrServer(restUrl);
                server.setRequestWriter(new BinaryRequestWriter());
                UpdateResponse ur = server.addBeans(sbl.iterator());
                String rtn = ur.toString();
                ur = server.commit();
                logger.debug("ur={}", ur.toString());
                sb.append(rtn).append("__");
            }
            return sb.toString();
        } else {
            HttpSolrServer server = new HttpSolrServer(restUrl);
            server.setRequestWriter(new BinaryRequestWriter());
            UpdateResponse ur = server.addBeans(docs.iterator());
            String rtn = ur.toString();
            ur = server.commit();
            logger.debug("ur={}", ur.toString());
            return rtn;
        }
    }

    /**
     * 删除索引
     * 
     * @param restUrl rest服务地址
     * @param id 要删除的id集合
     * @throws Exception
     */
    public static void delDocs(String restUrl, String... id) throws Exception {
        HttpSolrServer server = new HttpSolrServer(restUrl);
        List<String> ids = Arrays.asList(id);
        UpdateResponse ur = server.deleteById(ids);
        System.out.println(ur.toString());
        ur = server.commit();
        logger.debug("ur={}", ur.toString());
    }

    /**
     * 删除制定url下全部的索引数据
     * 
     * @param restUrl
     * @throws Exception
     */
    public static String delAll(String restUrl) throws Exception {
        HttpSolrServer server = new HttpSolrServer(restUrl);
        UpdateResponse ur = server.deleteByQuery("*:*");
        String res = ur.toString();
        ur = server.commit();
        return res;
    }

    /**
     * 查询一个结果集
     * 
     * @param clazz 结果集中的对象
     * @param restUrl rest服务地址，可以在对应的 searchBean 实例中获取
     * @param pageNo 页号
     * @param pageSize 页大小
     * @param q 主要查询条件，格式为 "search_key:search_value"；例如要查询name＝value则需要传递
     *            "name:value"
     * @param filterQueries 在 q 的结果集中按照给定条件过滤，可以传多个过滤条件，格式与 q 相同
     * @param fields 结果集中包含那些字段，应当与对应的 searchBean 实例的字段名相同
     * @param sortClause 排序条件
     * @param params 除 q 和 fq 以外的参数，例如按坐标查询时，需要传递参数 d
     * @return
     * @throws Exception
     */
    public static <T> PageBean<T> query(Class<T> clazz, String restUrl, int pageNo, int pageSize, String q,
            String[] filterQueries, String[] fields, Map<String, String> params, SortClause... sortClause)
            throws Exception {
        if (q == null) {
            q = "*:*";
        }
        new QueryLog(clazz.getSimpleName(), q, filterQueries, params).writeLog();

        HttpSolrServer server = new HttpSolrServer(restUrl);
        SolrQuery query = new SolrQuery();
        query.setQuery(q);
        query.setStart(pageNo);
        query.setRows(pageSize);
        if (filterQueries != null && filterQueries.length > 0)
            query.setFilterQueries(filterQueries);
        if (fields != null && fields.length > 0)
            query.setFields(fields);

        if (sortClause != null)
            for (SortClause s : sortClause) {
                query.addSort(s);
            }

        if (!params.isEmpty()) {
            for (Entry<String, String> entry : params.entrySet()) {
                query.setParam(entry.getKey(), entry.getValue());
            }
        }
        QueryResponse response = server.query(query);

        SolrDocumentList list = response.getResults();
        DocumentObjectBinder binder = new DocumentObjectBinder();
        List<T> beanList = binder.getBeans(clazz, list);
        PageBean<T> page = new PageBean<T>();
        page.setData(beanList);
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setTotalCount(list.getNumFound());
        return page;
    }

    /**
     * 查询一个结果集
     * 
     * @param clazz 结果集中的对象
     * @param restUrl rest服务地址，可以在对应的 searchBean 实例中获取
     * @param pageNo 页号
     * @param pageSize 页大小
     * @param q 主要查询条件，格式为 "search_key:search_value"；例如要查询name＝value则需要传递
     *            "name:value"
     * @param filterQueries 在 q 的结果集中按照给定条件过滤，可以传多个过滤条件，格式与 q 相同
     * @param fields 结果集中包含那些字段，应当与对应的 searchBean 实例的字段名相同
     * @param sortClause 排序条件
     * @param params 除 q 和 fq 以外的参数，例如按坐标查询时，需要传递参数 d
     * @return
     * @throws Exception
     */
    public static <T> PageBean<T> query(Class<T> clazz, String restUrl, int pageNo, int pageSize, String q,
            String[] filterQueries, String[] fields, Map<String, String> params, List<SortClause> sortList)
            throws Exception {
        if (q == null) {
            q = "*:*";
        }
        new QueryLog(clazz.getSimpleName(), q, filterQueries, params,sortList).writeLog();

        HttpSolrServer server = new HttpSolrServer(restUrl);
        SolrQuery query = new SolrQuery();
        query.setQuery(q);
        query.setStart(pageNo);
        query.setRows(pageSize);
        if (filterQueries != null && filterQueries.length > 0)
            query.setFilterQueries(filterQueries);
        if (fields != null && fields.length > 0)
            query.setFields(fields);

        if (CollectionUtils.isNotEmpty(sortList))
            for (SortClause s : sortList) {
                query.addSort(s);
            }

        if (params!=null && !params.isEmpty()) {
            for (Entry<String, String> entry : params.entrySet()) {
                query.setParam(entry.getKey(), entry.getValue());
            }
        }
        QueryResponse response = server.query(query);

        SolrDocumentList list = response.getResults();
        DocumentObjectBinder binder = new DocumentObjectBinder();
        List<T> beanList = binder.getBeans(clazz, list);
        PageBean<T> page = new PageBean<T>();
        page.setData(beanList);
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setTotalCount(list.getNumFound());
        return page;
    }

    public static void main(String[] args) throws Exception {
        // ContractTmplBean po = new ContractTmplBean();
        // po.setId("123456");
        // po.setName("你好世界");
        // po.setTypeId("0");
        // po.setAttachmentId("123456");
        // po.setAttachmentName("hello.docx");
        // po.setContent("中华人民共和国成立了");
        // po.setViewTimes(222);
        // po.setDownloadTimes(111);
        // setDocs(po);
        // PageBean<ContractTmplBean> page = query(
        // ContractTmplBean.class,
        // po.getRestUrl(),
        // 0,10,
        // "*:*",null,
        // new String[]{"id","typeId","name","attachmentId","attachmentName"},
        // new SortClause("id",ORDER.asc));
        // System.out.println(page);
        List<String> l = new ArrayList<String>();
        l.add("1");
        l.add("2");
        l.add("3");
        l.add("4");
        l.add("5");
        l.add("6");
        int idx = 1 * 3;
        System.out.println(l.subList(idx, l.size()));
    }

}
