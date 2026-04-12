package com.cur.furniture.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import com.cur.furniture.database.entity.Answer;
import com.cur.furniture.database.entity.Question;
import com.cur.furniture.database.entity.User;
import com.cur.furniture.database.repository.QuestionRepository;
import com.cur.furniture.database.repository.UserRepository;
import com.cur.furniture.dto.AnswerCreateDto;
import com.cur.furniture.dto.AnswerReadDto;
import com.cur.furniture.dto.UserReadDto;

@Mapper(componentModel = "spring")
public abstract class AnswerMapper {

    @Autowired private QuestionRepository questionRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private UserMapper userMapper;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "question", source = "questionId", qualifiedByName = "mapQuestion")
    @Mapping(target = "consultant", source = "consultantId", qualifiedByName = "mapConsultant")
    public abstract Answer toEntity(AnswerCreateDto createDto);
    
    @Mapping(target = "consultant", source = "consultant", qualifiedByName = "mapReadConsultant")
    public abstract AnswerReadDto toReadDto(Answer answer);

    @Named("mapReadConsultant")
    protected UserReadDto mapReadConsultant(User user) {
        return userMapper.toReadDto(user);
    }

    @Named("mapQuestion")
    protected Question mapQuestion(Long id) {
        return questionRepository.getReferenceById(id);
    }

    @Named("mapConsultant")
    protected User mapConsultant(Long id) {
        return userRepository.getReferenceById(id);
    }

}
