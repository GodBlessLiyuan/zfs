package com.rpa.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.rpa.common.mapper.BatchInfoMapper;
import com.rpa.common.mapper.UserMapper;
import com.rpa.common.mapper.UserVipMapper;
import com.rpa.common.mapper.ViptypeMapper;
import com.rpa.common.mapper.ChBatchMapper;
import com.rpa.common.mapper.ActiveZnzsMapper;
import com.rpa.common.pojo.BatchInfoPO;
import com.rpa.common.pojo.UserVipPO;
import com.rpa.common.pojo.UserPO;
import com.rpa.common.pojo.ViptypePO;
import com.rpa.common.pojo.ActiveZnzsPO;
import com.rpa.common.utils.LogUtil;
import com.rpa.common.vo.ResultVO;
import com.rpa.server.constant.BatchInfoConstant;
import com.rpa.server.dto.BatchInfoDTO;
import com.rpa.server.dto.BatchSycInfoDTO;
import com.rpa.server.service.IBatchInfoService;
import com.rpa.server.utils.UserVipUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Date;


/**
 * @author: xiahui
 * @date: Created in 2019/10/29 9:28
 * @description: 卡密表
 * @version: 1.0
 */
@EnableTransactionManagement
@Service
public class BatchInfoServiceImpl implements IBatchInfoService {
    private final static Logger logger = LoggerFactory.getLogger(BatchInfoServiceImpl.class);

    @Resource
    private BatchInfoMapper batchInfoMapper;
    @Resource
    private UserVipMapper userVipMapper;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ViptypeMapper vipTypeMapper;
    @Autowired
    private ChBatchMapper chBatchMapper;
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;
    @Autowired
    private ActiveZnzsMapper znzsMapper;
    @Value("${ZnzjUrl.keySycActivate}")
    private String keySycActivateUrl;
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultVO activate(BatchInfoDTO dto) {
        BatchInfoPO po = batchInfoMapper.queryByKey(dto.getKey());
        if (null == po) {
            return new ResultVO(1016);
        }

        if (BatchInfoConstant.FROZEN == po.getStatus()) {
            return new ResultVO(1017);
        } else if (BatchInfoConstant.ACTIVATED == po.getStatus()) {
            return new ResultVO(1020);
        } else if (BatchInfoConstant.EXPIRED == po.getStatus()) {
            return new ResultVO(1021);
        }

        po.setUserId(dto.getUd());
        po.setStatus((byte) 2);
        po.setUpdateTime(new Date());
        int result1 = batchInfoMapper.updateByPrimaryKey(po);
        if (result1 == 0) {
            LogUtil.log(logger, "activate", "更新失败", po);
        }

        // 更新用户会员数据
        UserVipPO userVipPO = userVipMapper.queryByUserId(dto.getUd());
        UserVipPO newUserVipPO = UserVipUtil.buildUserVipVO(userVipPO, dto.getUd(), po.getDays(), false);
        int result2;
        if (userVipPO == null) {
            result2 = userVipMapper.insert(newUserVipPO);
        } else {
            result2 = userVipMapper.updateByPrimaryKey(newUserVipPO);
        }
        if (result2 == 0) {
            LogUtil.log(logger, "activate", "更新用户会员数据失败", newUserVipPO);
        }
        return new ResultVO(1000);
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultVO keyactivate2(BatchInfoDTO dto) {
        BatchInfoPO po = batchInfoMapper.queryByKey(dto.getKey());
        if (null == po) {
            return new ResultVO(1016);
        }

        if (BatchInfoConstant.FROZEN == po.getStatus()) {
            return new ResultVO(1017);
        } else if (BatchInfoConstant.ACTIVATED == po.getStatus()) {
            return new ResultVO(1020);
        } else if (BatchInfoConstant.EXPIRED == po.getStatus()) {
            return new ResultVO(1021);
        }

        po.setUserId(dto.getUd());
        po.setStatus((byte) 2);
        po.setUpdateTime(new Date());
        int result1 = batchInfoMapper.updateByPrimaryKey(po);
        if (result1 == 0) {
            LogUtil.log(logger, "activate", "更新失败", po);
            return new ResultVO(2000);
        }

        // 更新用户会员数据
        UserVipPO userVipPO = userVipMapper.queryByUserId(dto.getUd());
        UserVipPO newUserVipPO = UserVipUtil.buildUserVipVO(userVipPO, dto.getUd(), po.getDays(), false);
        int result2;
        if (userVipPO == null) {
            result2 = userVipMapper.insert(newUserVipPO);
        } else {
            result2 = userVipMapper.updateByPrimaryKey(newUserVipPO);
        }
        if (result2 == 0) {
            LogUtil.log(logger, "activate", "更新用户会员数据失败", newUserVipPO);
            return new ResultVO(2000);
        }
        Byte activeSyc=chBatchMapper.queryActiveByPri(po.getBatchId());
        if(activeSyc==1){
            return new ResultVO(1000);
        }
        else if(activeSyc==2)
        {
            RestTemplate template=restTemplateBuilder.build();
            UserPO userPO= userMapper.selectByPrimaryKey(userVipPO.getUserId());
            ViptypePO vipTypePO=vipTypeMapper.selectByPrimaryKey(userVipPO.getViptypeId());
            //用于发送到智能助手的对象batchSycInfoDTO
            BatchSycInfoDTO batchSycInfoDTO=new BatchSycInfoDTO();
            batchSycInfoDTO.setUserPO(userPO);
            batchSycInfoDTO.setVipTypePO(vipTypePO);
            batchSycInfoDTO.setDay(po.getDays());
            batchSycInfoDTO.setUd(dto.getUd());

            ActiveZnzsPO znzsPO=new ActiveZnzsPO();
            znzsPO.setVipkey(po.getVipkey());
            znzsPO.setPhone(userPO.getPhone());
            znzsPO.setTime(new Date());
            try{
                Integer resultVO= template.postForObject(keySycActivateUrl,batchSycInfoDTO, Integer.class);

                if(resultVO!=null&&resultVO==1000){
                    znzsPO.setStatus((byte) 1);
                    znzsMapper.insert(znzsPO);
                    return new ResultVO(1000);
                }else{
                    LogUtil.log(logger,keySycActivateUrl, JSON.toJSONString(batchSycInfoDTO),JSON.toJSON(resultVO),"返回状态码不正确");
                    znzsPO.setStatus((byte) 2);
                    znzsMapper.insert(znzsPO);
                    return new ResultVO(2000);
                }

            }catch (Exception e){
                e.printStackTrace();
                LogUtil.log(logger,keySycActivateUrl,JSON.toJSONString(batchSycInfoDTO),"出现异常");
                znzsPO.setStatus((byte) 3);
                znzsMapper.insert(znzsPO);
                return new ResultVO(2000);
            }


        }
        else{
            return new ResultVO(2000);
        }
    }


}
