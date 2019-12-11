package com.rpa.web.service.impl;

import com.github.pagehelper.Page;
import com.rpa.common.utils.RedisKeyUtil;
import com.rpa.web.common.PageHelper;
import com.rpa.web.dto.OtherAppDTO;
import com.rpa.web.mapper.OtherAppMapper;
import com.rpa.web.pojo.OtherAppPO;
import com.rpa.web.service.IOtherAppService;
import com.rpa.web.utils.DTPageInfo;
import com.rpa.web.utils.FileUtil;
import com.rpa.common.vo.ResultVO;
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

    @Resource
    private OtherAppMapper otherAppMapper;
    @Autowired
    private StringRedisTemplate template;

    @Value("${file.iconDir}")
    private String iconDir;
    @Value("${file.appDir}")
    private String appDir;

    @Override
    public DTPageInfo<OtherAppDTO> query(int draw, int pageNum, int pageSize, Map<String, Object> reqData) {
        Page<OtherAppPO> page = PageHelper.startPage(pageNum, pageSize);
        List<OtherAppPO> pos = otherAppMapper.query(reqData);
        return new DTPageInfo<>(draw, page.getTotal(), OtherAppDTO.convert(pos));
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
        otherAppMapper.insert(po);

        this.deleteRedis();
        return ResultVOUtil.success();
    }

    @Override
    public int delete(int oId) {
        int first = otherAppMapper.deleteByPrimaryKey(oId);
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
