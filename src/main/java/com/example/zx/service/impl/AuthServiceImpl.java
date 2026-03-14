package com.example.zx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.zx.domain.RegisterDTO;
import com.example.zx.dto.BillDTO;
import com.example.zx.dto.BillDetailDTO;
import com.example.zx.dto.CategoryDTO;
import com.example.zx.entity.Bill;
import com.example.zx.entity.BillDetail;
import com.example.zx.entity.Category;
import com.example.zx.entity.SysUser;
import com.example.zx.mapper.BillDetailMapper;
import com.example.zx.mapper.SysUserMapper;
import com.example.zx.service.AuthService;
import com.example.zx.util.IpUtil;
import com.example.zx.util.PasswordUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.parser.Entity;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private BillDetailMapper billDetailMapper;

    private Boolean ifEqauls(String password, String confirmPassword) {
        if(StringUtils.equals(password,confirmPassword)){
            return true;
        }
        return false;
    }

//    private Boolean ifExitsUserName(String userName) {
//        LambdaQueryWrapper<SysUser> lambdaQueryWrapper = new LambdaQueryWrapper();
//        lambdaQueryWrapper.eq(SysUser::getUsername,userName);
//        return sysUserMapper.selectOne(lambdaQueryWrapper) != null ? true:false;
//    }

//    private void saveSysUser(SysUser sysUser){
//        int insert = sysUserMapper.insert(sysUser);
//        if(insert <= 0){
//            throw new RuntimeException("用户注册失败");
//        }
//    }

    private SysUser dtoToEntity(RegisterDTO registerDTO){
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(registerDTO,sysUser);
        return sysUser;
    }

//    @Transactional(rollbackFor = Exception.class)
//    @Override
//    public SysUser resignSysUser(RegisterDTO registerDTO, HttpServletRequest request){
//        if(!ifEqauls(registerDTO.getPassword(),registerDTO.getConfirmPassword())){
//            throw new RuntimeException("密码不一致");
//        }
//
//        if(ifExitsUserName(registerDTO.getUsername())){
//            throw new RuntimeException("用户名已被注册");
//        }
//
//        String encrypt = PasswordUtil.encrypt(registerDTO.getPassword());
//
//        SysUser sysUser = dtoToEntity(registerDTO);
//        sysUser.setPassword(encrypt);
//        sysUser.setLastLoginIp(IpUtil.getClientIp(request));
//        sysUser.setLastLoginTime(LocalDateTime.now());
//
//        saveSysUser(sysUser);
//
//        return sysUser;
//    }

    @Override
    public List<Category> queryCategory(CategoryDTO categoryDTO) {
        return sysUserMapper.queryCategory(categoryDTO);
    }

    @Override
    public List<Bill> queryBill(BillDTO billDTO) {
        return sysUserMapper.queryBill(billDTO);
    }

    @Override
    public void saveBill(BillDTO billDTO) {
        sysUserMapper.saveBill(dtoToEntity(billDTO));
    }

    @Override
    public void updateBill(BillDTO billDTO) {
        sysUserMapper.updateById(dtoToEntity(billDTO));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteBill(Integer id) {
        LambdaQueryWrapper<BillDetail> billDetails = new LambdaQueryWrapper<>();
        billDetails.eq(BillDetail::getBillId,id);
        List<BillDetail> billDetails1 = billDetailMapper.selectList(billDetails);

        if(null != billDetails1 && billDetails1.size() > 0){
            throw new RuntimeException("账单下关联明细，不可账单");
        }

        sysUserMapper.deleteById(id);
    }

    @Override
    public List<BillDetail> queryBillDetail(String billId) {
        return sysUserMapper.queryBillDetail(billId);
    }


    @Transactional
    @Override
    public void saveBillDetail(BillDetailDTO billDetailDTO) {
        billDetailMapper.insert(dtoToEntity(billDetailDTO));

        Bill bill = sysUserMapper.selectById(billDetailDTO.getBillId());
        if(null == bill){
            throw new RuntimeException("账单不存在");
        }

        bill.setTotalAmount(reduceAmount(bill.getId()));
        sysUserMapper.updateById(bill);
    }

    @Override
    public void updateBillDetail(BillDetailDTO billDetailDTO) {
        BillDetail billDetail = new BillDetail();
        BeanUtils.copyProperties(billDetailDTO,billDetail);
        billDetailMapper.updateById(billDetail);
    }

    @Override
    public void deleteBillDetail(Integer id) {
        billDetailMapper.deleteById(id);
    }

    private BigDecimal reduceAmount(Long id){
        LambdaQueryWrapper<BillDetail> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BillDetail::getBillId, id);
        List<BillDetail> billDetails = billDetailMapper.selectList(queryWrapper);
        BigDecimal total = billDetails.stream()
                .map(BillDetail::getAmount)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return total;
    }


    private Bill dtoToEntity(BillDTO billDTO){
        Bill bill = new Bill();
        BeanUtils.copyProperties(billDTO,bill);
        if(bill.getBillDate() == null){
            bill.setBillDate(LocalDate.now());
        }
        bill.setCreateBy("徐克彪");
        return bill;
    }

    private BillDetail dtoToEntity(BillDetailDTO billDetailDTO){
        BillDetail billDetail = new BillDetail();
        BeanUtils.copyProperties(billDetailDTO,billDetail);
        billDetail.setCreateBy("徐克彪");
        return billDetail;
    }

    @Override
    public BigDecimal statMonthAmount(String monthStr) {
        // 1. 解析月份 (假设入参是 "2023-10")
        YearMonth yearMonth = YearMonth.parse(monthStr, DateTimeFormatter.ofPattern("yyyy-MM"));

        // 2. 获取【当月1号 00:00:00】作为开始时间
        LocalDateTime startTime = yearMonth.atDay(1).atStartOfDay();

        // 3. 获取【下个月1号 00:00:00】作为结束时间 (左闭右开策略)
        LocalDateTime endTime = yearMonth.plusMonths(1).atDay(1).atStartOfDay();

        // 4. 调用 Mapper (传入 startTime 和 endTime)
        BigDecimal total = billDetailMapper.sumAmountByRange(startTime, endTime);

        return total;
    }

    @Override
    public BigDecimal statTodayAmount() {
        ZoneId zone = ZoneId.of("Asia/Shanghai"); // 按你的业务时区
        LocalDate today = LocalDate.now(zone);

        // 2. 开始时间：今天 00:00:00
        LocalDateTime startTime = today.atStartOfDay();

        // 3. 结束时间：明天 00:00:00 (核心逻辑：加1天，取0点)
        LocalDateTime endTime = today.plusDays(1).atStartOfDay();

        BigDecimal total = billDetailMapper.sumAmountByRange(startTime, endTime);

        return total;
    }
}
