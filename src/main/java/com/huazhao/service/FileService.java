package com.huazhao.service;

import com.huazhao.dao.DeleteDAO;
import com.huazhao.dao.QueryDAO;
import com.huazhao.dao.SaveDAO;
import com.huazhao.model.FileMeta;
import com.huazhao.util.ListUtil;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created with Intellij IDEA
 * Description:
 * User : 花朝
 * Date : 2021-02-24
 * Time : 18:52
 */
public class FileService {
    private final QueryDAO queryDAO = new QueryDAO();
    private final DeleteDAO deleteDAO = new DeleteDAO();
    private final SaveDAO saveDAO = new SaveDAO();

    public void deleteFileList(List<FileMeta> fileMetaList){
        List<Integer> idList = fileMetaList.stream().map(FileMeta ::getId).collect(Collectors.toList());
        deleteDAO.delete(idList);
    }
    public void saveFileList(List<FileMeta> fileMetaList){
        saveDAO.save(fileMetaList);
    }

    public void service(String path,List<FileMeta> scanFileList){
        List<FileMeta> queryFileList = queryDAO.queryByPath(path);

        List<FileMeta> ds = ListUtil.differenceSet(queryFileList,scanFileList);
        deleteFileList(ds);

        List<FileMeta> ss = ListUtil.differenceSet(scanFileList,queryFileList);
        saveFileList(ss);
    }
    public List<FileMeta> queryByName(String keyword){
        return queryDAO.query(keyword);
    }
}

