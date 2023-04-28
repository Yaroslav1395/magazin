package com.example.magazin.service.serviceImpl;

import com.example.magazin.dto.coupon.CouponDto;
import com.example.magazin.dto.mappers.CouponMapper;
import com.example.magazin.dto.user.UserDto;
import com.example.magazin.entity.coupon.Coupon;
import com.example.magazin.exceptions.ResourceNotFoundException;
import com.example.magazin.repository.coupon.CouponRepository;
import com.example.magazin.service.CouponService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.w3c.dom.ls.LSInput;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CouponServiceImpl implements CouponService {
    private CouponRepository couponRepository;
    private CouponMapper couponMapper;

    @Override
    public boolean isCouponExist(Integer id) {
        return couponRepository.existsById(id);
    }

    @Override
    public CouponDto getCouponById(Integer id) {
        Coupon coupon;
        try {
            coupon = couponRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Coupon not found"));
            coupon.setActive(coupon.getActiveUntil().isAfter(LocalDateTime.now()));
            return couponMapper.toDto(coupon);
        }catch (ResourceNotFoundException e){
            e.printStackTrace();
            return null;
        }
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

    @Override
    public List<CouponDto> getCouponsByIds(List<Integer> ids) {
        List<Coupon> coupons = couponRepository.findAllById(ids).stream()
                .peek(coupon -> coupon.setActive(coupon.getActiveUntil().isAfter(LocalDateTime.now())))
                .toList();
        return coupons.stream()
                .map(coupon -> couponMapper.toDto(coupon))
                .collect(Collectors.toList());
    }

    @Override
    public List<CouponDto> getCouponsByNames(List<String> names) {
        List<Coupon> coupons = couponRepository.findAllByName(names).stream()
                .peek(coupon -> coupon.setActive(coupon.getActiveUntil().isAfter(LocalDateTime.now())))
                .toList();
        return coupons.stream()
                .map(coupon -> couponMapper.toDto(coupon))
                .collect(Collectors.toList());
    }

    @Override
    public List<CouponDto> getAllCoupons() {
        List<Coupon> coupons = couponRepository.findAll().stream()
                .peek(coupon -> coupon.setActive(coupon.getActiveUntil().isAfter(LocalDateTime.now())))
                .toList();
        return coupons.stream()
                .map(coupon -> couponMapper.toDto(coupon))
                .collect(Collectors.toList());
    }

    @Override
    public List<CouponDto> getAllCoupons(Sort sort) {
        List<Coupon> coupons = couponRepository.findAll(sort).stream()
                .peek(coupon -> coupon.setActive(coupon.getActiveUntil().isAfter(LocalDateTime.now())))
                .toList();
        return coupons.stream()
                .map(coupon -> couponMapper.toDto(coupon))
                .collect(Collectors.toList());
    }

    @Override
    public List<CouponDto> getAllCoupons(Pageable pageable) {
        List<Coupon> coupons = couponRepository.findAll(pageable).stream()
                .peek(coupon -> coupon.setActive(coupon.getActiveUntil().isAfter(LocalDateTime.now())))
                .toList();
        return coupons.stream()
                .map(coupon -> couponMapper.toDto(coupon))
                .collect(Collectors.toList());
    }

    @Override
    public CouponDto saveCoupon(CouponDto couponDto) {
        return couponMapper.toDto(couponRepository.save(couponMapper.toEntity(couponDto)));
    }

    @Override
    public List<CouponDto> saveCoupons(List<CouponDto> couponDtoList) {
        List<Coupon> coupons = couponDtoList.stream()
                .map(couponDto -> couponMapper.toEntity(couponDto))
                .toList();
        return couponRepository.saveAll(coupons).stream()
                .map(coupon -> couponMapper.toDto(coupon))
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteCouponById(Integer id) {
        Coupon coupon;
        try {
            coupon = couponRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Coupon not found"));
            couponRepository.deleteById(coupon.getId());
            return true;
        }catch (ResourceNotFoundException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void deleteCouponsByIds(List<Integer> ids) {
        couponRepository.deleteAllById(ids);
    }

    @Override
    public CouponDto updateCoupon(Integer couponId, CouponDto couponDto) {
        Coupon coupon;
        try {
            coupon = couponRepository.findById(couponId)
                    .orElseThrow(() -> new ResourceNotFoundException("Coupon not found"));
            couponRepository.deleteById(coupon.getId());
            return couponMapper.toDto(couponRepository.save(couponMapper.toEntity(couponDto)));
        }catch (ResourceNotFoundException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Long countCoupons() {
        return couponRepository.count();
    }
}
