package com.rpa.web.service.impl;

import com.rpa.web.dto.AdminUserDTO;
import com.rpa.web.enumeration.ExceptionEnum;
import com.rpa.web.mapper.AdminUserMapper;
import com.rpa.web.pojo.AdminUserPO;
import com.rpa.web.service.LoginService;
import com.rpa.web.utils.ResultVOUtil;
import com.rpa.web.vo.ResultVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

import static com.rpa.web.common.Constant.ADMIN_USER;

/**
 * @author: dangyi
 * @date: Created in 16:49 2019/10/12
 * @version: 1.0.0
 * @description:
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private AdminUserMapper adminUserMapper;

    @Override
    public String login(HttpSession session, Map<String, Object> result,
                        String username, String password, String checkcode) {

        // 先校验验证码，从session中获取服务器端生成并传递给前端的验证码
        String serverCheckcode = (String) session.getAttribute("CHECKCODE_SERVER");

        AdminUserPO po = adminUserMapper.queryUserByUsername(username);

        /*try {
            // 对密码加密，之后再去数据库对比
            password = Md5Util.encodeByMd5(password);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("flag", false);
            result.put("msg", "MD5异常");
            return "forward:login";
        }*/

        // 将用户输入的验证码与服务器生成的进行比对
        if (serverCheckcode == null) {
            result.put("flag", false);
            result.put("msg", "服务器异常，请联系管理员！");
            return "forward:/login";
        }

        else if (!serverCheckcode.equalsIgnoreCase(checkcode)) {
            // 验证码校验失败
            result.put("flag", false);
            result.put("msg", "验证码输入错误，请重新输入！");
            return "forward:/login";
        }

        //判断用户名是否存在
        else if (po == null) {
            result.put("flag", false);
            result.put("msg", "用户名不存在");
            return "forward:/login";
        }

        //判断密码是否正确
        else if (!po.getPassword().equals(password)) {
            result.put("flag", false);
            result.put("msg", "密码错误");
            return "forward:/login";
        }

        else {
            // 登录成功，将登录用户数据写入session
            // 考虑到将来要被其他模块引用，这里将 po 转换为 dto，将 dto 存入session
            AdminUserDTO dto = new AdminUserDTO();
            dto.setaId(po.getaId());
            dto.setUsername(po.getUsername());
            dto.setPassword(po.getPassword());
            dto.setEmail(po.getEmail());
            dto.setIsLock(po.getIsLock());
            dto.setCreateTime(po.getCreateTime());
            dto.setLastTime(po.getLastTime());
            dto.setDr(po.getDr());
            dto.setRoleId(po.getRoleId());
            dto.setName(po.getName());
            dto.setExtra(po.getExtra());
            dto.setPhone(po.getPhone());

            session.setAttribute(ADMIN_USER, dto);

            return "redirect:/main";
        }
    }


    /**
     * 修改密码
     *
     * @param oldPassword
     * @param newPassword
     * @param httpSession
     * @return
     */
    @Override
    public ResultVO updatePassword(HttpSession httpSession, String oldPassword, String newPassword) {

        // 先从session中获取当前用户的a_id
        // 能从session中获取用户的信息，说明当前用户是登录状态
        AdminUserDTO dto = (AdminUserDTO) httpSession.getAttribute(ADMIN_USER);
        int aId = dto.getaId();

        // 对输入的旧密码进行校验，以确保的确是用户本人在进行修改密码操作
        String password = this.adminUserMapper.queryPassword(aId);
        if (oldPassword.equals(password)) {
            int count = this.adminUserMapper.updatePassword(aId, newPassword);
            if (count == 1) {
                return ResultVOUtil.success();
            }
            return ResultVOUtil.error(ExceptionEnum.PASSWORD_UPDATE_ERROR);
        }
        return ResultVOUtil.error(ExceptionEnum.PASSWORD_ERROR);
    }
}
