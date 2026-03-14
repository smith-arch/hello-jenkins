package com.example.zx.service;

import com.example.zx.domain.RegisterDTO;
import com.example.zx.dto.BillDTO;
import com.example.zx.dto.BillDetailDTO;
import com.example.zx.dto.CategoryDTO;
import com.example.zx.entity.Bill;
import com.example.zx.entity.BillDetail;
import com.example.zx.entity.Category;
import com.example.zx.entity.SysUser;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

public interface AuthService {

    /**
     * 用户注册
     */
    //SysUser resignSysUser(RegisterDTO registerDTO, HttpServletRequest request);

    /**
     * 类别查询
     */
    List<Category> queryCategory(CategoryDTO categoryDTO);

    /**
     * 账单查询
     */
    List<Bill> queryBill(BillDTO billDTO);

    /**
     * 账单保存
     */
    void saveBill(BillDTO billDTO);

    /**
     * 账单变更
     */
    void updateBill(BillDTO billDTO);

    /**
     * 账单删除
     */
    void deleteBill(Integer id);

    /**
     * 账单条目明细查询
     */
    List<BillDetail> queryBillDetail(@Param("billId") String billId);

    /**
     * 账单保存
     */
    void saveBillDetail(BillDetailDTO billDetailDTO);
    /**
     * 更新账单
     */
    void updateBillDetail(BillDetailDTO billDetailDTO);

    /**
     * 账单条目删除
     */
    void deleteBillDetail(Integer id);

    /**
     * 按月份统计消费金额
     */
    BigDecimal statMonthAmount(String monthStr);

    /**
     * 按天统计消费金额
     */
    BigDecimal statTodayAmount();


}
