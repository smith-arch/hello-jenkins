package com.example.zx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.zx.dto.BillDTO;
import com.example.zx.dto.CategoryDTO;
import com.example.zx.entity.Bill;
import com.example.zx.entity.BillDetail;
import com.example.zx.entity.Category;
import com.example.zx.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysUserMapper extends BaseMapper<Bill> {

    List<Category> queryCategory(@Param("categoryDTO") CategoryDTO categoryDTO);

    List<Bill> queryBill(@Param("categoryDTO") BillDTO billDTO);

    void saveBill(Bill bill);

    List<BillDetail> queryBillDetail(@Param("billId") String billId);

    void insertMonthStat(@Param("startDate") String startDate,
                         @Param("endDate") String endDate,
                         @Param("createBy") String createBy);

}
