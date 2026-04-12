package com.cur.furniture.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import com.cur.furniture.database.entity.Answer;
import com.cur.furniture.database.entity.Question;
import com.cur.furniture.dto.AnswerReadDto;
import com.cur.furniture.dto.QuestionCreateDto;
import com.cur.furniture.dto.QuestionReadDto;

@Mapper(componentModel = "spring")
public abstract class QuestionMapper {

    @Autowired private AnswerMapper answerMapper;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "answer", ignore = true)
    public abstract Question toEntity(QuestionCreateDto createDto);
    
    @Mapping(target = "answer", source = "answer", qualifiedByName = "mapAnswer")
    @Mapping(target = "isClosed", source = "closed")
    public abstract QuestionReadDto toReadDto(Question question);

    @Named("mapAnswer")
    protected AnswerReadDto mapQuestion(Answer answer) {
        return answerMapper.toReadDto(answer);
    }

}
