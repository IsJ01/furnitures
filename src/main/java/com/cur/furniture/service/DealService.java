package com.cur.furniture.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cur.furniture.database.entity.Deal;
import com.cur.furniture.database.repository.DealRepository;
import com.cur.furniture.dto.DealCreateDto;
import com.cur.furniture.dto.DealReadDto;
import com.cur.furniture.exception.DealNotFoundException;
import com.cur.furniture.mapper.DealMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DealService {

    private final DealMapper dealMapper;
    private final DealRepository dealRepository;

    @Transactional
    public void create(DealCreateDto dealCreateDto) {
        Deal deal = dealMapper.toEntity(dealCreateDto);
        dealRepository.save(deal);
    }

    public PagedModel<DealReadDto> findByPage(Pageable pageable) {
        return new PagedModel<>(dealRepository.findAll(pageable).map(dealMapper::toReadDto));
    }

    public DealReadDto findById(Long id) {
        return dealRepository.findById(id)
            .map(dealMapper::toReadDto)
            .orElseThrow(() -> new DealNotFoundException("Deal not found: " + id));
    }

}
