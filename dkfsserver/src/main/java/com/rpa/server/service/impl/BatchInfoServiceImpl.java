package com.rpa.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rpa.common.mapper.BatchInfoMapper;
import com.rpa.common.mapper.UserMapper;
import com.rpa.common.mapper.UserVipMapper;
import com.rpa.common.mapper.ChBatchMapper;
import com.rpa.common.mapper.ActiveZnzsMapper;

import com.rpa.common.pojo.BatchInfoPO;
import com.rpa.common.pojo.UserVipPO;
import com.rpa.common.pojo.UserPO;

import com.rpa.common.pojo.ActiveZnzsPO;
import com.rpa.common.utils.LogUtil;
import com.rpa.common.vo.ResultVO;
import com.rpa.server.constant.BatchInfoConstant;
import com.rpa.server.dto.BatchInfoDTO;
import com.rpa.server.dto.BatchSycInfoDTO;
import com.rpa.server.dto.UserDouDTO;
import com.rpa.server.service.IBatchInfoService;
import com.rpa.server.utils.UserVipUtil;
import com.rpa.server.vo.SycResultVO;
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
    private ChBatchMapper chBatchMapper;
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;
    @Autowired
    private ActiveZnzsMapper znzsMapper;
    @Value("${ZnzjUrl.keySycActivate}")
    private String keySycActivateUrl;
    @Value("${ZnzjUrl.keyactivateZnZj}")
    private String keyActivateUrl;


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
        /**
         * 没有用户，传批次和本用户
         * */
        if (null == po) {
            UserPO userPOF=userMapper.selectByPrimaryKey(dto.getUd());
            if(userPOF==null) return new ResultVO(2000);
            dto.setPhone(userPOF.getPhone());
            UserDouDTO userDouDTO=new UserDouDTO();
            UserDouDTO.convertDTO(userDouDTO,userPOF);
            BatchSycInfoDTO baSyn=new BatchSycInfoDTO();
            baSyn.setPhone(dto.getPhone());
            baSyn.setUserDouDTO(userDouDTO);
            baSyn.setKey(dto.getKey());
            logger.info("activeDKSF","不存在",po);
            RestTemplate template=new RestTemplate();
            //传用户信息，返回被激活的天数
            ResultVO<BatchSycInfoDTO> resultVO = template.postForObject(keyActivateUrl, baSyn, SycResultVO.class);

            ActiveZnzsPO znzsPO=new ActiveZnzsPO();
            znzsPO.setPhone(dto.getPhone());
            znzsPO.setTime(new Date());
            znzsPO.setVipkey(dto.getKey());
            znzsPO.setStatus((byte) 2);//默认返回状态码
            if(resultVO.getStatus()==999){
                BatchSycInfoDTO ba = resultVO.getData();
                znzsPO.setStatus((byte)1);
                resultVO=activeSelfDKFS(ba,dto.getPhone());
            }else if(resultVO.getStatus()==1000){
                znzsPO.setStatus((byte) 1);
            }
            znzsMapper.insert(znzsPO);
            return resultVO;
        }
        /**
         * 激活码都在多开分身的业务流程
         * */
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
            UserPO userPOF=userMapper.selectByPrimaryKey(dto.getUd());
            dto.setPhone(userPOF.getPhone());
            RestTemplate template=restTemplateBuilder.build();
            //发送到智能助手，天数，手机号和用户
            BatchSycInfoDTO batchSycInfoDTO=new BatchSycInfoDTO();
            batchSycInfoDTO.setDay(po.getDays());
            batchSycInfoDTO.setPhone(dto.getPhone());
            UserDouDTO userDouDTO=new UserDouDTO();
            UserDouDTO.convertDTO(userDouDTO,userPOF);
            batchSycInfoDTO.setUserDouDTO(userDouDTO);

            ActiveZnzsPO znzsPO=new ActiveZnzsPO();
            znzsPO.setVipkey(po.getVipkey());
            znzsPO.setPhone(dto.getPhone());
            znzsPO.setTime(new Date());
            try{
                String tmp= template.postForObject(keySycActivateUrl,batchSycInfoDTO, String.class);
                JSONObject jobj=JSON.parseObject(tmp,JSONObject.class);
                ResultVO resultVO=new ResultVO((Integer)jobj.get("status"));
                if(resultVO.getStatus()==1000){
                    znzsPO.setStatus((byte) 1);
                }else{
                    logger.info(keySycActivateUrl, JSON.toJSONString(batchSycInfoDTO),JSON.toJSON(resultVO),"返回状态码不正确");
                    znzsPO.setStatus((byte) 2);
                }
                znzsMapper.insert(znzsPO);
                return resultVO;

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

    private ResultVO activeSelfDKFS(BatchSycInfoDTO dto,String phone) {
        UserPO userPO = userMapper.queryByPhone(phone);
        long useID=userPO.getUserId();
        // 更新用户会员数据
        UserVipPO userVipPO = userVipMapper.queryByUserId(useID);
        UserVipPO newUserVipPO = UserVipUtil.buildUserVipVO(userVipPO, useID, dto.getDay(), false);
        int result2;
        if (userVipPO == null) {
            result2 = userVipMapper.insert(newUserVipPO);
        } else {
            result2 = userVipMapper.updateByPrimaryKey(newUserVipPO);
        }
        if (result2 == 0) {
            LogUtil.log(logger, "activity", "插入或更新失败", dto);
        }
        return new ResultVO(1000);
    }

}
