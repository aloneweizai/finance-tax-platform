package com.abc12366.bangbang.service.impl;

import com.abc12366.bangbang.mapper.db1.CurriculumBrowserWatchMapper;
import com.abc12366.bangbang.mapper.db2.CurriculumBrowserWatchRoMapper;
import com.abc12366.bangbang.model.curriculum.CurriculumBrowserWatch;
import com.abc12366.bangbang.model.curriculum.bo.CurriculumListBo;
import com.abc12366.bangbang.service.CurriculumBrowserWatchService;
import com.abc12366.gateway.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author liuQi
 * @Date 2018/1/30 16:42
 */
@Service
public class CurriculumBrowserWatchServiceImpl implements CurriculumBrowserWatchService {

    @Autowired
    private CurriculumBrowserWatchMapper curriculumBrowserWatchMapper;

    @Autowired
    private CurriculumBrowserWatchRoMapper curriculumBrowserWatchRoMapper;

    @Override
    public List<CurriculumListBo> selectList(Map<String, Object> map) {
        return curriculumBrowserWatchRoMapper.selectList(map);
    }

    @Override
    public List<CurriculumListBo> selectMonthList(Map<String, Object> map) {
        return curriculumBrowserWatchRoMapper.selectMonthList(map);
    }

    @Override
    public CurriculumBrowserWatch selectTodayNum() {
        return curriculumBrowserWatchRoMapper.selectTodayNum();
    }

    @Override
    public CurriculumBrowserWatch selectCurrentMonthNum() {
        return curriculumBrowserWatchRoMapper.selectCurrentMonthNum();
    }

    @Override
    public void updateBrowserNum(String curriculumId) {
        int cnt = curriculumBrowserWatchRoMapper.selectCntToday(curriculumId);
        if(cnt>0){
            curriculumBrowserWatchMapper.updateBrowseNum(curriculumId);
        }else{
            CurriculumBrowserWatch record = new CurriculumBrowserWatch();
            record.setId(Utils.uuid());
            record.setBrowseNum(1);
            record.setWatchNum(0);
            record.setCurriculumId(curriculumId);
            try{
                curriculumBrowserWatchMapper.insert(record);
            }catch (Exception e){
                curriculumBrowserWatchMapper.updateBrowseNum(curriculumId);
            }
        }
    }

    @Override
    public void updateWatchNum(String curriculumId) {
        int cnt = curriculumBrowserWatchRoMapper.selectCntToday(curriculumId);
        if(cnt>0){
            curriculumBrowserWatchMapper.updateWatchNum(curriculumId);
        }else{
            CurriculumBrowserWatch record = new CurriculumBrowserWatch();
            record.setId(Utils.uuid());
            record.setBrowseNum(0);
            record.setWatchNum(1);
            record.setCurriculumId(curriculumId);
            try{
                curriculumBrowserWatchMapper.insert(record);
            }catch (Exception e){
                curriculumBrowserWatchMapper.updateWatchNum(curriculumId);
            }
        }
    }

}
