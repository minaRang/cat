package com.backendStudy.cat.service;

import com.backendStudy.cat.domain.DTOBoard;
import com.backendStudy.cat.domain.DTOFond;
import com.backendStudy.cat.mapper.FondMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FondServiceImpl implements FondService{
    @Autowired
    FondMapper fondMapper;

    @Override
    public Long updateFondBoard(Long boardIdx, Long userIdx, long score) {
        DTOFond fond = new DTOFond();
        fond.setBoardIdx(boardIdx);
        fond.setUserIdx(userIdx);
        fond.setFondScore(score);
        if(fondMapper.findBoardFond(fond).isPresent()) {
            fond = fondMapper.findBoardFond(fond).get();
            fond.setFondScore(score);
            fondMapper.updateFond(fond);
        }
        else
            fondMapper.insertFondBoard(fond);
        return fond.getFondIdx();
    }
    @Override
    public Long updateFondAnswer(Long answerIdx, Long userIdx, long score) {
        DTOFond fond = new DTOFond();
        fond.setAnswerIdx(answerIdx);
        fond.setUserIdx(userIdx);
        fond.setFondScore(score);
        if(fondMapper.findAnswerFond(fond).isPresent()) {
            fond = fondMapper.findAnswerFond(fond).get();
            fond.setFondScore(score);
            fondMapper.updateFond(fond);
        }
        else
            fondMapper.insertFondAnswer(fond);
        return fond.getFondIdx();
    }
    @Override
    public Integer cntFondBoard(Long boardIdx) {
        return fondMapper.findByBoardIdx(boardIdx).orElseGet(()->{
            return 0;});
    }
    @Override
    public Integer cntFondAnswer(Long answerIdx) {
        return fondMapper.findByAnswerIdx(answerIdx).orElseGet(()->{
            return 0;});
    }
    @Override
    public DTOFond findFondBoard(DTOFond fond){
        return fondMapper.findBoardFond(fond).orElseGet(()->{
            return new DTOFond();});
    }
    @Override
    public DTOFond findFondAnswer(DTOFond fond){
        return fondMapper.findAnswerFond(fond).orElseGet(()->{
            return new DTOFond();});
    }

}
