package com.example.zx.controller;

import com.example.zx.domain.ApiResponse;
import com.example.zx.domain.RegisterDTO;
import com.example.zx.dto.BillDTO;
import com.example.zx.dto.BillDetailDTO;
import com.example.zx.dto.CategoryDTO;
import com.example.zx.entity.Bill;
import com.example.zx.entity.BillDetail;
import com.example.zx.entity.Category;
import com.example.zx.entity.SysUser;
import com.example.zx.service.AuthService;
import com.example.zx.vo.BillDetailVO;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AuthService authService;

//    @RequestMapping("/sysUser/resign")
//    public ResponseEntity<ApiResponse<SysUser>> resiger(@RequestBody @Validated RegisterDTO registerDTO, HttpServletRequest request){
//        try {
//            SysUser sysUser = authService.resignSysUser(registerDTO, request);
//            return ResponseEntity.ok(ApiResponse.success(sysUser));
//        }catch (Exception e){
//            log.error("用户注册失败",e.getMessage(),e);
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.fail(null));
//        }
//    }

    @RequestMapping("/category/list")
    public ResponseEntity<ApiResponse<List<Category>>> queryCategory(){
        try {
            List<Category> categories = authService.queryCategory(new CategoryDTO());
            return ResponseEntity.ok(ApiResponse.success(categories));
        }catch (Exception e){
            log.error("用户注册失败",e.getMessage(),e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.fail(null));
        }
    }

    @RequestMapping("/bill/list")
    public ResponseEntity<ApiResponse<List<Bill>>> queryBill(@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate, @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate){
        try {
            List<Bill> bills = authService.queryBill(new BillDTO());
            return ResponseEntity.ok(ApiResponse.success(bills));
        }catch (Exception e){
            log.error("用户注册失败",e.getMessage(),e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.fail(null));
        }
    }

    @RequestMapping("/bill/summary")
    public ResponseEntity<ApiResponse<Total>> queryTotal(){
        try {
            Total total = new Total();
            String yearMonth = YearMonth.now(ZoneId.of("Asia/Shanghai")).toString();
            total.setTodayTotal(authService.statTodayAmount());
            total.setMonthTotal(authService.statMonthAmount(yearMonth));
            return ResponseEntity.ok(ApiResponse.success(total));
        }catch (Exception e){
            log.error("用户注册失败",e.getMessage(),e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.fail(null));
        }
    }


    @Data
    static class Total{

        private BigDecimal todayTotal;

        private BigDecimal monthTotal;
    }

    @RequestMapping("/bill/add")
    public ResponseEntity<ApiResponse<String>> saveBill(@RequestBody BillDTO billDTO){
        try {
            authService.saveBill(billDTO);
            return ResponseEntity.ok(ApiResponse.success("success"));
        }catch (Exception e){
            log.error("用户注册失败",e.getMessage(),e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.fail("fail"));
        }
    }

    @RequestMapping("/bill/update")
    public ResponseEntity<ApiResponse<String>> updateBill(@RequestBody BillDTO billDTO){
        try {
            authService.updateBill(billDTO);
            return ResponseEntity.ok(ApiResponse.success("success"));
        }catch (Exception e){
            log.error("用户注册失败",e.getMessage(),e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.fail("fail"));
        }
    }

    @RequestMapping("/bill/delete/{id}")
    public ResponseEntity<ApiResponse<String>> deleteBill(@PathVariable("id") Integer id){
        try {
            authService.deleteBill(id);
            return ResponseEntity.ok(ApiResponse.success("success"));
        }catch (Exception e){
            log.error("用户注册失败",e.getMessage(),e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.fail("fail"));
        }
    }


    @RequestMapping("/bill-detail/add")
    public ResponseEntity<ApiResponse<String>> saveBillDetail(@RequestBody(required = true) BillDetailDTO billDetailDTO){
        try {
            authService.saveBillDetail(billDetailDTO);
            return ResponseEntity.ok(ApiResponse.success("success"));
        }catch (Exception e){
            log.error("用户注册失败",e.getMessage(),e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.fail(null));
        }
    }

    @RequestMapping("/bill-detail/update")
    public ResponseEntity<ApiResponse<String>> updateBillDetail(@RequestBody(required = true) BillDetailDTO billDetailDTO){
        try {
            authService.updateBillDetail(billDetailDTO);
            return ResponseEntity.ok(ApiResponse.success("success"));
        }catch (Exception e){
            log.error("用户注册失败",e.getMessage(),e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.fail(null));
        }
    }

    @RequestMapping("/bill-detail/list")
    public ResponseEntity<ApiResponse<List<BillDetailVO>>> queryBillDetail(@RequestParam(required = true) String billId){
        try {
            List<BillDetail> billDetails = authService.queryBillDetail(billId);
            List<BillDetailVO> billDetailVos = billDetails.stream().map(this::entityToVo).collect(Collectors.toList());
            return ResponseEntity.ok(ApiResponse.success(billDetailVos));
        }catch (Exception e){
            log.error("用户注册失败",e.getMessage(),e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.fail(null));
        }
    }

    @RequestMapping("/bill-detail/delete/{id}")
    public ResponseEntity<ApiResponse<String>> queryBillDetail(@PathVariable("id") Integer id){
        try {
            authService.deleteBillDetail(id);
            return ResponseEntity.ok(ApiResponse.success("success"));
        }catch (Exception e){
            log.error("用户注册失败",e.getMessage(),e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.fail(null));
        }
    }

    private BillDetailVO entityToVo(BillDetail billDetail){
        BillDetailVO billDetailVO = new BillDetailVO();
        BeanUtils.copyProperties(billDetail,billDetailVO);

        if(null != billDetail.getPaymentMethod()){
            String paymentMethodText = paymentMethodTrans(billDetail.getPaymentMethod());
            billDetailVO.setPaymentMethodText(paymentMethodText);
        }

        if(null != billDetail.getChannel()){
            String channelText = channelTrans(billDetail.getChannel());
            billDetailVO.setChannelText(channelText);
        }

        if(null != billDetail.getConsumeType()){
            String consumeType = consumeTypeTrans(billDetail.getConsumeType());
            billDetailVO.setConsumeTypeText(consumeType);
        }

        return billDetailVO;
    }

    private String paymentMethodTrans(Integer PaymentMethod){
        switch (PaymentMethod) {
            case 1:
                return "微信支付";
            case 2:
                return "支付宝";
            case 3:
                return "现金";
            case 4:
                return "银行卡";
            case 5:
                return "云闪付";
            case 6:
                return "其他";
            default:
                throw new RuntimeException("支付方式异常");
        }
    }

    private String channelTrans(Integer chanel){
        switch (chanel) {
            case 1:
                return "线上";
            case 2:
                return "线下";
            default:
                throw new RuntimeException("支付方式异常");
        }
    }

    private String consumeTypeTrans(Integer consumeType){
        switch (consumeType) {
            case 1:
                return "理性";
            case 2:
                return "非理性";
            case 3:
                return "正常";
            case 4:
                return "异常";
            case 5:
                return "冲动";
            default:
                throw new RuntimeException("消费异常");
        }
    }
}
