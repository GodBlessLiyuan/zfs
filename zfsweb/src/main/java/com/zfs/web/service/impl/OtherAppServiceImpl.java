package com.zfs.web.service.impl;

import com.github.pagehelper.Page;
import com.zfs.common.bo.OtherAppBO;
import com.zfs.common.mapper.OtherAppMapper;
import com.zfs.common.pojo.OtherAppPO;
import com.zfs.common.utils.LogUtil;
import com.zfs.common.utils.RedisKeyUtil;
import com.zfs.web.common.PageHelper;
import com.zfs.web.vo.OtherAppVO;
import com.zfs.web.service.IOtherAppService;
import com.zfs.web.utils.DTPageInfo;
import com.zfs.web.utils.FileUtil;
import com.zfs.common.vo.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author: xiahui
 * @date: Created in 2019/9/29 20:02
 * @description: 其他产品
 * @version: 1.0
 */
@Service
public class OtherAppServiceImpl implements IOtherAppService {
    private final static Logger logger = LoggerFactory.getLogger(OtherAppServiceImpl.class);

    @Resource
    private OtherAppMapper otherAppMapper;
    @Autowired
    private StringRedisTemplate template;

    @Value("${file.iconDir}")
    private String iconDir;
    @Value("${file.appDir}")
    private String appDir;

    @Override
    public DTPageInfo<OtherAppVO> query(int draw, int pageNum, int pageSize, Map<String, Object> reqData) {
        Page<OtherAppBO> page = PageHelper.startPage(pageNum, pageSize);
        List<OtherAppBO> pos = otherAppMapper.query(reqData);
        return new DTPageInfo<>(draw, page.getTotal(), OtherAppVO.convert(pos));
    }

    @Override
    public ResultVO insert(String oName, String extra, MultipartFile iconUrl, byte downloadType, String appUrl, MultipartFile apkUrl, int aId) {
        OtherAppPO po = new OtherAppPO();

        po.setaId(aId);
        po.setoName(oName);
        po.setExtra(extra);
        po.setIconUrl(FileUtil.uploadFile(iconUrl, iconDir, "otherapp"));
        po.setDownloadType(downloadType);
        if (downloadType == 1) {
            po.setAppUrl(FileUtil.uploadFile(apkUrl, appDir, "otherapp"));
        } else {
            po.setAppUrl(appUrl);
        }
        po.setCreateTime(new Date());
        int result = otherAppMapper.insert(po);
        if (result == 0) {
            LogUtil.log(logger, "insert", "插入失败", po);
        }

        this.deleteRedis();
        return new ResultVO(1000);
    }

    @Override
    public int delete(int oId) {
        int first = otherAppMapper.deleteByPrimaryKey(oId);
        if (first == 0) {
            LogUtil.log(logger, "delete", "删除失败", oId);
        }
        this.deleteRedis();
        return first;
    }

    /**
     * 删除对应的Redis
     */
    private void deleteRedis() {
        Set<String> redisKeys = template.keys(RedisKeyUtil.genOtherAppRedisKey("*"));
        if (!CollectionUtils.isEmpty(redisKeys)) {
            template.delete(redisKeys);
        }
    }
}
