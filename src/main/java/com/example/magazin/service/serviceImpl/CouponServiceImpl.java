package com.example.magazin.service.serviceImpl;

import com.example.magazin.dto.coupon.CouponDto;
import com.example.magazin.dto.mappers.CouponMapper;
import com.example.magazin.entity.coupon.Coupon;
import com.example.magazin.exceptions.ResourceNotFoundException;
import com.example.magazin.repository.coupon.CouponRepository;
import com.example.magazin.service.CouponService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class CouponServiceImpl implements CouponService {
    private CouponRepository couponRepository;
    private CouponMapper couponMapper;


    @Override
    public boolean exists(CouponDto couponDto) {
        return false;
    }

    @Override
    public CouponDto getById(Integer id) {
        return null;
    }

    @Override
    public List<CouponDto> getAllById(List<Integer> ids) {
        return null;
    }

    @Override
    public CouponDto save(CouponDto couponDto) {
        return null;
    }

    @Override
    public List<CouponDto> saveAll(List<CouponDto> couponDtos) {
        return null;
    }

    @Override
    public boolean delete(CouponDto couponDto) {
        return false;
    }


    @Override
    public CouponDto getCouponByName(String name) {
        Coupon coupon;
        try {
            coupon = couponRepository.findByName(name)
                    .orElseThrow(() -> new ResourceNotFoundException("Coupon wish name not found"));
        }catch (ResourceNotFoundException e){
            e.printStackTrace();
            return null;
        }

        CouponDto couponDto = couponMapper.toDto(coupon);
        couponDto.setActive(couponDto.getActiveUntil().isAfter(LocalDateTime.now()));
        return couponDto;
    }
}
